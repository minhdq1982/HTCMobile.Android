package com.tvo.htc.view.main.services;

import com.tvo.htc.view.BaseContract;

/**
 * Created by Ngocji on 3/21/2018.
 */

public interface ServicesContract {
    interface View extends BaseContract.View {

    }

    interface Presenter extends BaseContract.Presenter<View> {
        void saveAccepted();
    }
}
