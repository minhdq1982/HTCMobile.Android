package com.tvo.htc.view.main.buycar.car_comparison;

import android.content.Context;
import android.util.Pair;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.Car;
import com.android.lib.model.response.DetailCarResponse;
import com.tvo.htc.model.CarComparisonSpec;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class CarComparisonPresenter extends BasePresenter<CarComparisonContract.View>
        implements CarComparisonContract.Presenter {
    private DetailCarResponse mDetailCarResponse1;
    private DetailCarResponse mDetailCarResponse2;

    public CarComparisonPresenter(Context context) {
        super(context);
    }

    @Override
    public void getCarDetail(int carComparisonIndex, Car car) {
        RESTManager.getInstance().getCar(car.getId(), new IRequestListener<DetailCarResponse>() {
            @Override
            public void onCompleted(DetailCarResponse data) {
                super.onCompleted(data);

                data = Utils.removeOtherVersion(data, car.getVersion().get(0).getId());
                if (carComparisonIndex == 1) {
                    mDetailCarResponse1 = data;
                } else {
                    mDetailCarResponse2 = data;
                }

                List<CarComparisonSpec> carComparisonSpecs = wrapToDisplayNoUpdateResponse(null, mDetailCarResponse1, mDetailCarResponse2, false);
                carComparisonSpecs = wrapToDisplayNoUpdateResponse(carComparisonSpecs, mDetailCarResponse2, mDetailCarResponse1, true);
                getView().displayData(mDetailCarResponse1, mDetailCarResponse2, carComparisonSpecs);
            }
        });
    }

    @Override
    public void wrapToDisplay(DetailCarResponse carResponse1, DetailCarResponse carResponse2) {
        mDetailCarResponse1 = carResponse1;
        mDetailCarResponse2 = carResponse2;
        List<CarComparisonSpec> carComparisonSpecs = wrapToDisplayNoUpdateResponse(null, carResponse1, carResponse2, false);
        getView().displayData(mDetailCarResponse1, mDetailCarResponse2, carComparisonSpecs);
    }

    public List<CarComparisonSpec> wrapToDisplayNoUpdateResponse(
            List<CarComparisonSpec> carComparisonSpecs, DetailCarResponse carResponse1,
            DetailCarResponse carResponse2, boolean isReverse) {
        if (carComparisonSpecs == null) {
            carComparisonSpecs = new ArrayList<>();
        }

        for (int i = 0; i < carResponse1.getData().getVersion().get(0).getSpec().size(); i++) {
            Car.Version.Spec spec = carResponse1.getData().getVersion().get(0).getSpec().get(i);

            boolean isExistCarComparisonSpec = false;
            CarComparisonSpec carComparisonSpec = getCarComparisonSpec(spec, carComparisonSpecs);
            List<CarComparisonSpec.Item> itemsCarComparisonSpec;
            if (carComparisonSpec == null) {
                carComparisonSpec = new CarComparisonSpec();
                carComparisonSpec.setName(spec.getName());

                itemsCarComparisonSpec = new ArrayList<>();
            } else {
                isExistCarComparisonSpec = true;
                itemsCarComparisonSpec = carComparisonSpec.getItems();
            }
            for (int j = 0; j < spec.getDetails().size(); j++) {
                Car.Version.Spec.Detail specDetail = spec.getDetails().get(j);
                if (!isExistSpecDetail(spec, specDetail, carComparisonSpecs)) {
                    CarComparisonSpec.Item itemCarComparisonSpec = new CarComparisonSpec.Item();
                    itemCarComparisonSpec.setName(specDetail.getName());

                    Car.Version.Spec.Detail specDetail2 = getSpecDetail(spec, specDetail, carResponse2);
                    String value2 = "";
                    if (specDetail2 != null) {
                        value2 = specDetail2.getValue();
                    }
                    if (isReverse) {
                        itemCarComparisonSpec.setValue(new Pair<>(value2, specDetail.getValue()));
                    } else {
                        itemCarComparisonSpec.setValue(new Pair<>(specDetail.getValue(), value2));
                    }
                    itemsCarComparisonSpec.add(itemCarComparisonSpec);
                }
            }

            carComparisonSpec.setItems(itemsCarComparisonSpec);
            if (!isExistCarComparisonSpec) {
                carComparisonSpecs.add(carComparisonSpec);
            }
        }

        return carComparisonSpecs;
    }

    private CarComparisonSpec getCarComparisonSpec(Car.Version.Spec spec, List<CarComparisonSpec> carComparisonSpecs) {
        if (carComparisonSpecs == null || carComparisonSpecs.isEmpty()) {
            return null;
        }
        for (CarComparisonSpec carComparisonSpec : carComparisonSpecs) {
            if (carComparisonSpec.getName().equals(spec.getName())) {
                return carComparisonSpec;
            }
        }

        return null;
    }

    private boolean isExistSpecDetail(Car.Version.Spec spec, Car.Version.Spec.Detail detail,
                                      List<CarComparisonSpec> carComparisonSpecs) {
        if (carComparisonSpecs == null || carComparisonSpecs.isEmpty()) {
            return false;
        }
        for (CarComparisonSpec carComparisonSpec : carComparisonSpecs) {
            // Check same spec
            if (carComparisonSpec.getName() != null && carComparisonSpec.getName().equals(spec.getName())) {
                if (carComparisonSpec.getItems() != null && !carComparisonSpec.getItems().isEmpty()) {
                    for (CarComparisonSpec.Item item : carComparisonSpec.getItems()) {
                        // Check same spec detail
                        if (item.getName().equals(detail.getName())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private Car.Version.Spec.Detail getSpecDetail(Car.Version.Spec spec,
                                                  Car.Version.Spec.Detail specDetail, DetailCarResponse carResponse) {
        if (specDetail == null || carResponse == null || carResponse.getData() == null
                || carResponse.getData().getVersion() == null
                || carResponse.getData().getVersion().isEmpty()
                || carResponse.getData().getVersion().get(0).getSpec() == null
                || carResponse.getData().getVersion().get(0).getSpec().isEmpty()
                || carResponse.getData().getVersion().get(0).getSpec().get(0).getDetails() == null
                || carResponse.getData().getVersion().get(0).getSpec().get(0).getDetails().isEmpty()) {
            return null;
        }

        for (int i = 0; i < carResponse.getData().getVersion().get(0).getSpec().size(); i++) {
            Car.Version.Spec specItem = carResponse.getData().getVersion().get(0).getSpec().get(i);
            // Check same spec
            if (specItem.getName() != null && specItem.getName().equals(spec.getName())) {
                for (int j = 0; j < specItem.getDetails().size(); j++) {
                    Car.Version.Spec.Detail specDetail1 = specItem.getDetails().get(j);

                    // Check same spec detail
                    if (specDetail1.getName().equals(specDetail.getName())) {
                        return specDetail1;
                    }
                }
            }
        }

        return null;
    }
}
