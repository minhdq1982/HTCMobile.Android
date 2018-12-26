package com.tvo.htc.view.main.support;

import com.tvo.htc.view.BaseContract;

/**
 * Created by Ngocji on 3/21/2018.
 */

public interface SupportContract {
    interface View extends BaseContract.View {

        void loadHotline(String hotline, String hotline_complain);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadHotline();
    }
}
