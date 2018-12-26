package com.tvo.htc.view.main.buycar.price;

import com.android.lib.model.Car;
import com.android.lib.model.response.AreaResponse;
import com.android.lib.model.response.CarsResponse;
import com.android.lib.model.response.DetailCarResponse;
import com.android.lib.model.response.PriceAdviceResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

public interface PriceAdviceContract {
    interface View extends BaseContract.View{
        void displayVersion(List<Car.Version> versions);
        void displayCar(List<Car> cars);
        void displayArena(List<AreaResponse.Data> item);

        void displayPrice(PriceAdviceResponse.Price data);

        void emptyPrice();
    }

    interface Presenter extends BaseContract.Presenter<View>{
        void loadCar();
        void loadCarVersion(int position);
        void loadArea();

        void loadPrice(int versionId, int areaId);
    }
}
