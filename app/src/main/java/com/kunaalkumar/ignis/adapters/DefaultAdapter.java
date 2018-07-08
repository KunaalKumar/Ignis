package com.kunaalkumar.ignis.adapters;

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
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DefaultAdapter extends RecyclerView.Adapter<DefaultAdapter.ViewHolder> {

    private Result[] searchResults;


    public DefaultAdapter(Result[] searchResults) {
        this.searchResults = searchResults;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Result result = searchResults[position];


        Log.d("Ignis", "onBindViewHolder called: " + result.getId());

        // TODO: Check if character gender and apply placeholder image if needed
        if (result.getImage() == null) {
            Glide.with(holder.itemView)
                    .load(R.drawable.placeholder_male_superhero)
                    .into(holder.image);
        } else {
            Glide.with(holder.itemView)
                    .load(result.getImage().getScreenLargeUrl())
                    .into(holder.image);
        }

        holder.name.setText(result.getName());
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

        @BindView(R.id.real_name)
        TextView realName;

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
