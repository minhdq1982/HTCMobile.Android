package com.tvo.htc.view.main.buycar.select_car;

import com.android.lib.model.Car;
import com.android.lib.model.response.CarsResponse;
import com.android.lib.model.response.SelectCarResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;
import java.util.Map;

public interface SelectCarContract {
    interface View extends BaseContract.View {
        void displayListQuestion(List<SelectCarResponse.Item> mList);

        void showErrorRequireAnswerQuestion();

        void goToListCarRecommend(List<Car> listCar);

        void showErrorLoadQuestion(String displayMsg);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData();

        void handleSearch(Map<Integer, String> listAnswerQuestion);
    }
}
