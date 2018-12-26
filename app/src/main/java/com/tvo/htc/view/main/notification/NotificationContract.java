package com.tvo.htc.view.main.notification;

import com.android.lib.model.response.NotificationResponse.Notification;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface NotificationContract {
    interface View extends BaseContract.View {

        void getData(List<Notification> data);

        void updateData(List<Notification> data);

        void getDataFailed();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData(int startOffset, boolean refreshing);
        }
}
