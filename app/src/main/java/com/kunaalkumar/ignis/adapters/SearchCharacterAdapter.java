package com.kunaalkumar.ignis.adapters;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.activities.CharacterActivity;
import com.kunaalkumar.ignis.activities.SearchActivity;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.CharacterBrief;
import com.kunaalkumar.ignis.utils.SharedPrefs;
import com.peekandpop.shalskar.peekandpop.PeekAndPop;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchCharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String EXTRA_URL_HD = "com.kunaalkumar.ignis.URL.HD";
    public static final String EXTRA_URL_STD = "com.kunaalkumar.ignis.URL.Std";
    public static final String EXTRA_ID = "com.kunaalkumar.ignis.ID";
    public static final String EXTRA_NAME = "com.kunaalkumar.ignis.NAME";

    private static final int VIEW_TYPE_BANNER = 0;
    private static final int VIEW_TYPE_COVER = 1;

    public ArrayList<CharacterBrief> searchResults;
    public String currentQuery;
    private static String[] bannerViewTypes = new String[]{"character", "team", "person"};
    private SearchActivity activity;
    private PeekAndPop peekAndPop;
    private ImageView peekImageView;


    public SearchCharacterAdapter(SearchActivity activity, final PeekAndPop peekAndPop, String currentQuery) {
        this.activity = activity;
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

    private void initPeekPreview(PeekAndPop peekAndPop) {
        View peekView = peekAndPop.getPeekView();

        peekImageView = peekView.findViewById(R.id.preview_dialog);

        peekAndPop.setOnGeneralActionListener(new PeekAndPop.OnGeneralActionListener() {
            @Override
            public void onPeek(View view, int position) {
                if (searchResults.get(position).getImage() == null) {

                    loadImageFromRes(R.drawable.image_not_available, peekImageView);

                    Toast.makeText(activity, "No image found for " + searchResults.get(position).getName(), Toast.LENGTH_LONG).show();

                } else {
                    if (SharedPrefs.getPeekHighResImageState()) {
                        loadImageFromURL(searchResults.get(position).getImage().getOriginalUrl(), peekImageView);
                    } else {
                        loadImageFromURL(searchResults.get(position).getImage().getMediumUrl(), peekImageView);
                    }
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
                        shareUrlIntent(activity, searchResults.get(i).getName(), searchResults.get(i).getSiteDetailUrl());
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
                        bannerOnClick(searchResults.get(i));
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

    public static void shareUrlIntent(Activity activity, String subject, String url) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        i.putExtra(Intent.EXTRA_TEXT, url);
        activity.startActivity(Intent.createChooser(i, "Share URL"));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        initPeekPreview(peekAndPop);
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_banner, parent, false);
            return new BannerViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        // Last item loaded, load more if available
        if (getItemCount() == 10) {
            ((SearchActivity) activity).characterFragment.searchCall(activity, currentQuery, false);
        }

        final CharacterBrief searchResult = searchResults.get(position);

        switch (holder.getItemViewType())

        {
            // Case: is Banner View
            case VIEW_TYPE_BANNER:

                populateBanner((BannerViewHolder) holder, position, searchResult);

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
        return 0;
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

    // Populate for banner view holder
    private void populateBanner(BannerViewHolder viewHolder,
                                final int position,
                                final CharacterBrief searchResult) {

        peekAndPop.addLongClickView(viewHolder.parentLayout, position);

        Log.d("Ignis", "onBindViewHolder called: " + searchResult.getId());


        if (searchResult.getImage() == null) {
            loadImageFromRes(R.drawable.image_not_available, viewHolder.image);
        } else {
            if (SharedPrefs.getPeekHighResImageState()) {
                loadImageFromUrlFetch(searchResult.getImage().getScreenLargeUrl(),
                        viewHolder.image,
                        searchResult.getImage().getOriginalUrl());
            }
            loadImageFromURL(searchResult.getImage().getScreenLargeUrl(),
                    viewHolder.image);
        }

        if (searchResult.getName() != null) {
            viewHolder.name.setText(searchResult.getName());
        } else {
            viewHolder.name.setText(R.string.error_name_nf);
        }

        if (searchResult.getPublisher() != null) {
            viewHolder.additionInformation.setText(searchResult.getPublisher().getName());
        } else {
            viewHolder.additionInformation.setText(R.string.publisher_not_found);
        }

        if (searchResult.getRealName() != null) {
            viewHolder.realName.setText(searchResult.getRealName());
        } else {
            viewHolder.realName.setTextColor(Color.RED);
            viewHolder.realName.setText(R.string.Real_name_not_found);
        }

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bannerOnClick(searchResult);
            }
        });


    }

    private void bannerOnClick(CharacterBrief searchResult) {
        Intent intent = new Intent(activity, CharacterActivity.class);
        if (searchResult.getImage() != null) {
            intent.putExtra(EXTRA_URL_STD, searchResult.getImage().getMediumUrl());
            intent.putExtra(EXTRA_URL_HD, searchResult.getImage().getOriginalUrl());
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


                Picasso get and populate images




     */

    //    Loads given url image into given iamgeview
    public static void loadImageFromURL(String url, final ImageView imageView) {

        Picasso.get()
                .load(url)
                .into(imageView);
    }

    // Loads given url image into given imageview and pre-fetches the second given url
    public static void loadImageFromUrlFetch(String url, final ImageView imageView, final String secondUrl) {
        Picasso.get()
                .load(url)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        // Only load if needed
                        if (SharedPrefs.getPeekHighResImageState()) {
                            Picasso.get()
                                    .load(secondUrl)
                                    .fetch();
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        // Failed
                    }
                });
    }

    //    Loads given res image into given imageview
    public static void loadImageFromRes(int res, ImageView imageView) {
        Picasso.get()
                .load(res)
                .into(imageView);
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

        @BindView(R.id.banner_real_name)
        TextView realName;

        @BindView(R.id.banner_parent_layout)
        RelativeLayout parentLayout;

        @BindView(R.id.banner_progress)
        ProgressBar progressBar;

        private BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
