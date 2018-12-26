package com.tvo.htc.view.main.buycar.price;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.Car;
import com.android.lib.model.response.AreaResponse;
import com.android.lib.model.response.CarVersionResponse;
import com.android.lib.model.response.CarsResponse;
import com.android.lib.model.response.DetailCarResponse;
import com.android.lib.model.response.PriceAdviceResponse;
import com.tvo.htc.R;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class PriceAdvicePresenter extends BasePresenter<PriceAdviceContract.View> implements PriceAdviceContract.Presenter {

    private List<Car> item;

    public PriceAdvicePresenter(Context context) {
        super(context);
        item = SessionDataManager.getInstance().getCars();
    }

    @Override
    public void loadCar() {
        getView().displayCar(item);
    }

    @Override
    public void loadCarVersion(int position) {
        if(position >= 0) {
            getView().displayVersion(item.get(position).getVersion());
        } else
            getView().displayVersion(new ArrayList<>());
    }

    @Override
    public void loadArea() {
        RESTManager.getInstance().getListArea(new IRequestListener<AreaResponse>() {
            @Override
            public void onCompleted(AreaResponse data) {
                super.onCompleted(data);
                getView().displayArena(data.getData());
            }
        });
    }

    @Override
    public void loadPrice(int versionId, int areaId) {
        RESTManager.getInstance().getListPrice(versionId, areaId, new IRequestListener<PriceAdviceResponse>() {
            @Override
            public void onCompleted(PriceAdviceResponse data) {
                super.onCompleted(data);
                if(data.getData() != null) {
                    getView().displayPrice(data.getData());
                } else {
                    getView().emptyPrice();
                }
            }
        });
    }

}
