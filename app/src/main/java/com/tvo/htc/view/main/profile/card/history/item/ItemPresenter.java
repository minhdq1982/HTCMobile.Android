package com.tvo.htc.view.main.profile.card.history.item;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Pair;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.Card;
import com.android.lib.model.History;
import com.android.lib.model.response.HistoryEndowResponse;
import com.android.lib.model.response.HistoryPointResponse;
import com.android.lib.model.response.HistoryUsePointResponse;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.tvo.htc.util.AppConstants.START_OFFSET;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ItemPresenter extends BasePresenter<ItemContract.View> implements ItemContract.Presenter {
    private List<String> mTabList;
    private Pair<String, String> mPreviousTime;

    private int mCardId = -1;
    private History.Type mType;
    private Pair<String, String> mTime;

    private List<Card> mCards;

    private String mMembershipCode;
    private String mCardNumber;

    public ItemPresenter(Context context) {
        super(context);
        mCards = SessionDataManager.getInstance().getCards();
    }

    @Override
    public void loadData(History.Type type, int cardId, String time,String cardNumber, String membershipCode, boolean refreshing) {
        mCardId = cardId;
        mType = type;
        String[] times = time.split("\\*");
        mTime = new Pair<>(times[0], times[1]);
        mMembershipCode = membershipCode;
        mCardNumber = cardNumber;
        loadMoreData(0, refreshing);
    }


    @Override
    public void loadDate(boolean isStartDate) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener listener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            getView().getDate(calendar.getTime(), isStartDate);
        };
        new DatePickerDialog(getContext(), listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void changeTimeHistory(String timeStart, String timeEnd) {
        mPreviousTime = new Pair<>(timeStart, timeEnd);
        getView().startLoadData();
    }

    @Override
    public void loadMoreData(int skipCount, boolean refreshing) {
        switch (mType) {
            case GRADE_POINT:
                getGradePoint(skipCount, refreshing);
                break;
            case USE_POINT:
                getListUsePoint(skipCount, refreshing);
                break;
            case ENDOW:
                getListEndow(skipCount, refreshing);
                break;
        }
    }

    public void getGradePoint(int skipCount, boolean refreshing) {
        HTTPRequestOption httpRequestOption = new HTTPRequestOption(false, false);
        if (skipCount == 0 && !refreshing) {
            httpRequestOption = new HTTPRequestOption(true, true);
        }
        RESTManager.getInstance().getGradePoint(
                skipCount,
                AppConstants.PAGE_LIMIT,
                "",
                mCardNumber,
                mPreviousTime.first,
                mPreviousTime.second,
                new IRequestListener<HistoryPointResponse>(httpRequestOption) {
                    @Override
                    public void onCompleted(HistoryPointResponse data) {
                        super.onCompleted(data);
                        if (data.getData() != null && data.getData().getItems() != null) {
                            List<History> list = new ArrayList<>(data.getData().getItems());
                            if (skipCount == START_OFFSET) {
                                getView().displayListHistory(list);
                            } else {
                                getView().updateListLoadMore(list);
                            }
                        }
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        super.onFailed(throwable);
                        getView().displayListHistory(new ArrayList<>());
                        getView().getDataFailed();
                    }
                }
        );
    }

    public void getListUsePoint(int skipCount, boolean refreshing) {
        HTTPRequestOption httpRequestOption = new HTTPRequestOption(false, false);
        if (skipCount == 0 && !refreshing) {
            httpRequestOption = new HTTPRequestOption(true, true);
        }
        RESTManager.getInstance().getUsePoint(
                skipCount,
                AppConstants.PAGE_LIMIT,
                "",
                mCardNumber,
                mPreviousTime.first,
                mPreviousTime.second,
                new IRequestListener<HistoryUsePointResponse>(httpRequestOption) {
                    @Override
                    public void onCompleted(HistoryUsePointResponse data) {
                        super.onCompleted(data);
                        if (data.getData() != null && data.getData().getItems() != null) {
                            List<History> list = new ArrayList<>(data.getData().getItems());
                            if (skipCount == START_OFFSET) {
                                getView().displayListHistory(list);
                            } else {
                                getView().updateListLoadMore(list);
                            }
                        }
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        super.onFailed(throwable);
                        getView().displayListHistory(new ArrayList<>());
                        getView().getDataFailed();
                    }
                }
        );
    }

    public void getListEndow(int skipCount, boolean refreshing) {
        HTTPRequestOption httpRequestOption = new HTTPRequestOption(false, false);
        if (skipCount == 0 && !refreshing) {
            httpRequestOption = new HTTPRequestOption(true, true);
        }
        RESTManager.getInstance().getEndow(
                skipCount,
                AppConstants.PAGE_LIMIT,
                "",
                mCardNumber,
                "",
                "",
                mPreviousTime.first,
                mPreviousTime.second,
                new IRequestListener<HistoryEndowResponse>(httpRequestOption) {
                    @Override
                    public void onCompleted(HistoryEndowResponse data) {
                        super.onCompleted(data);
                        if (data.getData() != null && data.getData().getItems() != null) {
                            List<History> list = new ArrayList<>(data.getData().getItems());
                            if (skipCount == START_OFFSET) {
                                getView().displayListHistory(list);
                            } else {
                                getView().updateListLoadMore(list);
                            }
                        }
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        super.onFailed(throwable);
                        getView().displayListHistory(new ArrayList<>());
                        getView().getDataFailed();
                    }
                }
        );
    }
}
