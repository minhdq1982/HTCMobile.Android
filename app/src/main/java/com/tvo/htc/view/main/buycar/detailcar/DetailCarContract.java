package com.tvo.htc.view.main.buycar.detailcar;

import com.android.lib.model.Car;
import com.android.lib.model.response.DetailCarResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface DetailCarContract {
    interface View extends BaseContract.View {
        void displayInfoCar(String name, List<String> images);

        void displayTitleOption(List<String> title);

        void showErrorDetailCar(String message);

        void hideWebViewData();

        void showVehicleData();

        void hideVehicleData();

        void showWebViewData(String dataContent);

        void displaySpinnerVersion(List<Car.Version> version);

        void showErrorLinkDownloadCatalog();

        void showDownloadDialog(String url,String name);

        void showOpenCatalog();

        void showSelectCarVersion(DetailCarResponse detailCarResponse);

        void gotoCompare(DetailCarResponse detailCarResponse);

        void showErrorVersion();

        void displayTestDrive(boolean hasTestDrive);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData(int carId, String data);

        DetailCarResponse getResponse();

        void handleChangePreviewOption(int position);

        void handleDownloadCatalog();

        void savePathCatalog(String path);

        void handleCompare();
    }
}
