package com.tvo.htc.view.main.services.make_appointment.services;

import android.content.Context;

import com.android.lib.model.response.ServicesListResponse;
import com.google.gson.Gson;
import com.tvo.htc.R;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ChooseServicesPresenter extends BasePresenter<ChooseServicesContract.View> implements ChooseServicesContract.Presenter {
    public ChooseServicesPresenter(Context context) {
        super(context);
    }

    private String data = "{\"data\": [{ \"id\": 0,\"iconRes\": \"ic_service_manual\"}, {\"id\": 1,\"iconRes\": \"ic_service_oil\"}, {\"id\": 2, \"iconRes\": \"ic_service_wrench\"}, {\"id\": 3, \"iconRes\": \"ic_service_painting\"}, {\"id\": 4, \"iconRes\": \"ic_service_mechanic\"}]}";
    private ServicesListResponse servicesListResponse;
    private List<String> mListTile;

    @Override
    public void loadServiceList() {
        servicesListResponse = new Gson().fromJson(data, ServicesListResponse.class);
        mListTile = Arrays.asList(getContext().getResources().getStringArray(R.array.make_appointment_service_list_item));
        if (servicesListResponse != null && servicesListResponse.getData() != null && mListTile != null) {
            addNameToList(servicesListResponse.getData(), mListTile);
            getView().displayServiceList(servicesListResponse.getData());
        } else {
            getView().finish();
        }
    }

    @Override
    public void changeSelectService(int position) {
        ServicesListResponse.Data data = servicesListResponse.getData().get(position);
        data.setSelected(!data.isSelected());
        getView().notifyItemChanged(position);
    }

    @Override
    public void handleNext() {
        ArrayList<ServicesListResponse.Data> listSelected = new ArrayList<>();
        List<ServicesListResponse.Data> originalList = servicesListResponse.getData();
        for (ServicesListResponse.Data data : originalList) {
            if (data.isSelected()) listSelected.add(data);
        }
        if (listSelected.isEmpty()){
            getView().showErrorServicesEmpty();
        }else {
            getView().onNextStep(listSelected);
        }
    }

    private void addNameToList(List<ServicesListResponse.Data> data, List<String> listTitle) {
        for (ServicesListResponse.Data item : data) {
            item.setName(listTitle.get(item.getId()));
        }
    }
}
