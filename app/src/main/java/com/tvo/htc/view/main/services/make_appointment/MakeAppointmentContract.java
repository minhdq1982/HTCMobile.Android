package com.tvo.htc.view.main.services.make_appointment;

import android.support.v4.app.Fragment;

import com.android.lib.model.response.ServicesListResponse;
import com.tvo.htc.model.WrapperDataMakeAppoint;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface MakeAppointmentContract {
    interface View extends BaseContract.View {
        void displayStepMakeAppointment(List<Fragment> listStep);

        void displayDataPreview(WrapperDataMakeAppoint wrapperDataMakeAppoint);

        void displayStep(int currentStep);

        void finishMakeAppointment();

        void displayConfirmFinishMakeAppointment();

        void hideBackButton();

        void showSuccessMakeAppointment();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData();

        void handlePreviousStep();

        void handleBackPressNav();

        void handleSaveAppointment();

        void handleNextStep(List<ServicesListResponse.Data> listSelectedService);

        void handleNextStep(String date,
                            String time,
                            String staffName,
                            int cityId,
                            String cityName,
                            int agencyId,
                            String agencyName);

        void handleNextStep(String honorifics,
                            String fullName,
                            String email,
                            String phone,
                            boolean isUpdateProfile,
                            String note,
                            int carId,
                            String carName,
                            String licensePlates,
                            int versionId,
                            String versionName,
                            boolean isAddNewCar);
    }
}
