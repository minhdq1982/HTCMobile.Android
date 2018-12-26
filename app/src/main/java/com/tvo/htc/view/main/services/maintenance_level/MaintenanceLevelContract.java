package com.tvo.htc.view.main.services.maintenance_level;

import com.android.lib.model.Car;
import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.CarVersionNameResponse;
import com.android.lib.model.response.MaintenanceDetailResponse.MaintenanceList;
import com.android.lib.model.response.MaintenanceLevelResponse.MaintenanceLevel;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by Ngocji on 3/21/2018.
 */

public interface MaintenanceLevelContract {
    interface View extends BaseContract.View {
        void displayAgencyList(List<AgenciesResponse.Agency> mList);

        void displayCarCategoryList(List<CarVersionNameResponse.CarVersion> carList);

        void displayMaintenanceLevelList(List<MaintenanceLevel> levelList);

       void displayItemsMaintenance(List<MaintenanceList> maintenanceLists);

        void displayTotal(String total);

        void displayEmptyItems();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData();

        void loadMaintenance(int carPosition, int agencyPosition, int levelPosition);
    }
}
