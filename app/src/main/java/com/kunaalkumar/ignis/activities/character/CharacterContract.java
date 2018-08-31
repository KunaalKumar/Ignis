package com.kunaalkumar.ignis.activities.character;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

public interface CharacterContract {

    interface MvpView {

        Toolbar getToolbar();

        AppBarLayout getAppBarLayout();

        FloatingActionButton getFab();

        ImageView getCharacterImageView();

        NestedScrollView getNestedScrollView();

        ProgressBar getContentProgressBar();

        /**
         * Layouts
         */
        CollapsingToolbarLayout getCollapsingToolbarLayout();

        LinearLayout getCharacterInfoParentLayout();

        CoordinatorLayout getCharacterParentLayout();

        MaterialCardView getCharacterDeckParentLayout();

        MaterialCardView getCharacterGeneralInformationParentLayout();

        MaterialCardView getComicInfoParentLayout();

        MaterialCardView getPowersInfoParentLayout();

        MaterialCardView getTeamsInfoParentLayout();

        /**
         * Titles
         */

        TextView getDeckTitleView();

        TextView getGeneralInfoTitleView();

        TextView getComicInfoTitleView();

        /**
         * Deck
         */

        TextView getCharacterDeckView();

        ImageView getCharacterDeckInfoView();

        /**
         * Real name
         */
        TextView getRealNameTitleView();

        TextView getRealNameView();

        /**
         * Aliases
         */
        TextView getAliasesTitleView();

        TextView getAliasesView();

        /**
         * Publisher
         */
        TextView getPublisherTitleView();

        MaterialButton getPublisherView();

        /**
         * Creators
         */
        TextView getCreatorsTitleView();

        RecyclerView getCreatorsRecyclerView();

        /**
         * Gender
         */
        TextView getGenderTitleView();

        TextView getGenderView();

        /**
         * Origin
         */
        TextView getOriginTitleView();

        MaterialButton getOriginView();

        /**
         * Birthday
         */
        TextView getBirthdayTitleView();

        TextView getBirthdayView();

        /**
         * Issues died
         */
        TextView getIssuesDiedTitleView();

        RecyclerView getIssuesDiedRecyclerView();

        /**
         * Powers
         */
        TextView getPowerTitleView();

        RecyclerView getPowerRecyclerView();

        /**
         * Teams
         */
        TextView getTeamsTitleView();

        RecyclerView getTeamsRecyclerView();

    }

    interface Presenter {

        void searchCall(Intent intent);

        void resetDrawableColors();

        void fabClicked();

    }
}
