package com.tvo.htc.view.main.buycar.car_comparison;

import android.content.Context;

import com.tvo.htc.model.CarComparisonSpec;
import com.tvo.htc.view.BaseCustomTabScrollAdapter;

import java.util.List;

public class CarSpecGroupAdapter extends BaseCustomTabScrollAdapter<CarComparisonSpec> {

    public CarSpecGroupAdapter(Context context, List<CarComparisonSpec> items) {
        super(context, items);
    }

    @Override
    protected String getName(CarComparisonSpec item) {
        return item.getName();
    }
}
