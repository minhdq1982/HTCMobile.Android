package com.tvo.htc.view;

/**
 * Created by ThinhNH on 3/20/2018.
 */

public class BaseContract {
    public interface View {

    }

    public interface Presenter<V extends View> {
        void attachView(V view);

        void detachView();
    }
}
