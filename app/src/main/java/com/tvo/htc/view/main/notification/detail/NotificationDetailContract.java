package com.tvo.htc.view.main.notification.detail;

import android.os.Bundle;

import com.android.lib.model.response.NotificationResponse;
import com.tvo.htc.view.BaseContract;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface NotificationDetailContract {
    interface View extends BaseContract.View {
        void displayData(String title, String content);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData(Bundle args);
    }
}
