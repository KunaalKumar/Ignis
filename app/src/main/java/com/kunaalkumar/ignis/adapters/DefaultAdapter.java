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
import com.kunaalkumar.ignis.activities.CharacterActivity;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.activities.SearchActivity;
import com.kunaalkumar.ignis.comicvine_objects.SearchResult;
import com.peekandpop.shalskar.peekandpop.PeekAndPop;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DefaultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String EXTRA_URL = "com.kunaalkumar.ignis.URL";
    public static final String EXTRA_ID = "com.kunaalkumar.ignis.ID";

    public static ArrayList<SearchResult> searchResults;
    private Activity activity;
    private PeekAndPop peekAndPop;
    private View peekView;
    private ImageView peekImageView;

    public DefaultAdapter(Activity context, PeekAndPop peekAndPop) {
        this.activity = context;
        this.peekAndPop = peekAndPop;

        searchResults = new ArrayList<SearchResult>();

        peekView = peekAndPop.getPeekView();

        peekImageView = peekView.findViewById(R.id.preview_dialog);

        peekAndPop.setOnGeneralActionListener(new PeekAndPop.OnGeneralActionListener() {
            @Override
            public void onPeek(View view, int position) {

                Glide.with(activity)
                        .load(searchResults.get(position).getImage().getOriginalUrl())
                        .into(peekImageView);
            }

            @Override
            public void onPop(View view, int position) {
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_banner, parent, false);
            BannerViewHolder bannerViewHolder = new BannerViewHolder(view);
            return bannerViewHolder;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cover, parent, false);
        CoverViewHolder coverViewHolder = new CoverViewHolder(view);
        return coverViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        // Last item loaded, load more if available
        if (position == (getItemCount() - 1)) {
            SearchActivity.nextPage(activity);
            Toast.makeText(activity, "Last element is visible", Toast.LENGTH_LONG).show();
        }

        final SearchResult searchResult = searchResults.get(position);

        switch (holder.getItemViewType()) {
            // Case: is Banner View
            case 0:

                final BannerViewHolder viewHolder = (BannerViewHolder) holder;

                peekAndPop.addLongClickView(viewHolder.parentLayout, position);

                Log.d("Ignis", "onBindViewHolder called: " + searchResult.getId());


                if (searchResult.getImage() != null) {

                    Glide.with(activity)
                            .load(searchResult.getImage().getScreenLargeUrl())
                            .apply(new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    viewHolder.progressBar.setVisibility(View.GONE);
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    viewHolder.progressBar.setVisibility(View.GONE);
                                    return false;
                                }
                            })
                            .into(viewHolder.image);

                    Glide.with(activity)
                            .load(searchResult.getImage().getOriginalUrl())
                            .preload();

                }

                if (searchResult.getName() != null) {
                    viewHolder.name.setText(searchResult.getName());
                } else {
                    viewHolder.name.setText("Name not found");
                }

                viewHolder.resourceType.setText(searchResult.getResourceType());

                viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(activity, "Clicked on " + position, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(activity, CharacterActivity.class);
                        String url = searchResult.getImage().getOriginalUrl();
                        intent.putExtra(EXTRA_URL, url);
                        intent.putExtra(EXTRA_ID, searchResult.getId());
                        activity.startActivity(intent);
                    }
                });
                break;

            // Case: is Cover View
            case 1:
                final CoverViewHolder coverViewHolder = (CoverViewHolder) holder;

                peekAndPop.addLongClickView(coverViewHolder.parentLayout, position);

                Log.d("Ignis", "onBindViewHolder called: " + searchResult.getId());


                if (searchResult.getImage() != null) {

                    Glide.with(activity)
                            .load(searchResult.getImage().getMediumUrl())
                            .apply(new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    coverViewHolder.progressBar.setVisibility(View.GONE);
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    coverViewHolder.progressBar.setVisibility(View.GONE);
                                    return false;
                                }
                            })
                            .into(coverViewHolder.image);

                    Glide.with(activity)
                            .load(searchResult.getImage().getOriginalUrl())
                            .preload();

                }

                if (searchResult.getName() != null) {
                    coverViewHolder.name.setText(searchResult.getName());
                } else {
                    coverViewHolder.name.setText("Name not found");
                }

                coverViewHolder.resourceType.setText(searchResult.getResourceType());

                coverViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(activity, "Clicked on " + position, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(activity, CharacterActivity.class);
                        String url = searchResult.getImage().getOriginalUrl();
                        intent.putExtra(EXTRA_URL, url);
                        intent.putExtra(EXTRA_ID, searchResult.getId());
                        activity.startActivity(intent);
                    }
                });
        }
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (searchResults.get(position).getResourceType().equals("character")) {
            return 0;
        }

        return 1;
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner_image)
        ImageView image;

        @BindView(R.id.banner_name)
        TextView name;

        @BindView(R.id.banner_resource_type)
        TextView resourceType;

        @BindView(R.id.banner_parent_layout)
        RelativeLayout parentLayout;

        @BindView(R.id.banner_progress)
        ProgressBar progressBar;

        public BannerViewHolder(@NonNull View itemView) {
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


        public CoverViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
