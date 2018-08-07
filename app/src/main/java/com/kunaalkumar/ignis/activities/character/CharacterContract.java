package com.kunaalkumar.ignis.activities.character;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public interface CharacterContract {

    interface MvpView {

        Toolbar getToolbar();

        AppBarLayout getAppBarLayout();

        CollapsingToolbarLayout getCollapsingToolbarLayout();

        FloatingActionButton getFab();

        ImageView getCharacterImage();

        // Deck
        MaterialCardView getCharacterDeckParentLayout();

        TextView getCharacterDeck();

        ImageView getCharacterDeckInfo();

        LinearLayout getCharacterInfoParent();

        CoordinatorLayout getCharacterParent();

    }

    interface Presenter {

        void searchCall(Intent intent);

        void resetDrawableColors();

        void fabClicked();

    }
}
