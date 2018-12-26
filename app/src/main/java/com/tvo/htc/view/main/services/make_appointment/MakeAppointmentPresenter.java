package com.tvo.htc.view.main.services.make_appointment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.CarsResponse;
import com.android.lib.model.response.CityResponse;
import com.android.lib.model.response.PostAppointmentResponse;
import com.android.lib.model.response.ServicesListResponse;
import com.google.gson.GsonBuilder;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.model.WrapperDataMakeAppoint;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.view.BasePresenter;
import com.tvo.htc.view.main.services.make_appointment.customer.InfoCustomerFragment;
import com.tvo.htc.view.main.services.make_appointment.info.InfoAppointFragment;
import com.tvo.htc.view.main.services.make_appointment.preview.PreviewFragment;
import com.tvo.htc.view.main.services.make_appointment.services.ChooseServicesFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class MakeAppointmentPresenter extends BasePresenter<MakeAppointmentContract.View> implements MakeAppointmentContract.Presenter {
    private ArrayList<Fragment> mListStepMake;
    private int currentStep = 0;
    private WrapperDataMakeAppoint wrapperDataMakeAppoint;
    private int mCountConnection = 0;

    private HTTPRequestOption httpRequestOption;

    public MakeAppointmentPresenter(Context context) {
        super(context);
        mListStepMake = new ArrayList<>();
        wrapperDataMakeAppoint = new WrapperDataMakeAppoint();
        httpRequestOption = new HTTPRequestOption(true, false);
    }

    @Override
    public void loadData() {
        loadListAgency();
        loadListCity();
        loadListCar();
        if (mCountConnection == 0) {
            initStep();
        }
    }


    @Override
    public void handleNextStep(String honorifics, String fullName, String email, String phone, boolean isUpdateProfile, String note, int carId, String carName, String licensePlates, int versionId, String versionName, boolean isAddNewCar) {
        wrapperDataMakeAppoint.setHonorifics(honorifics);
        wrapperDataMakeAppoint.setCustomerName(fullName);
        wrapperDataMakeAppoint.setCustomerEmail(email);
        wrapperDataMakeAppoint.setCustomerPhone(phone);
        wrapperDataMakeAppoint.setCustomerNote(note);
        wrapperDataMakeAppoint.setCarId(carId);
        wrapperDataMakeAppoint.setCarName(carName);
        wrapperDataMakeAppoint.setLicensePlates(licensePlates);
        wrapperDataMakeAppoint.setCarVersionId(versionId);
        wrapperDataMakeAppoint.setVersionName(versionName);
        wrapperDataMakeAppoint.setUpdateProfile(isUpdateProfile);
        wrapperDataMakeAppoint.setAddNewCar(isAddNewCar);
        nextStep();
    }

    @Override
    public void handleNextStep(List<ServicesListResponse.Data> listSelectedService) {
        wrapperDataMakeAppoint.setServiceName(toStringListService(listSelectedService));
        nextStep();
    }

    @Override
    public void handleNextStep(String date, String time, String staffName, int cityId, String cityName, int agencyId, String agencyName) {
        wrapperDataMakeAppoint.setDateAppointment(date + " " + time);
        wrapperDataMakeAppoint.setStaffName(staffName);
        wrapperDataMakeAppoint.setCityId(cityId);
        wrapperDataMakeAppoint.setCityName(cityName);
        wrapperDataMakeAppoint.setAgencyId(agencyId);
        wrapperDataMakeAppoint.setAgencyName(agencyName);
        nextStep();
    }

    private void nextStep() {
        if (currentStep < mListStepMake.size() - 1) {
            currentStep++;
            getView().displayStep(currentStep);
            if (currentStep == mListStepMake.size() - 1) {
                getView().hideBackButton();
                getView().displayDataPreview(wrapperDataMakeAppoint);
            }
        }
    }

    @Override
    public void handlePreviousStep() {
        if (currentStep > 0) {
            currentStep--;
            getView().displayStep(currentStep);
        }
    }

    @Override
    public void handleBackPressNav() {
        if (currentStep == 0) {
            getView().finishMakeAppointment();
        } else if (currentStep > 0 && currentStep < mListStepMake.size() - 1) {
            handlePreviousStep();
        } else {
            getView().displayConfirmFinishMakeAppointment();
        }
    }

    @Override
    public void handleSaveAppointment() {
        //todo check if use profile car
        if (wrapperDataMakeAppoint.isUpdateProfile()) {
            wrapperDataMakeAppoint.setAddNewCar(false);
        }
        String encode = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(wrapperDataMakeAppoint);
        RESTManager.getInstance().makeAppointment(encode, new IRequestListener<PostAppointmentResponse>() {
            @Override
            public void onCompleted(PostAppointmentResponse data) {
                super.onCompleted(data);
                if (data != null && data.isSuccess()) {
                    EventBusUtils.postEvent(data);
                    getView().showSuccessMakeAppointment();
                    if (wrapperDataMakeAppoint.isUpdateProfile()) {
                        LocalDataManager.getInstance(getContext()).updateProfile(wrapperDataMakeAppoint.getCustomerName(), wrapperDataMakeAppoint.getCustomerEmail());
                    }
                    if (wrapperDataMakeAppoint.isAddNewCar()) {
                        LocalDataManager.getInstance(getContext()).updateCar(wrapperDataMakeAppoint.getCarId(), wrapperDataMakeAppoint.getCarName(), wrapperDataMakeAppoint.getLicensePlates());
                    }
                }
            }
        });
    }

    private void handleSuccessLoadData() {
        mCountConnection--;
        if (mCountConnection == 0) {
            initStep();
        }
    }

    private void initStep() {
        mListStepMake.add(ChooseServicesFragment.newInstance());
        mListStepMake.add(InfoCustomerFragment.newInstance());
        mListStepMake.add(InfoAppointFragment.newInstance());
        mListStepMake.add(PreviewFragment.newInstance());
        getView().displayStepMakeAppointment(mListStepMake);
    }

    private void resetCountConnectionData() {
        mCountConnection = 0;
    }

    private void loadListCity() {
        if (SessionDataManager.getInstance().getCities() == null) {
            mCountConnection++;
            RESTManager.getInstance().getCities(new IRequestListener<CityResponse>(httpRequestOption) {
                @Override
                public void onCompleted(CityResponse data) {
                    super.onCompleted(data);
                    if (data != null && data.getData() != null) {
                        SessionDataManager.getInstance().setCities(data.getData());
                        handleSuccessLoadData();
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                    resetCountConnectionData();
                }
            });
        }
    }

    private void loadListAgency() {
        if (SessionDataManager.getInstance().getAgencies() == null) {
            mCountConnection++;
            RESTManager.getInstance().getAgencies(new IRequestListener<AgenciesResponse>(httpRequestOption) {
                @Override
                public void onCompleted(AgenciesResponse data) {
                    super.onCompleted(data);
                    if (data != null && data.getData() != null) {
                        SessionDataManager.getInstance().setAgencies(data.getData());
                        handleSuccessLoadData();
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                    resetCountConnectionData();
                }
            });
        }
    }

    private void loadListCar() {
        if (SessionDataManager.getInstance().getCars() == null) {
            mCountConnection++;
            RESTManager.getInstance().getCars(true, new IRequestListener<CarsResponse>(httpRequestOption) {
                @Override
                public void onCompleted(CarsResponse data) {
                    super.onCompleted(data);
                    if (data != null && data.getData() != null) {
                        SessionDataManager.getInstance().setCars(data.getData());
                        handleSuccessLoadData();
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                    resetCountConnectionData();
                }
            });
        }


    }

    private String toStringListService(List<ServicesListResponse.Data> list) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i).getName());
            if (i != list.size() - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
