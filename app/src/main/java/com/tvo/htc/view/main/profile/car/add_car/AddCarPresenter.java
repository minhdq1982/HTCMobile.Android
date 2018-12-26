package com.tvo.htc.view.main.profile.car.add_car;

import android.content.Context;
import android.os.Bundle;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.Car;
import com.android.lib.model.response.ActionCarResponse;
import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.CarsResponse;
import com.android.lib.model.response.LoginResponse;
import com.android.lib.util.LibUtils;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.model.event.EventCar;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BasePresenter;

import java.util.List;

/**
 * Created by Ngocji on 16/10/2018.
 */

public class AddCarPresenter extends BasePresenter<AddCarContract.View> implements AddCarContract.Presenter {
    public AddCarPresenter(Context context) {
        super(context);
        mHttpOption = new HTTPRequestOption(true, false);
        LoginResponse response = LocalDataManager.getInstance(getContext()).getLoginResponse();
        if (response != null && response.getData() != null) {
            mCustomerId = response.getData().getId();
        }
    }

    private LoginResponse.Data.Car mCar;
    private int mCustomerId;

    private final int countRequest = 2;
    private int currentRequest = 0;

    private List<Car> mListCar;
    private List<AgenciesResponse.Agency> mListAgency;

    private HTTPRequestOption mHttpOption;

    @Override
    public void initData(Bundle arguments) {
        if (arguments != null) {
            mCar = arguments.getParcelable(AddCarFragment.KEY_DATA);
        }
        if (mCar == null)
            getView().displayAddNewCar();
        else
            getView().displayEditCar();
    }

    @Override
    public void loadData() {
        getView().displayWaitDialog(true);
        loadListCar();
        loadListAgency();
    }

    private void loadListCar() {
        mListCar = SessionDataManager.getInstance().getCars();
        if (mListCar == null) {
            RESTManager.getInstance().getCars(new IRequestListener<CarsResponse>(mHttpOption) {
                @Override
                public void onCompleted(CarsResponse data) {
                    super.onCompleted(data);
                    if (data != null && data.getData() != null) {
                        mListCar = data.getData();
                        getView().displayListCar(mListCar);
                        SessionDataManager.getInstance().setCars(mListCar);
                        checkRequestDone();
                    }
                }
            });
        } else {
            getView().displayListCar(mListCar);
            checkRequestDone();
        }
    }

    private void loadListAgency() {
        mListAgency = SessionDataManager.getInstance().getAgencies();
        if (mListAgency == null) {
            RESTManager.getInstance().getAgencies(new IRequestListener<AgenciesResponse>(mHttpOption) {
                @Override
                public void onCompleted(AgenciesResponse data) {
                    super.onCompleted(data);
                    if (data != null && data.getData() != null) {
                        mListAgency = data.getData();
                        SessionDataManager.getInstance().setAgencies(mListAgency);
                        getView().displayListAgency(mListAgency);
                        checkRequestDone();
                    }
                }
            });
        } else {
            getView().displayListAgency(mListAgency);
            checkRequestDone();
        }
    }

    @Override
    public void handleSaveCar(int carId,
                              String VINNo,
                              String licensePlate) {
        // format string input
        //VINNo = Utils.normalizeString(VINNo, false);
        //licensePlate = Utils.normalizeString(licensePlate, true);
        //purchaseDate = Utils.normalizeString(purchaseDate, false);

        if (mCustomerId == -1 ||
                carId == -1 ||
                licensePlate.isEmpty()) {
            getView().displayMessage(getContext().getString(R.string.add_car_error_add_empty));
        } else {
            if (!VINNo.isEmpty() && (VINNo.length() != 17 || LibUtils.checkSpecialCharacters(VINNo))) {
                getView().displayMessage(getContext().getString(R.string.add_car_error_vin_number));
                return;
            }

            if (!Utils.isValidLicensePlates(licensePlate)) {
                getView().displayMessage(getContext().getString(R.string.add_car_error_licensePlate));
                return;
            }

//            if (!purchaseDate.isEmpty() && (!LibUtils.isValidTime(purchaseDate) || LibUtils.checkToday(purchaseDate))) {
//                getView().displayMessage(getContext().getString(R.string.add_car_error_invalid_date));
//                return;
//            }
            if (mCar != null) {
                RESTManager.getInstance().addCar(mCustomerId, mCar.getId(), carId, VINNo, licensePlate, new IRequestListener<ActionCarResponse>() {
                    @Override
                    public void onCompleted(ActionCarResponse data) {
                        super.onCompleted(data);
                        EventBusUtils.postEvent(new EventCar(data.getData(), false));
                        getView().displaySuccessEditCar();
                    }
                });
            } else {
                RESTManager.getInstance().addCar(mCustomerId, -1, carId, VINNo, licensePlate, new IRequestListener<ActionCarResponse>() {
                    @Override
                    public void onCompleted(ActionCarResponse data) {
                        super.onCompleted(data);
                        EventBusUtils.postEvent(new EventCar(data.getData(), true));
                        getView().displaySuccessAddCar();
                    }
                });
            }
        }
    }

    private void checkRequestDone() {
        currentRequest++;
        if (currentRequest == countRequest) {
            if (mCar != null) {
                getView().displayCarEdit(
                        getCarPosition(mCar.getCarID()),
                        mCar.getVINNo(),
                        mCar.getLicensePlate(),
                        getAgencyPosition(mCar.getAgencyId()),
                        getDisplayPurchaseDate(mCar.getPurchaseDate()));
            }
            getView().displayWaitDialog(false);
        }
    }

    private int getCarPosition(int carId) {
        if (mListCar != null) {
            for (int i = 0; i < mListCar.size(); i++) {
                if (carId == mListCar.get(i).getId()) {
                    return i;
                }
            }
        }
        return 0;
    }

    private int getAgencyPosition(int agencyId) {
        if (mListAgency != null) {
            for (int i = 0; i < mListAgency.size(); i++) {
                if (agencyId == mListAgency.get(i).getId()) {
                    return i;
                }
            }
        }
        return 0;
    }

    private String getDisplayPurchaseDate(String date) {
        if (date == null || date.isEmpty()) return "";
        String[] spDate = date.split("T")[0].split("-");
        return spDate[2] + "/" + spDate[1] + "/" + spDate[0];
    }
}
