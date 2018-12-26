package com.tvo.htc.view.main.buycar.allcar;

import com.android.lib.model.response.CarCategoryResponse;
import com.android.lib.model.response.DetailCarResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

public interface AllCarContract {
    interface View extends BaseContract.View {
        void displayListPreviewCar(List<Object> cars);

        void displayFilterCar(List<CarCategoryResponse.Category> categories);

        void displayCarOption(List<String> options);

        void showDialog(String message);

        void hideWait();

        void showWait();

        void updateListPreviewCar(List<Object> cars);

        void updateCarOption(List<String> options);

        void hideFilterCar();

        void removeOptionPrice();

        void removeOptionCapacity();

        void removeOptionGear();

        void showDialogSelectVersion(DetailCarResponse detailCarResponse);

        void gotoDetailCar(int id);

        void gotoComparision(DetailCarResponse detailCarResponse);

        void showErrorCarVersion();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData();

        void filterCar(boolean isRemove);

        void dataSearch(String price, String capacity, String gear, int position);

        void filterCarWithCategory(int idCategory);

        void handleRevertPreviewCar();

        void savePriceFilter(String price, boolean moreThan500M);

        void saveCapacity(String capacity, boolean moreThan1500ml);

        void saveGear(String gear, int gearPosition);

        void handleRemoveOption(int position);

        void handleClickedItem(Object itemAtPosition, boolean hasChooseVersion);
    }
}
