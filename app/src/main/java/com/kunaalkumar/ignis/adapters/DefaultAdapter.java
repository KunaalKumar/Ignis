package com.kunaalkumar.ignis.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.comicvine_objects.Result;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DefaultAdapter extends RecyclerView.Adapter<DefaultAdapter.ViewHolder> {

    private Result[] searchResults;
    private Context context;


    public DefaultAdapter(Result[] searchResults, Context context) {
        this.searchResults = searchResults;
        this.context = context;
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

        Result result = searchResults[position];


        Log.d("Ignis", "onBindViewHolder called: " + result.getId());

        if (result.getImage() != null) {
            Glide.with(holder.itemView)
                    .asBitmap()
                    .load(result.getImage().getOriginalUrl())
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
