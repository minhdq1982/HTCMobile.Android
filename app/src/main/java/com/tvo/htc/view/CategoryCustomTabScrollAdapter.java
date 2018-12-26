package com.tvo.htc.view;

import android.content.Context;

import com.android.lib.model.response.CarCategoryResponse;

import java.util.List;

public class CategoryCustomTabScrollAdapter  extends BaseCustomTabScrollAdapter<CarCategoryResponse.Category>  {
    public CategoryCustomTabScrollAdapter(Context context, List<CarCategoryResponse.Category> items) {
        super(context, items);
    }

    @Override
    protected String getName(CarCategoryResponse.Category item) {
        return item.getName();
    }
}
