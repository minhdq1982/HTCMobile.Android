package com.tvo.htc.view.main.services.make_appointment.customer;

import com.android.lib.model.Car;
import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface InfoCustomerContract {
    interface View extends BaseContract.View {
        void displayHonorifics(List<String> listHonorific);

        void onNextStep(String honorifics,
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

        void displayInfo(String name, String emailAddress, String phoneNumber);

        void showErrorEmpty();

        void showErrorName();

        void showErrorEmail();

        void showErrorPhoneNumber();

        void displayListCar(List<Car> cars);

        void updateVersionByCar(List<Car.Version> version);

        void updateLicensePlates(String licensePlate);

        void showErrorLicensePlates();

        void updateSelectionCar(int i);

        void displayListUseCar(List<LoginResponse.Data.Car> cars);

        void disableProfileCar();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData();


        void filterVersion(int carId);

        void filterLicensePlates(int carId);

        void handleNextStep(String honorifix,
                            String name,
                            String email,
                            String phone,
                            boolean isUpdateProfile,
                            String note,
                            String licenseplates,
                            int useCarPoisition,
                            int selectCarPosition,
                            int versionPosition, boolean useProfileCar,
                            boolean isAddNewCar);
    }
}
