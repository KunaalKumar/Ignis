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
import com.kunaalkumar.ignis.comicvine_objects.CharacterResults;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DefaultAdapter extends RecyclerView.Adapter<DefaultAdapter.ViewHolder> {

    private CharacterResults[] characterResults;


    public DefaultAdapter(CharacterResults[] characterResults) {
        this.characterResults = characterResults;
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
        Log.d("Ignis", "onBindViewHolder called: " + characterResults[position].getName());

        CharacterResults character = characterResults[position];

        // TODO: Check if character gender and apply placeholder image if needed
        if (character.getImage() == null) {
            if (character.getGender() == 1) {
                Glide.with(holder.itemView)
                        .load(R.drawable.placeholder_male_superhero)
                        .into(holder.superheroImage);
            } else {
                Glide.with(holder.itemView)
                        .load(R.drawable.placeholder_female_superhero)
                        .into(holder.superheroImage);
            }
        } else if (character.getGender() == 1) {
            Glide.with(holder.itemView)
                    .load(characterResults[position].getImage().getSmallUrl())
                    .into(holder.superheroImage);
        } else {
            Glide.with(holder.itemView)
                    .load(characterResults[position].getImage().getSmallUrl())
                    .into(holder.superheroImage);
        }

        holder.superheroName.setText(characterResults[position].getName());

    }

    @Override
    public int getItemCount() {
        return characterResults.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.character_image)
        ImageView superheroImage;

        @BindView(R.id.superhero_name)
        TextView superheroName;

        @BindView(R.id.parent_layout)
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
