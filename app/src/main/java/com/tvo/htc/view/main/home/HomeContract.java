package com.tvo.htc.view.main.home;

import com.android.lib.model.Card;
import com.android.lib.model.response.GroupNews;
import com.android.lib.model.response.SurveyResponse;
import com.tvo.htc.model.event.EventUpdateListCard;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface HomeContract {
    interface View extends BaseContract.View {
        void displayListCard(List<Card> cards, boolean isShowEmptyList);

        void displayGroupNews(List<GroupNews> groupNews);

        void getSurveySuccess(SurveyResponse surveyResponse);

        void allRequestFinish();

        void displayHotline(String hotline);

        void showWait();

        void showGuest(boolean showHomeLogin);

        void showLogin();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadGroupNews(boolean refreshing);

        void loadMembershipCards(boolean refreshing);

        void loadSurvey();

        void handleUpdateListCard(EventUpdateListCard event, boolean isAdapterNull);

        void loadSetting();

        void loadData(boolean refreshing);

        void loadLogin();
    }
}
