package com.tvo.htc.util.switch_page;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.NotificationDetailResponse;
import com.android.lib.model.response.NotificationResponse;
import com.android.lib.model.response.SimpleResponse;
import com.android.lib.model.response.SurveyResponse;
import com.android.lib.util.Logger;
import com.google.gson.Gson;
import com.tvo.htc.model.NotificationEntity;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.account.AccountActivity;
import com.tvo.htc.view.component.CpnTab;
import com.tvo.htc.view.main.MainActivity;
import com.tvo.htc.view.main.home.HomeFragment;
import com.tvo.htc.view.main.login.LoginFragment;
import com.tvo.htc.view.main.notification.detail.NotificationDetailFragment;
import com.tvo.htc.view.main.survey.SurveyFragment;

import static com.tvo.htc.util.AppConstants.EXTRAS_NOTIFICATION;
import static com.tvo.htc.util.AppConstants.EXTRAS_NOTIFICATION_CONTENT;

/**
 * Create by Ngocji on 12/4/2018
 **/


public class SwitchPageImpl implements SwitchPage {
    static SwitchPage instance;

    public static SwitchPage getInstance() {
        if (instance == null) {
            instance = new SwitchPageImpl();
        }
        return instance;
    }

    private Uri mDataDeepLink;
    private NotificationEntity mNotificationEntity;
    private SurveyCallBack surveyCallBack;
    private Handler uiLooper = new Handler(Looper.getMainLooper());

    @Override
    public void handleGoToSurveyMain(Context context, String token, SurveyResponse.SurveyType type, View ivBanner) {
        checkSurveyValid(context, token, new CallBackCheckSurvey() {
            @Override
            void onSuccess() {
                Fragment fragment = SurveyFragment.newInstance(type.ordinal(), token);
                ((SurveyFragment) fragment).setSurveyListener(() -> {
                    uiLooper.post(() -> ivBanner.setVisibility(View.GONE));
                });
                FragmentUtil.startFragmentNoTabbar(context, fragment, null);
            }

            @Override
            void onError() {
                uiLooper.post(() -> ivBanner.setVisibility(View.GONE));
            }

            @Override
            void onErrorToken() {
                uiLooper.post(() -> ivBanner.setVisibility(View.GONE));
            }

            @Override
            void onFailed() {
                uiLooper.post(() -> ivBanner.setVisibility(View.GONE));
            }
        });

    }

    @Override
    public boolean handleIntent(Context context, Intent intent) {
        if (!hasDataSwitch()) {
            getDataSwitch(intent);
        }
        if (hasDataSwitch()) {
            if (context instanceof AccountActivity) {
                goToMainActivity(context, intent);
                return true;
            } else if (context instanceof MainActivity && HomeFragment.isRunning) {
                if (hasDataSwitch()) {
                    if (hasDeepLink()) {
                        handleDeepLink(context);
                    } else if (hasNotification()) {
                        handleNotification(context);
                    }
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean handleMainSwitchPage(Context context, Intent intent, SurveyCallBack callBack) {
        if (!hasDataSwitch()) {
            getDataSwitch(intent);
        }
        if (hasDataSwitch()) {
            surveyCallBack = callBack;
            if (Utils.hasLogin(context)) {
                if (hasDeepLink()) {
                    handleDeepLink(context);
                } else {
                    handleNotification(context);
                }
                onDestroy();
            } else {
                goToLogin(context);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean handleSwitchLoginSuccess(Context context) {
        if (hasDataSwitch()) {
            goToHomeClearBackStack(context);
            if (hasDeepLink()) {
                handleDeepLink(context);
            } else {
                handleNotification(context);
            }
            onDestroy();
            return true;
        }
        return false;
    }

    @Override
    public void handleBackPressed(Context context) {
        goToHome(context, true);
    }

    @Override
    public void handleBackSurvey(Context context) {
        goToHome(context, true);
        returnSurveyError();
    }

    @Override
    public void handleNotification(Context context, NotificationResponse.Notification item) {
        if (!item.isRead()) {
            setReadNotifications(item.getId());
        }
        mNotificationEntity = new NotificationEntity();
        mNotificationEntity.setId((long) item.getId());
        mNotificationEntity.setNotificationType(item.getNotificationType().type);
        mNotificationEntity.setSurveyToken(item.getSurveyToken());
        mNotificationEntity.setSurveyType(item.getSurveyType());
        handleNotification(context);
    }

    @Override
    public void onDestroy() {
        mDataDeepLink = null;
        mNotificationEntity = null;
    }

    /*************** Handel *****************/
    private void handleDeepLink(Context context) {
        if (hasSurvey()) {
            checkSurveyValid(context, getTokenSurvey());
        } else {
            goToDetailNotification(context);
        }
    }

    private void handleNotification(Context context) {
        if (hasSurvey()) {
            checkSurveyValid(context, getTokenSurvey());
        } else {
            goToDetailNotification(context);
        }
        onDestroy();
    }

    /**
     * get token with uri
     *
     * @return
     */
    private String getTokenSurvey() {
        if (mDataDeepLink != null) {
            return mDataDeepLink.toString();
        } else if (mNotificationEntity != null) {
            return mNotificationEntity.getSurveyToken();
        }
        return null;
    }

    private void checkSurveyValid(Context context, String token) {
        checkSurveyValid(context, token, new CallBackCheckSurvey() {
            @Override
            public void onErrorToken() {
                returnSurveyError();
            }

            @Override
            public void onSuccess() {
                goToSurvey(context);
            }

            @Override
            public void onError() {
                goToHome(context, false);
                returnSurveyError();
            }

            @Override
            public void onFailed() {
                onDestroy();
                goToHome(context, false);
                returnSurveyError();
            }
        });
    }

    private void checkSurveyValid(Context context, String token, CallBackCheckSurvey callBack) {
        if (token == null || token.isEmpty()) {
            callBack.onErrorToken();
        } else {
            RESTManager.getInstance().checkSurveyInvalid(token, new IRequestListener<SimpleResponse>() {
                @Override
                public void onCompleted(SimpleResponse data) {
                    super.onCompleted(data);
                    if (data.isSuccess()) {
                        callBack.onSuccess();
                    } else {
                        callBack.onError();
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                    callBack.onFailed();
                }
            });
        }
    }

    //****************** check *************//
    private boolean hasDeepLink() {
        return mDataDeepLink != null;
    }

    private boolean hasNotification() {
        return mNotificationEntity != null;
    }

    private boolean hasSurvey() {
        if (mDataDeepLink != null) {
            return mDataDeepLink.toString().contains("survey");
        } else if (mNotificationEntity != null && mNotificationEntity.getSurveyToken() != null) {
            return !mNotificationEntity.getSurveyToken().isEmpty();
        }
        return false;
    }

    private boolean hasDataSwitch() {
        return mDataDeepLink != null || mNotificationEntity != null;
    }

    private boolean getDataSwitch(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (action != null) {
                switch (action) {
                    case Intent.ACTION_VIEW:
                        mDataDeepLink = intent.getData();
                        mNotificationEntity = intent.getParcelableExtra(EXTRAS_NOTIFICATION);
                        break;
                    case Intent.ACTION_MAIN:
                        String encode = intent.getStringExtra(EXTRAS_NOTIFICATION_CONTENT);
                        try {
                            mNotificationEntity = new Gson().fromJson(encode, NotificationEntity.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
            return hasDataSwitch();
        }
        return false;
    }

    private long getNotificationDetailId() {
        if (mNotificationEntity != null) {
            return mNotificationEntity.getId();
        }
        return -1;
    }

    private String getNotificationTitle() {
        return mNotificationEntity != null ? mNotificationEntity.getTitle() : "";
    }

    private String getNotificationContent() {
        return mNotificationEntity != null ? mNotificationEntity.getContent() : "";
    }

    private void setReadNotifications(long notificationId) {
        RESTManager.getInstance().setRead(notificationId, new IRequestListener<NotificationDetailResponse>(new HTTPRequestOption(false, false)) {
            @Override
            public void onCompleted(NotificationDetailResponse data) {
                super.onCompleted(data);

            }
        });
    }

    //****          go to method           **********//

    private void removeBackStack(Context context) {
        FragmentUtil.clearAllBackStack(context);
    }


    private void goToMainActivity(Context context, Intent intentReceiver) {
        ((AccountActivity) context).finish();
        Intent intent = new Intent(context, MainActivity.class);
        intent.setAction(intentReceiver.getAction());
        intent.putExtra(EXTRAS_NOTIFICATION, (Parcelable) intentReceiver.getParcelableExtra(EXTRAS_NOTIFICATION));
        intent.putExtra(EXTRAS_NOTIFICATION_CONTENT, intentReceiver.getStringExtra(EXTRAS_NOTIFICATION_CONTENT));
        context.startActivity(intent);
    }

    private void goToLogin(Context context) {
        FragmentUtil.startFragmentNoTabbar(context, LoginFragment.newInstance(), null);
    }

    private void goToSurvey(Context context) {
        SurveyFragment surveyFragment = SurveyFragment.newInstance(SurveyResponse.SurveyType.NEW_CAR.ordinal(), getTokenSurvey());
        surveyFragment.setSurveyListener(this::returnSurveyComplete);
        FragmentUtil.startFragmentNoTabbar(context, surveyFragment, null);
    }

    private void goToDetailNotification(Context context) {
        Logger.e("On go to detail--> ");
        FragmentUtil.startFragment(context, NotificationDetailFragment.newInstance(getNotificationDetailId(), getNotificationTitle(), getNotificationContent()), null);
    }

    private void goToHome(Context context, boolean hasRemoveCurrentFragment) {
        if (hasRemoveCurrentFragment) FragmentUtil.removeFragment(context);
        if (!HomeFragment.isRunning) {
            ((MainActivity) context).onTabMenuSelected(CpnTab.TabType.HOME);
        }
    }

    private void goToHomeClearBackStack(Context context) {
        removeBackStack(context);
        goToHome(context, false);
    }

    //****          call back method           **********//

    private void returnSurveyError() {
        if (surveyCallBack != null) {
            surveyCallBack.onSurveyChecked();
        }
    }

    private void returnSurveyComplete() {
        if (surveyCallBack != null) {
            surveyCallBack.onSurveyComplete();
        }
    }
}
