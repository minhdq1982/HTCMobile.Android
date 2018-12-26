package com.tvo.htc.view;

import android.content.Context;

/**
 * Created by ThinhNH on 3/20/2018.
 */

public abstract class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V> {
    private Context context;
    private V view;

    public BasePresenter(Context context) {
        this.context = context;
    }

    public final Context getContext() {
        return context;
    }

    public final V getView() {
        return view;
    }

    @Override
    public final void attachView(V view) {
        this.view = view;
    }

    @Override
    public final void detachView() {
        this.view = null;
    }
}
