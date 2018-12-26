package com.tvo.htc.view.main.buycar.select_car;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.DialogHandler;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.CarsResponse;
import com.android.lib.model.response.SelectCarResponse;
import com.tvo.htc.R;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.view.BasePresenter;

import java.util.List;
import java.util.Map;

public class SelectCarPresenter extends BasePresenter<SelectCarContract.View> implements SelectCarContract.Presenter {
    private List<SelectCarResponse.Item> mListCarSelect;

    public SelectCarPresenter(Context context) {
        super(context);
    }

    @Override
    public void loadData() {
        if (SessionDataManager.getInstance().getListSelectCar() != null) {
            mListCarSelect = SessionDataManager.getInstance().getListSelectCar();
            getView().displayListQuestion(mListCarSelect);
        } else {
            RESTManager.getInstance().getListRecommendQuestion(new IRequestListener<SelectCarResponse>(new HTTPRequestOption(false, false)) {
                @Override
                public void onCompleted(SelectCarResponse data) {
                    super.onCompleted(data);
                    if (data.isSuccess() && data.getData() != null) {
                        mListCarSelect = data.getData();
                        getView().displayListQuestion(mListCarSelect);
                        SessionDataManager.getInstance().setListSelectCar(data.getData());
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    getView().showErrorLoadQuestion(DialogHandler.getInstance().createDisplayMsg(throwable, 0));
                }
            });
        }
    }

    @Override
    public void handleSearch(Map<Integer, String> listAnswerQuestion) {
        if (mListCarSelect == null || mListCarSelect.isEmpty()) {
            getView().showErrorLoadQuestion(getContext().getString(R.string.error_load_question));
            return;
        }
        if (listAnswerQuestion == null || listAnswerQuestion.isEmpty()) {
            getView().showErrorRequireAnswerQuestion();
            return;
        }
        StringBuilder builderQuestion = new StringBuilder();
        for (int i = 0; i < mListCarSelect.size(); i++) {
            int no = mListCarSelect.get(i).getQuestionNo();
            String data = listAnswerQuestion.get(no);
            if (data == null) {
                getView().showErrorRequireAnswerQuestion();
                return;
            } else {
                builderQuestion.append(data);
                if (i != mListCarSelect.size() - 1) {
                    builderQuestion.append("|");
                }
            }
        }
        RESTManager.getInstance().getListCarSelect(builderQuestion.toString(), new IRequestListener<CarsResponse>() {
            @Override
            public void onCompleted(CarsResponse data) {
                super.onCompleted(data);
                if (data != null && data.isSuccess() && data.getData() != null) {
                    getView().goToListCarRecommend(data.getData());
                }
            }
        });
    }
}

