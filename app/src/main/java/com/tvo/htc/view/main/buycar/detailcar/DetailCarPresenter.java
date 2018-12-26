package com.tvo.htc.view.main.buycar.detailcar;

import android.content.Context;

import com.android.lib.http.ErrorException;
import com.android.lib.model.Car;
import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.DetailCarResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.FileUtils;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BasePresenter;
import com.tvo.htc.view.dialog.SelectVersionDialog;
import com.tvo.htc.view.main.buycar.car_comparison.CarComparisonFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class DetailCarPresenter extends BasePresenter<DetailCarContract.View> implements DetailCarContract.Presenter {
    private DetailCarResponse mResponse;
    private List<String> mImages;
    private List<String> mTitleOption;

    public DetailCarPresenter(Context context) {
        super(context);
    }

    private int mCardId = -1;
    private String mPathSaveCatalog = "";

    @Override
    public void loadData(int carId, String data) {
        if (carId != -1) {
            mCardId = carId;
            RESTManager.getInstance().getDetailCar(carId, new IRequestListener<DetailCarResponse>() {
                @Override
                public void onCompleted(DetailCarResponse data) {
                    super.onCompleted(data);
                    if (data.isSuccess() && data.getData() != null) {
                        mResponse = data;
                        mImages = makeListImageCar(mResponse.getData().getImages());
                        mTitleOption = Utils.getArrayStringResId(getContext(), R.array.detail_car_title_option);
                        getView().displayTitleOption(mTitleOption);
                        getView().displayInfoCar(mResponse.getData().getName(), mImages);
                        getView().displaySpinnerVersion(mResponse.getData().getVersion());
                        getView().displayTestDrive(data.getData().isHasTestDrive());
                        checkExistsDownloadCatalog(mCardId, mResponse.getData().getCatalog());
                        handleChangePreviewOption(0);
                    } else {
                        getView().showErrorDetailCar(data.getMessage());
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    if (throwable instanceof ErrorException) {
                        ErrorException errorException = (ErrorException) throwable;
                        if (errorException.getResponseCode() == 200) {
                            if (errorException.getMessage() != null && !errorException.getMessage().isEmpty()) {
                                getView().showErrorDetailCar(errorException.getMessage());
                                return;
                            }
                        }
                    }
                    super.onFailed(throwable);
                }
            });
        } else {
            getView().showErrorDetailCar(getContext().getString(R.string.detail_car_error_load));
        }
    }

    private void checkExistsDownloadCatalog(int prefix, String link) {
        if (link != null && !link.isEmpty()) {
            if (FileUtils.hasCatalogExists(prefix, link)) {
                getView().showOpenCatalog();
                mPathSaveCatalog = FileUtils.getPathCatalog(prefix, link);
            }
        }
    }

    @Override
    public DetailCarResponse getResponse() {
        return mResponse;
    }

    @Override
    public void handleChangePreviewOption(int position) {
        String titleSelected = mTitleOption.get(position);
        if (titleSelected.equalsIgnoreCase(mTitleOption.get(mTitleOption.size() - 1))) {
            getView().hideWebViewData();
            getView().showVehicleData();
        } else {
            getView().hideVehicleData();
            getView().showWebViewData(getDataContent(position));
        }
    }

    @Override
    public void savePathCatalog(String path) {
        mPathSaveCatalog = path;
    }

    @Override
    public void handleDownloadCatalog() {
        if (mPathSaveCatalog.isEmpty()) {
            if (mResponse != null && mResponse.getData() != null && mResponse.getData().getCatalog() != null) {
                getView().showDownloadDialog(mResponse.getData().getCatalog(), FileUtils.getFileName(mCardId, mResponse.getData().getCatalog()));
            } else {
                getView().showErrorLinkDownloadCatalog();
            }
        } else {
            FileUtils.goToFolderCatalog(getContext());
        }
    }

    @Override
    public void handleCompare() {
        if (mResponse == null || mResponse.getData() == null) return;
        if (Utils.hasCarVersion(mResponse.getData())) {
            if (Utils.hasMultiCarVersion(mResponse.getData())) {
                getView().showSelectCarVersion(mResponse);
            } else {
                getView().gotoCompare(mResponse);
            }
        } else {
            getView().showErrorVersion();
        }
    }

    private String getDataContent(int position) {
        String s = "";
        if (mResponse != null && mResponse.getData() != null) {
            Car item = mResponse.getData();
            switch (position) {
                case 0:
                    s = item.getHighlights();
                    break;
                case 1:
                    s = item.getExterior();
                    break;
                case 2:
                    s = item.getFurniture();
                    break;
                case 3:
                    s = item.getOperation();
                    break;
                case 4:
                    s = item.getSafe();
                    break;
                case 5:
                    s = item.getConvenient();
                    break;
            }
        }
        return s;
    }

    private List<String> makeListImageCar(List<String> images) {
        List<String> resultImages = new ArrayList<>();
        for (String item : images) {
            resultImages.add(Utils.getImagePath(item));
        }
        return resultImages;
    }

}
