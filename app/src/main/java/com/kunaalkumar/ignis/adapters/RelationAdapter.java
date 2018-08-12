package com.kunaalkumar.ignis.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RelationAdapter extends
        RecyclerView.Adapter<RelationAdapter.RelationViewHolder> {

    public Relation[] creators;
    public ArrayList<Button> buttons;

    public RelationAdapter(Relation[] creators) {
        this.creators = creators;
        buttons = new ArrayList<>();
    }

    @NonNull
    @Override
    public RelationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_character_creators, parent, false);
        return new RelationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelationViewHolder holder, int position) {

        // Add button to list
        buttons.add(holder.button);

        holder.button.setText(creators[position].getName());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "Creator info coming later",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return creators.length;
    }

    public class RelationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view_character_creator_button)
        MaterialButton button;

        public RelationViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
