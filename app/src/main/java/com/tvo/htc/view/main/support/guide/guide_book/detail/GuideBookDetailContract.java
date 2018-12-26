package com.tvo.htc.view.main.support.guide.guide_book.detail;

import com.android.lib.model.response.GuildBookItemDetailResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface GuideBookDetailContract {
    interface View extends BaseContract.View {
        void displayListHistory(List<GuildBookItemDetailResponse.Item> list);

        void showErrorMessage();

        void updateSelectedItem(int mPrePosition, int position);

        void showData(String link);

        void updateScaleWebView(int scalePercentInScreen);

        void updateTitle(String title);

        void showProgress();

        void hideProgress();

        void showEmptyContent();

        void hideEmptyContent();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData(int id, String title);

        void handleItemClicked(int position);

        void handleNextItem();

        void handleScaleUp();

        void handleScaleDown();
    }
}
