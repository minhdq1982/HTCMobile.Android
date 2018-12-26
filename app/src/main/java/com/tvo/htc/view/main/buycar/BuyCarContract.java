package com.tvo.htc.view.main.buycar;

import com.android.lib.model.Car;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface BuyCarContract {
    interface View extends BaseContract.View {

        void displayListProduct(List<Car> listProduct);

        void updateSearch(List<Car> searchList);

        void hideEmptyView();

        void showEmptyView();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadListProduct();

        void handleSearch(String key);
    }
}
