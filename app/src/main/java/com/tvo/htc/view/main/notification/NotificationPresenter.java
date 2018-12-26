package com.tvo.htc.view.main.notification;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.NotificationDetailResponse;
import com.android.lib.model.response.NotificationResponse;
import com.tvo.htc.model.event.EventNumberNotification;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;

import static com.tvo.htc.util.AppConstants.START_OFFSET;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class NotificationPresenter extends BasePresenter<NotificationContract.View> implements NotificationContract.Presenter {
    public NotificationPresenter(Context context) {
        super(context);
    }

    private int mOffset = AppConstants.START_OFFSET;

    @Override
    public void loadData(int skipCount, boolean refreshing) {
        HTTPRequestOption httpRequestOption = new HTTPRequestOption(false, false);
        if (skipCount == 0 && !refreshing) {
            httpRequestOption = new HTTPRequestOption(true, true);
        }
        RESTManager.getInstance().getNotification(
                skipCount,
                AppConstants.PAGE_LIMIT,
                new IRequestListener<NotificationResponse>(httpRequestOption) {
                    @Override
                    public void onCompleted(NotificationResponse data) {
                        super.onCompleted(data);
                        if (skipCount == START_OFFSET) {
                            //softing();
                            if (getView() != null) {
                                getView().getData(data.getData());
                            }
                        } else {
                            if (getView() != null) {
                                getView().updateData(data.getData());
                            }
                        }
                        EventBusUtils.postEvent(new EventNumberNotification(data.getUnreadNotification()));
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        super.onFailed(throwable);

                        if (skipCount == START_OFFSET) {
                            if (getView() != null) {
                                getView().getData(new ArrayList<>());
                                getView().getDataFailed();
                            }
                        }
                    }
                }
        );
    }

}
