package com.tvo.htc.view.main.services.maintenance_level;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.AgenciesResponse.Agency;
import com.android.lib.model.response.CarVersionNameResponse;
import com.android.lib.model.response.CarVersionNameResponse.CarVersion;
import com.android.lib.model.response.MaintenanceDetailResponse;
import com.android.lib.model.response.MaintenanceDetailResponse.MaintenanceDetail;
import com.android.lib.model.response.MaintenanceDetailResponse.MaintenanceList;
import com.android.lib.model.response.MaintenanceLevelResponse;
import com.android.lib.model.response.MaintenanceLevelResponse.MaintenanceLevel;
import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class MaintenanceLevelPresenter extends BasePresenter<MaintenanceLevelContract.View> implements MaintenanceLevelContract.Presenter {

    private List<Agency> mAgencyList;
    private List<CarVersion> mCarVersion;
    private List<MaintenanceLevel> mMaintenanceList;

    private CarVersion hintCar = new CarVersion("Mẫu xe");
    private MaintenanceLevel hintLevel = new MaintenanceLevel("", "", "Cấp bảo dưỡng", -1);

    MaintenanceLevelPresenter(Context context) {
        super(context);

        mAgencyList = SessionDataManager.getInstance().getAgencies();
        mCarVersion = SessionDataManager.getInstance().getCarVersion();
        mMaintenanceList = SessionDataManager.getInstance().getMaintenanceLevels();

    }

    @Override
    public void loadData() {
        loadCarList();
        loadAgencyList();
        loadMaintenanceList();
    }

    private void loadMaintenanceList() {
        if (mMaintenanceList == null) {
            RESTManager.getInstance().getListMaintenanceLevel(new IRequestListener<MaintenanceLevelResponse>() {
                @Override
                public void onCompleted(MaintenanceLevelResponse data) {
                    super.onCompleted(data);
                    mMaintenanceList = new ArrayList<>();
                    mMaintenanceList.add(hintLevel);
                    mMaintenanceList.addAll(data.getData());
                    SessionDataManager.getInstance().setMaintenanceLevels(mMaintenanceList);
                    getView().displayMaintenanceLevelList(mMaintenanceList);
                }
            });
        } else {
            getView().displayMaintenanceLevelList(mMaintenanceList);
        }
    }

    @Override
    public void loadMaintenance(int carPosition, int agencyPosition, int levelPosition) {
        if (mCarVersion != null && mAgencyList != null & mMaintenanceList != null) {
            String carVersionName = mCarVersion.get(carPosition).getCarVersionName();
            int agencyID = mAgencyList.get(agencyPosition).getId();
            int levelID = mMaintenanceList.get(levelPosition).getId();

            Logger.d(carVersionName + "\n" + agencyID + "\n" + levelID);
            RESTManager.getInstance().postMaintenanceCarGetDetail(
                    carVersionName,
                    agencyID,
                    levelID,
                    new IRequestListener<MaintenanceDetailResponse>() {
                        @Override
                        public void onCompleted(MaintenanceDetailResponse data) {
                            super.onCompleted(data);
                            if (data.getData() != null) {
                                List<MaintenanceList> list = new ArrayList<>();
                                MaintenanceDetail detail = data.getData();

                                list.add(new MaintenanceList(getString(R.string.maintenance_list_getlaborcost), detail.getLaborCost()));
                                list.add(new MaintenanceList(getString(R.string.maintenance_list_getengineoil), detail.getEngineOil()));
                                list.add(new MaintenanceList(getString(R.string.maintenance_list_getboilflushwasher), detail.getBoilFlushWasher()));
                                list.add(new MaintenanceList(getString(R.string.maintenance_list_getengineoilfilter), detail.getEngineOilFilter()));
                                list.add(new MaintenanceList(getString(R.string.maintenance_list_getengineairfilter), detail.getEngineAirFilter()));
                                list.add(new MaintenanceList(getString(R.string.maintenance_list_getfuelfilter), detail.getFuelFilter()));
                                list.add(new MaintenanceList(getString(R.string.maintenance_list_getacairfilter), detail.getAcAirFilter()));
                                list.add(new MaintenanceList(getString(R.string.maintenance_list_getbugi), detail.getBugi()));
                                list.add(new MaintenanceList(getString(R.string.maintenance_list_getgearcaseoil), detail.getGearCaseOil()));
                                list.add(new MaintenanceList(getString(R.string.maintenance_list_getenginecoolant), detail.getEngineCoolant()));
                                list.add(new MaintenanceList(getString(R.string.maintenance_list_getbrakefluid), detail.getBrakeFluid()));
                                list.add(new MaintenanceList(getString(R.string.maintenance_list_getwindshieldwasherfluid), detail.getWindshieldWasherFluid()));

                                for (int i = list.size() - 1; i >= 0; i--) {
                                    if (list.get(i).getPrice() == 0) {
                                        list.remove(i);
                                    }
                                }

                                getView().displayTotal(detail.getTotalPrice() + "");
                                getView().displayItemsMaintenance(list);
                            } else {
                                getView().displayEmptyItems();
                            }
                        }
                    });
        }
    }

    private void loadCarList() {
        if (mCarVersion == null) {
            RESTManager.getInstance().postCarVersion(new IRequestListener<CarVersionNameResponse>() {
                @Override
                public void onCompleted(CarVersionNameResponse data) {
                    super.onCompleted(data);
                    mCarVersion = new ArrayList<>();
                    mCarVersion.add(hintCar);
                    mCarVersion.addAll(data.getData());
                    SessionDataManager.getInstance().setCarVersion(mCarVersion);
                    getView().displayCarCategoryList(mCarVersion);
                }
            });
        } else {
            getView().displayCarCategoryList(mCarVersion);
        }
    }

    private void loadAgencyList() {
        if (mAgencyList == null) {
            RESTManager.getInstance().getAgencies(new IRequestListener<AgenciesResponse>() {
                @Override
                public void onCompleted(AgenciesResponse data) {
                    super.onCompleted(data);
                    SessionDataManager.getInstance().setAgencies(data.getData());
                    mAgencyList = data.getData();
                    getView().displayAgencyList(data.getData());
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                }
            });
        } else {
            getView().displayAgencyList(mAgencyList);
        }
    }

    private String getString(int resource) {
        return getContext().getResources().getString(resource);
    }

}
