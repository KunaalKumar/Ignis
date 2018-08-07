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

        FloatingActionButton getFab();

        ImageView getCharacterImageView();

        TextView getGeneralInformationTitle();

        /**
         * Layouts
         */
        CollapsingToolbarLayout getCollapsingToolbarLayout();

        LinearLayout getCharacterInfoParentLayout();

        CoordinatorLayout getCharacterParentLayout();
        MaterialCardView getCharacterDeckParentLayout();

        MaterialCardView getCharacterGeneralInformationParentLayout();

        /**
         * Deck
         */
        TextView getDeckTitleView();

        TextView getCharacterDeckView();

        ImageView getCharacterDeckInfoView();


        /**
         * Real name
         *
         * @return
         */
        TextView getRealNameTitleView();

        TextView getRealNameView();

    }

    interface Presenter {

        void searchCall(Intent intent);

        void resetDrawableColors();

        void fabClicked();

    }
}
