package com.tvo.htc.view.main.buycar.car_comparison;

import com.android.lib.model.Car;
import com.android.lib.model.response.DetailCarResponse;
import com.tvo.htc.model.CarComparisonSpec;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface CarComparisonContract {
    interface View extends BaseContract.View {
        void displayData(DetailCarResponse carResponse, DetailCarResponse carResponse1,
                         List<CarComparisonSpec> specs);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getCarDetail(int carComparisonIndex, Car car);

        void wrapToDisplay(DetailCarResponse detailCarResponse1, DetailCarResponse detailCarResponse2);
    }
}
