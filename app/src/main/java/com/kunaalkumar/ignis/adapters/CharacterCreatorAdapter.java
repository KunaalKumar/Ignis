package com.kunaalkumar.ignis.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CharacterCreatorAdapter extends
        RecyclerView.Adapter<CharacterCreatorAdapter.CharacterCreatorViewHolder> {

    public Relation[] creators;

    public CharacterCreatorAdapter(Relation[] creators) {
        this.creators = creators;
    }

    @NonNull
    @Override
    public CharacterCreatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_character_creators, parent, false);
        return new CharacterCreatorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterCreatorViewHolder holder, int position) {
        holder.button.setText(creators[position].getName());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "Creator information coming later.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return creators.length;
    }

    public class CharacterCreatorViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view_character_creator_button)
        MaterialButton button;

        public CharacterCreatorViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
