package com.kunaalkumar.ignis.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.comicvine_objects.Result;
import com.peekandpop.shalskar.peekandpop.PeekAndPop;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.NonNull;
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

//                peekImageView.getHierarchy().setProgressBarImage(new ProgressBarDrawable());
//                peekImageView.setImageURI(Uri.parse(searchResults[position].getImage().getMediumUrl()));

                Picasso.get().load(Uri.parse(searchResults[position].getImage().getOriginalUrl())).into(peekImageView);
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
            Uri uri = Uri.parse(result.getImage().getOriginalUrl());
            holder.image.getHierarchy().setProgressBarImage(new ProgressBarDrawable());
            holder.image.setImageURI(uri);
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
        SimpleDraweeView image;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.resource_type)
        TextView resourceType;

        @BindView(R.id.parent_layout)
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
