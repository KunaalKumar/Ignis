package com.kunaalkumar.ignis.activities.main;

/**
 * Responsible for handling actions from the view and updating the UI as required
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.MvpView view;

    public MainPresenter(MainContract.MvpView view) {
        this.view = view;
    }
}
