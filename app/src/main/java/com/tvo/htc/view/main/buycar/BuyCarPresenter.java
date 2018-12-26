package com.tvo.htc.view.main.buycar;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.CarsResponse;
import com.android.lib.model.Car;
import com.google.gson.Gson;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ThinhNH on 3/20/2018.
 */

public class BuyCarPresenter extends BasePresenter<BuyCarContract.View> implements BuyCarContract.Presenter {

    private List<Car> mListCars;

    public BuyCarPresenter(Context context) {
        super(context);
        mListCars = SessionDataManager.getInstance().getCars();
    }

    private final int MAX_ITEM = 6;

    private List<Car> mListDisplay = new ArrayList<>();

    @Override
    public void loadListProduct() {
        if (mListCars == null || mListCars.isEmpty()) {
            RESTManager.getInstance().getCars(new IRequestListener<CarsResponse>() {
                @Override
                public void onCompleted(CarsResponse data) {
                    super.onCompleted(data);
                    if (data != null && data.isSuccess() && data.getData() != null) {
                        SessionDataManager.getInstance().setCars(data.getData());
                        mListCars = data.getData();
                        mListDisplay = new ArrayList<>(data.getData().subList(0, MAX_ITEM));
                        getView().displayListProduct(mListDisplay);
                    }
                }
            });
        } else {
            mListDisplay = new ArrayList<>(mListCars.subList(0, MAX_ITEM));
            getView().displayListProduct(mListDisplay);
        }
    }

    @Override
    public void handleSearch(String key) {
        if (hasDataResponse()) {
            if (key.isEmpty()) {
                getView().updateSearch(mListDisplay);
                getView().hideEmptyView();
            } else {
                List<Car> listSearch = new ArrayList<>();
                for (Car car : mListCars) {
                    if (car.getName().toLowerCase().contains(key.toLowerCase())) {
                        listSearch.add(car);
                    }
                }
                if (listSearch.isEmpty()) {
                    getView().showEmptyView();
                } else {
                    getView().hideEmptyView();
                }
                getView().updateSearch(listSearch);
            }
        }
    }

    private boolean hasDataResponse() {
        return mListCars!=null && !mListCars.isEmpty();
    }

    private List<Object> convertToListHeader(List<Car> listCar) {
        List<Object> listData = new ArrayList<>();
        if (!listCar.isEmpty()) {
            Collections.sort(listCar, (o1, o2) -> Integer.compare(o1.getCategoryId(), o2.getCategoryId()));
            int currentCateCheck = listCar.get(0).getCategoryId();
            for (Car car : listCar) {
                if (car.getCategoryId() == currentCateCheck) {
                    listData.add(car);
                } else {
                    currentCateCheck = car.getCategoryId();
                    listData.add(car);
                }
            }
        }
        return listData;
    }

}
