package com.tvo.htc.view.main.notification.detail;

import android.content.Context;
import android.os.Bundle;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.NotificationDetailResponse;
import com.android.lib.model.response.NotificationResponse;
import com.tvo.htc.model.event.EventNumberNotification;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.view.BasePresenter;

import static com.tvo.htc.view.main.notification.detail.NotificationDetailFragment.EXTRAS_CONTENT;
import static com.tvo.htc.view.main.notification.detail.NotificationDetailFragment.EXTRAS_ID;
import static com.tvo.htc.view.main.notification.detail.NotificationDetailFragment.EXTRAS_TITLE;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class NotificationDetailPresenter extends BasePresenter<NotificationDetailContract.View> implements NotificationDetailContract.Presenter {

    public NotificationDetailPresenter(Context context) {
        super(context);
    }

    @Override
    public void loadData(Bundle args) {
        long id = args.getLong(EXTRAS_ID, -1L);
        String title = args.getString(EXTRAS_TITLE, "");
        String content = args.getString(EXTRAS_CONTENT, "");
        if (title.isEmpty() || content.isEmpty()) {
            RESTManager.getInstance().getNotificationDetail(id, new IRequestListener<NotificationDetailResponse>() {
                @Override
                public void onCompleted(NotificationDetailResponse data) {
                    super.onCompleted(data);
                    if (data.getData() != null) {
                        getView().displayData(data.getData().getTitle(), data.getData().getContent());
                        EventBusUtils.postEvent(new EventNumberNotification(data.getUnreadNotification()));
                    }
                }
            });
        } else {
            getView().displayData(title, content);
        }
    }
}
