package com.kunaalkumar.ignis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomBarFragment extends Fragment {

    public static final String ARG_NAME = "arg_name";

    @BindView(R.id.bottom_bar_name)
    TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bottom_bar, container, false);

        ButterKnife.bind(this, rootView);

        String title = getArguments().getString(ARG_NAME, "");
        name.setText(title);

        return rootView;
    }

}
