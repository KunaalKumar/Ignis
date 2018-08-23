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
        if (creators.length == 0) {
            this.creators = new Relation[]{
                    new Relation("Unknown", 0, "Unknown", "Unknown")};
        } else {
            this.creators = creators;
        }
        buttons = new ArrayList<>();
    }

    @NonNull
    @Override
    public RelationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_character_relation, parent, false);
        return new RelationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelationViewHolder holder, final int position) {

        // Add button to list
        buttons.add(holder.button);

        holder.button.setText(creators[position].getName());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (creators[position].getName().equals("Unknown")) {
                    Toast.makeText(v.getContext(),
                            "Unknown",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(v.getContext(),
                            "Info coming later",
                            Toast.LENGTH_LONG).show();
                }
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
