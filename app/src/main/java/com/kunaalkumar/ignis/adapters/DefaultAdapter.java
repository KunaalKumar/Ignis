package com.kunaalkumar.ignis.adapters;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.snackbar.Snackbar;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.activities.CharacterActivity;
import com.kunaalkumar.ignis.activities.SearchActivity;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.SearchResult;
import com.peekandpop.shalskar.peekandpop.PeekAndPop;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DefaultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String EXTRA_URL = "com.kunaalkumar.ignis.URL";
    public static final String EXTRA_ID = "com.kunaalkumar.ignis.ID";
    public static final String EXTRA_NAME = "com.kunaalkumar.ignis.NAME";

    private static final int VIEW_TYPE_BANNER = 0;
    private static final int VIEW_TYPE_COVER = 1;

    public static ArrayList<SearchResult> searchResults;
    public String currentQuery;
    private static String[] bannerViewTypes = new String[]{"character", "team", "person"};
    private Activity activity;
    private PeekAndPop peekAndPop;
    private ImageView peekImageView;


    public DefaultAdapter(Activity context, final PeekAndPop peekAndPop, String currentQuery) {
        this.activity = context;
        this.peekAndPop = peekAndPop;
        this.currentQuery = currentQuery;

        searchResults = new ArrayList<>();

    }

    /*




    .___              .__
    |   | ____   ____ |__| ______
    |   |/ ___\ /    \|  |/  ___/
    |   / /_/  >   |  \  |\___ \
    |___\___  /|___|  /__/____  >
       /_____/      \/        \/


                Init Peek and Pop




     */

    private void initPeekPreview(PeekAndPop peekAndPop, final int viewType) {
        View peekView = peekAndPop.getPeekView();

        peekImageView = peekView.findViewById(R.id.preview_dialog);

        peekAndPop.setOnGeneralActionListener(new PeekAndPop.OnGeneralActionListener() {
            @Override
            public void onPeek(View view, int position) {
                if (searchResults.get(position).getImage() == null) {

                    populateImageWithError(peekImageView);

                    Toast.makeText(activity, "No image found for " + searchResults.get(position).getName(), Toast.LENGTH_LONG).show();

                } else {
                    Glide.with(activity)
                            .load(searchResults.get(position).getImage().getOriginalUrl())
                            .into(peekImageView);
                }

            }

            @Override
            public void onPop(View view, int position) {
                // Called when finger is lifted
            }
        });

        peekAndPop.setOnHoldAndReleaseListener(new PeekAndPop.OnHoldAndReleaseListener() {
            @Override
            public void onHold(View view, int i) {
                // do something on hold
            }

            @Override
            public void onLeave(View view, int i) {
                // do something on leave, without lifting finger
            }

            @Override
            public void onRelease(View view, int i) {

                switch (view.getId()) {
                    case R.id.peek_view_share:
                        shareUrlIntent(searchResults.get(i).getName(), searchResults.get(i).getSiteDetailUrl());
                        break;
                    case R.id.peek_view_copy:
                        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText(searchResults.get(i).getName(),
                                searchResults.get(i).getSiteDetailUrl());

                        clipboard.setPrimaryClip(clip);
                        Snackbar.make(activity.findViewById(android.R.id.content), "Copied to clipboard",
                                Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.peek_view_open:
                        switch (viewType) {
                            case VIEW_TYPE_BANNER:
                                bannerOnClick(searchResults.get(i));
                                break;
                            case VIEW_TYPE_COVER:
                                coverOnClick(searchResults.get(i));
                                break;
                            default:
                                break;
                        }
                        break;
                }
            }
        });

        peekAndPop.addHoldAndReleaseView(R.id.peek_view_share);
        peekAndPop.addHoldAndReleaseView(R.id.peek_view_copy);
        peekAndPop.addHoldAndReleaseView(R.id.peek_view_open);

        peekAndPop.setOnLongHoldListener(new PeekAndPop.OnLongHoldListener() {
            @Override
            public void onEnter(View view, int i) {
//                Toast.makeText(activity, "Entered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongHold(View view, int i) {
                switch (view.getId()) {
                    case R.id.peek_view_share:
                        Toast.makeText(activity, "Share", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.peek_view_copy:
                        Toast.makeText(activity, "Copy to clipboard", Toast.LENGTH_SHORT).show();
                    case R.id.peek_view_open:
                        Toast.makeText(activity, "Open " + searchResults.get(i).getName() + "'s page", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

        peekAndPop.addLongHoldView(R.id.peek_view_share, false);
        peekAndPop.addLongHoldView(R.id.peek_view_copy, false);
        peekAndPop.addLongHoldView(R.id.peek_view_open, false);

    }

    private void shareUrlIntent(String subject, String url) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        i.putExtra(Intent.EXTRA_TEXT, url);
        activity.startActivity(Intent.createChooser(i, "Share URL"));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_banner, parent, false);
            return new BannerViewHolder(view);
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cover, parent, false);
        return new CoverViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        // Last item loaded, load more if available
        if (position == (getItemCount() - 1)) {
            SearchActivity.nextPage(activity, currentQuery);
        }

        final SearchResult searchResult = searchResults.get(position);

        switch (holder.getItemViewType()) {

            // Case: is Banner View
            case VIEW_TYPE_BANNER:

                populateBanner((BannerViewHolder) holder, position, searchResult);

                break;

            // Case: is Cover View
            case VIEW_TYPE_COVER:

                populateCover((CoverViewHolder) holder, position, searchResult);

                break;

            default:
                Snackbar.make(holder.itemView, "Something is terribly wrong.", Snackbar.LENGTH_INDEFINITE).show();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (Arrays.asList(bannerViewTypes).contains(searchResults.get(position).getResourceType())) {
            return 0;
        }

        return 1;
    }

    /*




    .___              .__
    |   | ____   ____ |__| ______
    |   |/ ___\ /    \|  |/  ___/
    |   / /_/  >   |  \  |\___ \
    |___\___  /|___|  /__/____  >
       /_____/      \/        \/


                Populate view holders




     */


    // Populate for cover view holder
    private void populateCover(CoverViewHolder viewHolder, final int position, final SearchResult searchResult) {

        initPeekPreview(peekAndPop, VIEW_TYPE_COVER);

        peekAndPop.addLongClickView(viewHolder.parentLayout, position);

        Log.d("Ignis", "onBindViewHolder called: " + searchResult.getId());


        if (searchResult.getImage() == null) {
            populateImageWithError(viewHolder.image);
        } else {
            populateImage(searchResult.getImage().getMediumUrl(),
                    searchResult.getImage().getOriginalUrl(),
                    viewHolder.progressBar, viewHolder.image);
        }

        if (searchResult.getName() != null) {
            viewHolder.name.setText(searchResult.getName());
        } else {
            viewHolder.name.setText(R.string.error_name_nf);
        }

        viewHolder.resourceType.setText(searchResult.getResourceType());

        // TODO: change to call other activity with intent
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coverOnClick(searchResult);
            }
        });
    }

    private void coverOnClick(SearchResult searchResult) {
        Snackbar.make(activity.findViewById(android.R.id.content), "Cover views aren't implemented yet.",
                Snackbar.LENGTH_SHORT).show();
    }

    // Populate for banner view holder
    private void populateBanner(BannerViewHolder viewHolder, final int position, final SearchResult searchResult) {

        initPeekPreview(peekAndPop, VIEW_TYPE_BANNER);

        peekAndPop.addLongClickView(viewHolder.parentLayout, position);

        Log.d("Ignis", "onBindViewHolder called: " + searchResult.getId());


        if (searchResult.getImage() == null) {
            populateImageWithError(viewHolder.image);
        } else {
            populateImage(searchResult.getImage().getScreenLargeUrl(),
                    searchResult.getImage().getOriginalUrl(),
                    viewHolder.progressBar, viewHolder.image);
        }

        if (searchResult.getName() != null) {
            viewHolder.name.setText(searchResult.getName());
        } else {
            viewHolder.name.setText(R.string.error_name_nf);
        }

        viewHolder.resourceType.setText(searchResult.getResourceType());

        if (searchResult.getResourceType().equals("character")) {
            if (searchResult.getPublisher() != null) {
                viewHolder.additionInformation.setText(searchResult.getPublisher().getName());
            } else {
                viewHolder.additionInformation.setText(R.string.error_name_nf);
            }

            viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bannerOnClick(searchResult);
                }
            });
        }

        if (searchResult.getResourceType().equals("team")) {
            viewHolder.additionInformation.setText(searchResult.getPublisher().getName());
        }

        if (searchResult.getResourceType().equals("person")) {
            viewHolder.additionInformation.setText(searchResult.getCountry());
        }
    }

    private void bannerOnClick(SearchResult searchResult) {
        Intent intent = new Intent(activity, CharacterActivity.class);
        if (searchResult.getImage() != null) {
            String url = searchResult.getImage().getOriginalUrl();
            intent.putExtra(EXTRA_URL, url);
        }

        intent.putExtra(EXTRA_ID, searchResult.getId());
        intent.putExtra(EXTRA_NAME, searchResult.getName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(intent);
    }


    /*




    .___              .__
    |   | ____   ____ |__| ______
    |   |/ ___\ /    \|  |/  ___/
    |   / /_/  >   |  \  |\___ \
    |___\___  /|___|  /__/____  >
       /_____/      \/        \/


                Populate images




     */

    // Populates given image view with error
    private void populateImageWithError(ImageView imageView) {
        Glide.with(activity)
                .load(R.drawable.image_not_available)
                .apply(new RequestOptions()
                        .dontAnimate())
                .into(imageView);
    }

    private void populateImage(String imageUrl, String originalImageUrl, final ProgressBar progressBar, ImageView imageView) {
        Glide.with(activity)
                .load(imageUrl)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .apply(new RequestOptions()
                        .dontAnimate())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageView);

        Glide.with(activity)
                .load(originalImageUrl)
                .apply(new RequestOptions()
                        .dontAnimate())
                .preload();
    }


    /*




    .___              .__
    |   | ____   ____ |__| ______
    |   |/ ___\ /    \|  |/  ___/
    |   / /_/  >   |  \  |\___ \
    |___\___  /|___|  /__/____  >
       /_____/      \/        \/


                View holders




     */

    public static class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner_image)
        ImageView image;

        @BindView(R.id.banner_name)
        TextView name;

        @BindView(R.id.banner_additional_info)
        TextView additionInformation;

        @BindView(R.id.banner_resource_type)
        TextView resourceType;

        @BindView(R.id.banner_parent_layout)
        RelativeLayout parentLayout;

        @BindView(R.id.banner_progress)
        ProgressBar progressBar;

        private BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class CoverViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cover_image)
        ImageView image;

        @BindView(R.id.cover_name)
        TextView name;

        @BindView(R.id.cover_resource_type)
        TextView resourceType;

        @BindView(R.id.cover_parent_layout)
        RelativeLayout parentLayout;

        @BindView(R.id.cover_progress)
        ProgressBar progressBar;


        private CoverViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
