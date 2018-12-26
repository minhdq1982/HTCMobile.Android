package com.tvo.htc.view;

import android.content.Context;

import java.util.List;

public class SimpleCustomTabScrollAdapter extends BaseCustomTabScrollAdapter<String> {

    public SimpleCustomTabScrollAdapter(Context context, List<String> items) {
        super(context, items);
    }

    protected String getName(String item) {
        return item;
    }
}
