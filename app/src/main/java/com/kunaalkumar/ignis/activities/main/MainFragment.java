package com.kunaalkumar.ignis.activities.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.fragments.FavoritesFragment;
import com.kunaalkumar.ignis.fragments.NewsFragment;
import com.kunaalkumar.ignis.fragments.RandomFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kunaalkumar.ignis.MainActivity.mFirebaseAnalytics;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements MainContract.MvpView {


    private MainPresenter mainPresenter;

    private NewsFragment newsFragment = new NewsFragment();
    private FavoritesFragment favoritesFragment = new FavoritesFragment();
    private RandomFragment randomFragment = new RandomFragment();

    private FragmentManager fragmentManager;

    @BindView(R.id.search)
    ImageView search;

    @BindView(R.id.settings)
    ImageView settings;

    @BindView(R.id.main_bottom_navigation)
    BottomNavigationView bottomNavigationView;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);


        mainPresenter = new MainPresenter(this);

        fragmentManager = getActivity().getSupportFragmentManager();

        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, newsFragment)
                .commit();

        mainPresenter.initBottomNavigationBar(bottomNavigationView);

        mainPresenter.handleIntent(getActivity().getIntent());

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mainPresenter.handleChangeTheme(requestCode, resultCode, data);
    }

    @OnClick(R.id.search)
    public void searchOnClick(View view) {
        Toast.makeText(getContext(), "Opens up Search Activity (or Fragment now...)",
                Toast.LENGTH_LONG).show();
//        startActivity(new Intent(this, SearchActivity.class));
    }

    @OnClick(R.id.settings)
    public void settingsOnClick(View view) {

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "settings_click");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Open Settings");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "button");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        Toast.makeText(getContext(), "Opens up Settings Activity (or Fragment now...)",
                Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(this, SettingsActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivityForResult(intent, EXIT);
    }

    @Override
    public void openNews() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, newsFragment)
                .commit();
    }

    @Override
    public void openFavorite() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, favoritesFragment)
                .commit();
    }

    @Override
    public void openRandom() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, randomFragment)
                .commit();
    }

    @Override
    public void changeFragment(int id, Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, fragment)
                .commit();

        bottomNavigationView.setSelectedItemId(id);
    }

    @Override
    public FavoritesFragment getFavoriteFragment() {
        return favoritesFragment;
    }

    @Override
    public RandomFragment getRandomFragment() {
        return randomFragment;
    }

    @Override
    public NewsFragment getNewsFragment() {
        return newsFragment;
    }

}
