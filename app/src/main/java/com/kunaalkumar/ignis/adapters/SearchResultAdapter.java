package com.kunaalkumar.ignis.adapters;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.activities.search.SearchActivity;
import com.kunaalkumar.ignis.utils.SharedPrefs;
import com.kunaalkumar.ignis.web_scraper.SearchResult;
import com.peekandpop.shalskar.peekandpop.PeekAndPop;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String EXTRA_URL_HD = "com.kunaalkumar.ignis.URL.HD";
    public static final String EXTRA_URL_STD = "com.kunaalkumar.ignis.URL.Std";
    public static final String EXTRA_ID = "com.kunaalkumar.ignis.ID";
    public static final String EXTRA_NAME = "com.kunaalkumar.ignis.NAME";

    private static final int VIEW_TYPE_BANNER = 0;

    public ArrayList<SearchResult> searchResults;
    private SearchActivity activity;
    private PeekAndPop peekAndPop;
    private ImageView peekImageView;


    public SearchResultAdapter(SearchActivity activity, final PeekAndPop peekAndPop,
                               ArrayList<SearchResult> searchResults) {
        this.activity = activity;
        this.peekAndPop = peekAndPop;

        this.searchResults = searchResults;
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
                if (searchResults.get(position).getImageUrl() == null) {

                    loadImageFromRes(R.drawable.image_not_available, peekImageView);

                    Toast.makeText(activity, "No image found for " + searchResults.get(position).getName(), Toast.LENGTH_LONG).show();

                } else {
                    if (SharedPrefs.getPeekHighResImageState()) {
                        loadImageFromURL(searchResults.get(position).getImageUrl(), peekImageView);
                    } else {
                        loadImageFromURL(searchResults.get(position).getImageUrl(), peekImageView);
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
                        shareUrlIntent(activity, searchResults.get(i).getName(), searchResults.get(i).getResultUri());
                        break;
                    case R.id.peek_view_copy:
                        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText(searchResults.get(i).getName(),
                                searchResults.get(i).getResultUri());

                        clipboard.setPrimaryClip(clip);
                        Snackbar.make(activity.findViewById(android.R.id.content), "Copied to clipboard",
                                Snackbar.LENGTH_SHORT).show();
                        break;

                    case R.id.peek_view_open:
                        bannerOnClick();
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
                        break;
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_banner,
                    parent, false);
            return new BannerViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        final SearchResult searchResult = searchResults.get(position);

        initBanner((BannerViewHolder) holder, position, searchResult);

    }

    @Override
    public int getItemCount() {
        return searchResults.size();
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
    private void initBanner(final BannerViewHolder viewHolder,
                            final int position,
                            final SearchResult searchResult) {

        peekAndPop.addLongClickView(viewHolder.parentLayout, position);

        Log.d("IGNIS", "onBindViewHolder called: " + searchResult.getName());

        // Set favorite state
        //TODO: Implement favorite
//        if (SharedPrefs.getFavoriteCharacters().contains(searchResult.getId())) {
//            viewHolder.setFavoriteState(true);
//        } else {
//            viewHolder.setFavoriteState(false);
//        }

        // Populate image
        if (searchResult.getImageUrl() == null) {
            loadImageFromRes(R.drawable.image_not_available, viewHolder.image);
        } else {
            //TODO: implement highres and medium res
            if (SharedPrefs.getPeekHighResImageState()) {
                loadImageFromUrlFetch(searchResult.getImageUrl(),
                        viewHolder.image,
                        searchResult.getImageUrl());
            }
            loadImageFromURL(searchResult.getImageUrl(),
                    viewHolder.image);
        }

        // Populate name
        if (searchResult.getName() != null) {
            viewHolder.name.setText(searchResult.getName());
        } else {
            viewHolder.name.setText(R.string.error_name_nf);
        }

        // Populate chip
        if (searchResult.getChipInfo() != null) {
            viewHolder.chip.setText(searchResult.getChipInfo());
        } else {
            viewHolder.chip.setText("Unknown");
        }

        // Parent OnClick
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bannerOnClick();
            }
        });

        // Favorite OnClick
        viewHolder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: implement favorite
                if (viewHolder.favoriteState) {
                    // Remove from favorites
//                    SharedPrefs.removeFavoriteCharacter(searchResult.getId());
                } else {
                    // Add to favorites
//                    SharedPrefs.addFavoriteCharacter(searchResult.getId());
                }
                viewHolder.setFavoriteState(!viewHolder.favoriteState);
                System.console();
            }
        });

    }

    private void bannerOnClick() {
        Toast.makeText(activity, "Need to implement", Toast.LENGTH_LONG).show();
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

        @BindView(R.id.banner_parent_layout)
        RelativeLayout parentLayout;

        @BindView(R.id.banner_progress)
        ProgressBar progressBar;

        @BindView(R.id.banner_favorite)
        ImageView favorite;

        @BindView(R.id.banner_chip)
        TextView chip;

        boolean favoriteState;

        private BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            favoriteState = false;

            if (SharedPrefs.getImageWidthState()) {
                FrameLayout.LayoutParams params =
                        new FrameLayout.LayoutParams(image.getLayoutParams());

                params.setMarginStart(0);
                params.setMarginEnd(0);
                image.setLayoutParams(params);
            }
        }

        public void setFavoriteState(boolean state) {
            favoriteState = state;

            if (state)
                favorite.setBackgroundResource(R.drawable.ic_favorite_filled_24dp);
            else
                favorite.setBackgroundResource(R.drawable.ic_favorite_unfilled_24dp);
        }
    }
}
