package com.tvo.htc.view.account.term;

import com.android.lib.model.response.TermResponse;
import com.tvo.htc.view.BaseContract;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface TermContract {
    interface View extends BaseContract.View {
        void getDataSuccess(TermResponse response);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void saveAccepted();
        void loadData();
    }
}
