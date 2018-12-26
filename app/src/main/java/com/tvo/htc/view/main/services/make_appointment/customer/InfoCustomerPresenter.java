package com.tvo.htc.view.main.services.make_appointment.customer;

import android.content.Context;

import com.android.lib.model.Car;
import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.util.Utils;
import com.tvo.htc.util.ValidateFieldUtils;
import com.tvo.htc.view.BasePresenter;

import net.glxn.qrgen.core.scheme.EMail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tvo.htc.util.ValidateFieldUtils.Type.LICENSE_PLATES;
import static com.tvo.htc.util.ValidateFieldUtils.Type.MAIL;
import static com.tvo.htc.util.ValidateFieldUtils.Type.NAME;
import static com.tvo.htc.util.ValidateFieldUtils.Type.PHONE;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class InfoCustomerPresenter extends BasePresenter<InfoCustomerContract.View> implements InfoCustomerContract.Presenter {
    public InfoCustomerPresenter(Context context) {
        super(context);
        validate = new ValidateFieldUtils(context);
    }

    private List<Car> mListSelectCar;
    private List<LoginResponse.Data.Car> mListProfileCar;
    private ValidateFieldUtils validate;

    @Override
    public void loadData() {
        loadInfoProfile();
        loadHonorifics();
        loadListCar();
    }

    @Override
    public void filterVersion(int carId) {
        if (mListSelectCar != null && !mListSelectCar.isEmpty()) {
            for (int i = 0; i < mListSelectCar.size(); i++) {
                Car item = mListSelectCar.get(i);
                if (item.getId() == carId) {
                    getView().updateVersionByCar(item.getVersion());
                    return;
                }
            }
        }
    }

    @Override
    public void filterLicensePlates(int carId) {
        if (mListProfileCar != null && !mListProfileCar.isEmpty()) {
            for (int i = 0; i < mListProfileCar.size(); i++) {
                LoginResponse.Data.Car item = mListProfileCar.get(i);
                if (item.getCarID() == carId) {
                    getView().updateLicensePlates(item.getLicensePlate());
                    return;
                }
            }
        }
        //todo replace old data license plates
        getView().updateLicensePlates("");
    }


    @Override
    public void handleNextStep(String honorifix, String name, String email, String phone, boolean isUpdateProfile, String note, String licenseplates, int useCarPoisition, int selectCarPosition, int versionPosition, boolean useProfileCar, boolean isAddNewCar) {
        if (!validate.hasValidate(name, NAME)) return;
        if (!validate.hasValidate(email, MAIL)) return;
        if (!validate.hasValidate(phone, PHONE)) return;
        if (!useProfileCar && !validate.hasValidate(licenseplates, LICENSE_PLATES)) return;

        int versionId = -1;
        String versionName = "";
        int carId;
        String carName;

        if (useProfileCar) {
            carId = mListProfileCar.get(useCarPoisition).getCarID();
            carName = mListProfileCar.get(useCarPoisition).getCarName();
        } else {
            carId = mListSelectCar.get(selectCarPosition).getId();
            carName = mListSelectCar.get(selectCarPosition).getName();
            List<Car.Version> versions = mListSelectCar.get(selectCarPosition).getVersion();
            if (versions != null && !versions.isEmpty() && versionPosition != 0 && versionPosition < versions.size()) {
                versionId = versions.get(versionPosition).getId();
                versionName = versions.get(versionPosition).getVersionName();
            }
        }

        getView().onNextStep(honorifix, name, email, phone, isUpdateProfile, note, carId, carName, licenseplates, versionId, versionName, (!useProfileCar && isAddNewCar));
    }

    private void loadHonorifics() {
        getView().displayHonorifics(Arrays.asList(getContext().getResources().getStringArray(R.array.make_appoint_arr_honorific)));
    }

    private void loadInfoProfile() {
        LoginResponse response = LocalDataManager.getInstance(getContext()).getLoginResponse();
        if (response != null && response.getData() != null) {
            LoginResponse.Data data = response.getData();
            getView().displayInfo(data.getName(), data.getEmailAddress(), data.getPhoneNumber());
            if (hasCarDataProfile(response)) {
                mListProfileCar = response.getData().getCars();
            }
        }

        if (mListProfileCar == null || mListProfileCar.isEmpty()) {
            getView().disableProfileCar();
        } else {
            getView().displayListUseCar(mListProfileCar);
        }
    }

    private void loadListCar() {
        mListSelectCar = SessionDataManager.getInstance().getCars();
        if (mListSelectCar == null) mListSelectCar = new ArrayList<>();
        getView().displayListCar(mListSelectCar);
    }

    private boolean hasCarDataProfile(LoginResponse response) {
        return response != null && response.getData() != null && response.getData().getCars() != null && !response.getData().getCars().isEmpty();
    }
}
