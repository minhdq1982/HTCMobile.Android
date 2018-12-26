package com.tvo.htc.view.main.buycar.allcar;

import android.content.Context;
import android.util.Pair;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.Car;
import com.android.lib.model.response.CarCategoryResponse;
import com.android.lib.model.response.CarFilterResponse;
import com.android.lib.model.response.CarFilterResponse.Data.CarItem;
import com.android.lib.model.response.CarsResponse;
import com.android.lib.model.response.DetailCarResponse;
import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class AllCarPresenter extends BasePresenter<AllCarContract.View> implements AllCarContract.Presenter {
    public AllCarPresenter(Context context) {
        super(context);
        httpRequestOption = new HTTPRequestOption(true, false);
        mPreviewCar = new ArrayList<>();
    }

    private List<CarCategoryResponse.Category> mListCategory;
    private List<String> mListOption;
    private List<Car> mListCar;
    private List<Object> mPreviewCar;
    private int countRequest = 0;
    private HTTPRequestOption httpRequestOption;
    private int preCategoryId = -1;

    private Pair<String, Boolean> pairPrice = new Pair<>("", null);
    private Pair<String, Boolean> pairCapacity = new Pair<>("", null);
    private Pair<String, Integer> pairGear = new Pair<>("", null);

    @Override
    public void loadData() {
        getView().showWait();
        loadCar();
        loadCategory();
    }

    @Override
    public void filterCar(boolean isRemove) {
        if (mListOption == null) {
            mListOption = new ArrayList<>();
        }
        mListOption.clear();
        if (pairPrice.first.isEmpty() && pairCapacity.first.isEmpty() && pairGear.first.isEmpty()) {
            getView().showDialog(getContext().getString(R.string.select_car_message_empty));
            return;
        }
        if (!pairPrice.first.isEmpty()) mListOption.add(pairPrice.first);
        if (!pairCapacity.first.isEmpty()) mListOption.add(pairCapacity.first);
        if (!pairGear.first.isEmpty()) mListOption.add(pairGear.first);

        if (isRemove) {
            getView().updateCarOption(mListOption);
        } else {
            getView().displayCarOption(mListOption);
        }

        RESTManager.getInstance().filterCars(pairPrice.second, pairCapacity.second, pairGear.second, new IRequestListener<CarFilterResponse>() {
            @Override
            public void onCompleted(CarFilterResponse data) {
                super.onCompleted(data);
                if (data != null && data.isSuccess()) {
                    mPreviewCar.clear();
                    mPreviewCar.addAll(data.getData().getItems());
                    getView().updateListPreviewCar(mPreviewCar);
                }
            }

            @Override
            public void onFailed(Throwable throwable) {
                super.onFailed(throwable);
                mPreviewCar.clear();
                getView().updateListPreviewCar(mPreviewCar);
            }
        });
    }


    @Override
    public void savePriceFilter(String price, boolean moreThan500M) {
        pairPrice = Pair.create(price, moreThan500M);
    }

    @Override
    public void saveCapacity(String capacity, boolean moreThan1500ml) {
        pairCapacity = Pair.create(capacity, moreThan1500ml);
    }

    @Override
    public void saveGear(String gear, int gearPosition) {
        pairGear = Pair.create(gear, gearPosition);
    }

    @Override
    public void handleRemoveOption(int position) {
        String option = mListOption.remove(position);

        if (option.equalsIgnoreCase(getContext().getString(R.string.select_car_filter_smaller))
                || option.equalsIgnoreCase(getContext().getString(R.string.select_car_filter_lager))) {
            pairPrice = Pair.create("", null);
            getView().removeOptionPrice();
        } else if (option.equalsIgnoreCase(getContext().getString(R.string.select_car_filter_bellow))
                || option.equalsIgnoreCase(getContext().getString(R.string.select_car_filter_above))) {
            pairCapacity = Pair.create("", null);
            getView().removeOptionCapacity();
        } else {
            pairGear = Pair.create("", null);
            getView().removeOptionGear();
        }
        if (mListOption.isEmpty()) {
            getView().hideFilterCar();
            handleRevertPreviewCar();
        } else {
            filterCar(true);
        }
    }

    @Override
    public void dataSearch(String price, String capacity, String gear, int position) {
    }

    @Override
    public void filterCarWithCategory(int idCategory) {
        preCategoryId = idCategory;
        mPreviewCar.clear();
        if (idCategory != -1) {
            for (Car item :
                    mListCar) {
                if (item.getCategoryId() == idCategory) {
                    mPreviewCar.add(item);
                }
            }
        } else {
            mPreviewCar.addAll(mListCar);
        }
        getView().updateListPreviewCar(mPreviewCar);
    }

    @Override
    public void handleRevertPreviewCar() {
        //todo handle revert preview card
        filterCarWithCategory(preCategoryId);
    }

    @Override
    public void handleClickedItem(Object item, boolean hasChooseVersion) {
        if (item instanceof Car) {
            if (hasChooseVersion) {
                if (Utils.hasCarVersion((Car) item)) {
                    if (Utils.hasMultiCarVersion((Car) item)) {
                        getView().showDialogSelectVersion(new DetailCarResponse((Car) item));
                    } else {
                        getView().gotoComparision(new DetailCarResponse((Car) item));
                    }
                } else {
                    getView().showErrorCarVersion();
                }
            } else {
                getView().gotoDetailCar(((Car) item).getId());
            }
        } else if (item instanceof CarItem) {
            if (hasChooseVersion) {
                List<Car.Version> versions = new ArrayList<>();
                versions.add(new Car.Version(((CarItem) item).getVersionName(), ((CarItem) item).getVersionId()));
                DetailCarResponse detailCarResponse = new DetailCarResponse(new Car(((CarItem) item).getCarName(), ((CarItem) item).getCarId(), versions));
                if (Utils.hasCarVersion(detailCarResponse.getData())) {
                    if (Utils.hasMultiCarVersion(detailCarResponse.getData())) {
                        getView().showDialogSelectVersion(detailCarResponse);
                    } else {
                        getView().gotoComparision(detailCarResponse);
                    }
                } else {
                    getView().showErrorCarVersion();
                }
            } else {
                getView().gotoDetailCar(((CarItem) item).getCarId());
            }
        }
    }

    private void loadCar() {
        countRequest++;
        if (SessionDataManager.getInstance().getCars() != null) {
            mListCar = SessionDataManager.getInstance().getCars();
            mPreviewCar.addAll(mListCar);
            onSuccess();
        } else {
            RESTManager.getInstance().getCars(new IRequestListener<CarsResponse>(httpRequestOption) {
                @Override
                public void onCompleted(CarsResponse data) {
                    super.onCompleted(data);
                    if (data != null && data.isSuccess() && data.getData() != null) {
                        mListCar = data.getData();
                        mPreviewCar.addAll(mListCar);
                        onSuccess();
                        SessionDataManager.getInstance().setCars(data.getData());
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                    onError();
                }
            });
        }
    }

    private void loadCategory() {
        countRequest++;
        if (SessionDataManager.getInstance().getListCategory() != null) {
            mListCategory = SessionDataManager.getInstance().getListCategory();
            onSuccess();
        } else {
            RESTManager.getInstance().getListCategory(new IRequestListener<CarCategoryResponse>(httpRequestOption) {
                @Override
                public void onCompleted(CarCategoryResponse data) {
                    super.onCompleted(data);
                    if (data != null && data.isSuccess() && data.getData() != null) {
                        mListCategory = new ArrayList<>(data.getData());
                        mListCategory.add(0, new CarCategoryResponse.Category(-1, getContext().getString(R.string.all_car_all)));
                        onSuccess();
                        SessionDataManager.getInstance().setListCategory(mListCategory);
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                    onError();
                }
            });
        }
    }

    private void onSuccess() {
        countRequest--;
        if (countRequest == 0) {
            getView().hideWait();
            getView().displayFilterCar(mListCategory);
            getView().displayListPreviewCar(mPreviewCar);
        }
    }

    private void onError() {
        countRequest = 0;
        getView().hideWait();
    }
}
