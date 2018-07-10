package com.kunaalkumar.ignis.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.comicvine_objects.Result;
import com.peekandpop.shalskar.peekandpop.PeekAndPop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DefaultAdapter extends RecyclerView.Adapter<DefaultAdapter.ViewHolder> {

    private Result[] searchResults;
    private Activity activity;
    private PeekAndPop peekAndPop;
    private View peekView;
    private ImageView peekImageView;

    public DefaultAdapter(final Result[] searchResults, Activity context, PeekAndPop peekAndPop) {
        this.searchResults = searchResults;
        this.activity = context;
        this.peekAndPop = peekAndPop;

        peekView = peekAndPop.getPeekView();

        peekImageView = peekView.findViewById(R.id.preview_dialog);

        peekAndPop.setOnGeneralActionListener(new PeekAndPop.OnGeneralActionListener() {
            @Override
            public void onPeek(View view, int position) {

                Glide.with(activity)
                        .load(Uri.parse(searchResults[position].getImage().getOriginalUrl()))
                        .apply(new RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.ALL))
                        .into(peekImageView);
            }

            @Override
            public void onPop(View view, int position) {
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Result result = searchResults[position];

        peekAndPop.addLongClickView(holder.parentLayout, position);

        Log.d("Ignis", "onBindViewHolder called: " + result.getId());


        if (result.getImage() != null) {

            Glide.with(activity)
                    .load(Uri.parse(searchResults[position].getImage().getOriginalUrl()))
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            holder.progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            holder.progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.image);

        }

        if (result.getName() != null) {
            holder.name.setText(result.getName());
        } else {
            holder.name.setText("Name not found");
        }

        holder.resourceType.setText(result.getResourceType());
    }

    @Override
    public int getItemCount() {
        return searchResults.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.resource_type)
        TextView resourceType;

        @BindView(R.id.parent_layout)
        RelativeLayout parentLayout;

        @BindView(R.id.progress)
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
