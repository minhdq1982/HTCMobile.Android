package com.tvo.htc.view.main.services.make_appointment.services;

import com.android.lib.model.response.ServicesListResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface ChooseServicesContract {
    interface View extends BaseContract.View {
        void displayServiceList(List<ServicesListResponse.Data> services);

        void notifyItemChanged(int position);

        void onNextStep(List<ServicesListResponse.Data> listSelected);

        void finish();

        void showErrorServicesEmpty();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadServiceList();
        void changeSelectService(int position);
        void handleNext();
    }
}
