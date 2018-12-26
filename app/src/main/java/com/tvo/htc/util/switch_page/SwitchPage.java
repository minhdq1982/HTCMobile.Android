package com.tvo.htc.util.switch_page;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.android.lib.model.response.NotificationResponse;
import com.android.lib.model.response.SurveyResponse;

/**
 * Create by Ngocji on 12/4/2018
 **/


public interface SwitchPage {
    boolean handleMainSwitchPage(Context context, Intent intent, SurveyCallBack callBack);

    boolean handleSwitchLoginSuccess(Context context);

    void handleBackPressed(Context context);

    void handleBackSurvey(Context context);

    void onDestroy();

    void handleGoToSurveyMain(Context context, String token, SurveyResponse.SurveyType type, View ivBanner);

    void handleNotification(Context context, NotificationResponse.Notification item);

    boolean handleIntent(Context context, Intent intent);


    public interface SurveyCallBack {
        void onSurveyChecked();

        void onSurveyComplete();
    }

    public abstract class CallBackCheckSurvey {
        void onSuccess() {
        }

        void onError() {
        }

        void onFailed() {
        }

        void onErrorToken() {
        }
    }
}
