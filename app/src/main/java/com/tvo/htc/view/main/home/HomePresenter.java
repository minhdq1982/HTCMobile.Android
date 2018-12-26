package com.tvo.htc.view.main.home;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.Card;
import com.android.lib.model.response.CardListResponse;
import com.android.lib.model.response.GroupNews;
import com.android.lib.model.response.GroupNewsResponse;
import com.android.lib.model.response.LoginResponse;
import com.android.lib.model.response.SettingResponse;
import com.android.lib.model.response.SurveyResponse;
import com.android.lib.util.Logger;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.model.event.EventUpdateListCard;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinhNH on 3/20/2018.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private int numRequest = 0;
    private HTTPRequestOption httpRequestOption;

    private String fakeSurvey = "{data : {\"number\":0,\"token\":\"aca\",\"fullName\":\"\",\"phoneNumber\":\"\",\"email\":\"\",\"address\":\"\",\"dayPayment\":\"0001-01-01T00:00:00\",\"carName\":\"\",\"agencyName\":\"\",\"status\":0,\"dateAnswer\":\"\",\"daySent\":\"0001-01-01T00:00:00\",\"dayExpired\":\"\",\"surveyType\":0,\"id\":0}\n}";

    public HomePresenter(Context context) {
        super(context);
        httpRequestOption = new HTTPRequestOption(true, false);
    }

    @Override
    public void loadData(boolean refreshing) {
        if (!refreshing && !SessionDataManager.getInstance().isHasFirstLoadDataHome()) {
            if (hasViewNotNull()) getView().showWait();
        }
        loadLogin();
        loadMembershipCards(refreshing);
        loadGroupNews(refreshing);
        loadSetting();
        loadSurvey();
    }

    @Override
    public void loadLogin() {
        if (hasViewNotNull()) {
            if (Utils.hasLogin(getContext())) {
                getView().showLogin();
            } else {
                getView().showGuest(LocalDataManager.getInstance(getContext()).isShowHomeLogin());
            }
        }
    }

    @Override
    public void loadMembershipCards(boolean refreshing) {
        if (!Utils.hasLogin(getContext())) return;
        List<Card> cards = SessionDataManager.getInstance().getCards();
        if (cards != null && !refreshing) {
            if (hasViewNotNull()) {
                getView().displayListCard(cards, false);
            }
        } else {
            numRequest++;
            RESTManager.getInstance().getCardList(new IRequestListener<CardListResponse>(
                    httpRequestOption) {
                @Override
                public void onCompleted(CardListResponse data) {
                    super.onCompleted(data);
                    if (data != null && data.getData() != null && getView() != null) {
                        getView().displayListCard(data.getData(), false);
                        SessionDataManager.getInstance().setCards(data.getData());
                        onLoadSuccess();
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                    if (getView() != null) {
                        List<Card> cards = new ArrayList<>();
                        cards.add(new Card());
                        getView().displayListCard(cards, true);
                        onLoadSuccess();
                    }
                }
            });
        }
    }

    @Override
    public void loadGroupNews(boolean refreshing) {
        List<GroupNews> groupNews = SessionDataManager.getInstance().getGroupNews();
        if (groupNews != null && !groupNews.isEmpty() && !refreshing) {
            if (hasViewNotNull()) getView().displayGroupNews(groupNews);
        } else {
            numRequest++;
            RESTManager.getInstance().getGroupNews(new IRequestListener<GroupNewsResponse>(
                    new HTTPRequestOption(true, false)) {
                @Override
                public void onCompleted(GroupNewsResponse data) {
                    super.onCompleted(data);
                    if (hasViewNotNull() && data != null && data.getData() != null) {
                        SessionDataManager.getInstance().setGroupNews(data.getData());
                        getView().displayGroupNews(data.getData());
                        onLoadSuccess();
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                    onLoadSuccess();
                }
            });
        }
    }

    @Override
    public void loadSurvey() {
        LoginResponse response = LocalDataManager.getInstance(getContext()).getLoginResponse();
        if (response != null && response.getData() != null && response.getData().getPhoneNumber() != null) {
            RESTManager.getInstance().getSurvey(response.getData().getPhoneNumber(), new IRequestListener<SurveyResponse>(
                    new HTTPRequestOption(false, false)) {
                @Override
                public void onCompleted(SurveyResponse data) {
                    Logger.e("Call survey survey--> success");
                    super.onCompleted(data);
                    if (hasViewNotNull() && data != null) {
                        getView().getSurveySuccess(data);
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                    Logger.e("Call survey survey--> falield");
                }
            });
        }
    }

    @Override
    public void handleUpdateListCard(EventUpdateListCard event, boolean isAdapterNull) {
//        if (isAdapterNull) {
        boolean hasEmpty = event.getListCard().isEmpty();
        List list = new ArrayList(event.getListCard());
        if (hasEmpty) list.add(new Card());
        getView().displayListCard(list, hasEmpty);
        SessionDataManager.getInstance().setCards(list);
//        } else {
//            getView().updateMembershipCardsSuccess(event.getListCard());
//        }
    }

    @Override
    public void loadSetting() {
        SettingResponse.Setting setting = SessionDataManager.getInstance().getSetting();
        if (setting != null) {
            if (hasViewNotNull()) getView().displayHotline(setting.getHotline());
        } else {
            numRequest++;
            RESTManager.getInstance().getSetting(new IRequestListener<SettingResponse>(new HTTPRequestOption(false, false)) {
                @Override
                public void onCompleted(SettingResponse data) {
                    super.onCompleted(data);
                    if (data != null && data.getData() != null && hasViewNotNull()) {
                        LocalDataManager.getInstance(getContext()).saveSetting(data.getData());
                        SessionDataManager.getInstance().setSetting(data.getData());
                        getView().displayHotline(data.getData().getHotline());
                    }
                    onLoadSuccess();
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                    SettingResponse.Setting setting = LocalDataManager.getInstance(getContext()).getSetting();
                    if (setting != null && hasViewNotNull()) {
                        getView().displayHotline(setting.getHotline());
                        SessionDataManager.getInstance().setSetting(setting);
                    }
                    onLoadSuccess();
                }
            });
        }
    }

    private void onLoadSuccess() {
        numRequest--;
        if (hasViewNotNull() && numRequest == 0) {
            getView().allRequestFinish();
            SessionDataManager.getInstance().setHasFirstLoadDataHome(true);
        }
    }

    private boolean hasViewNotNull() {
        return getView() != null;
    }
}
