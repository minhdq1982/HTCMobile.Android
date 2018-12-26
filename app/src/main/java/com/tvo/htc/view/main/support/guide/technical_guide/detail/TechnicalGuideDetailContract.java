package com.tvo.htc.view.main.support.guide.technical_guide.detail;

import com.android.lib.model.response.TechnicalGuideResponse;
import com.tvo.htc.view.BaseContract;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface TechnicalGuideDetailContract {
    interface View extends BaseContract.View {
        void showInfo(String imgLink, String title, String time);

        void showData(String content);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData(TechnicalGuideResponse.Item data);
    }
}
