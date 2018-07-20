package com.kunaalkumar.ignis.adapters;

import android.app.Activity;
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
            case 0:

                populateBanner((BannerViewHolder) holder, position, searchResult);

                break;

            // Case: is Cover View
            case 1:

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
                Toast.makeText(activity, "Not implemented yet", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(activity, CharacterActivity.class);
//                        String url = searchResult.getImage().getOriginalUrl();
//                        intent.putExtra(EXTRA_URL, url);
//                        intent.putExtra(EXTRA_ID, searchResult.getId());
//                        activity.startActivity(intent);
            }
        });
    }

    // Populate for banner view holder
    private void populateBanner(BannerViewHolder viewHolder, final int position, final SearchResult searchResult) {

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
            viewHolder.additionInformation.setText(searchResult.getPublisher().getName());

            viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, CharacterActivity.class);
                    if (searchResult.getImage() != null) {
                        String url = searchResult.getImage().getOriginalUrl();
                        intent.putExtra(EXTRA_URL, url);
                    }

                    intent.putExtra(EXTRA_ID, searchResult.getId());
                    intent.putExtra(EXTRA_NAME, searchResult.getName());
                    activity.startActivity(intent);
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
                .into(imageView);
    }

    private void populateImage(String imageUrl, String originalImageUrl, final ProgressBar progressBar, ImageView imageView) {
        Glide.with(activity)
                .load(imageUrl)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
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
