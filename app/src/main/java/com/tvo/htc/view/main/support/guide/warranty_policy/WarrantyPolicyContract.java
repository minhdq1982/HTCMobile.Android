package com.tvo.htc.view.main.support.guide.warranty_policy;

import com.android.lib.model.response.WarrantyPolicyResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface WarrantyPolicyContract {
    interface View extends BaseContract.View {

        void displayListWarrantyPolicy(List<WarrantyPolicyResponse.WarrantyPolicy> items);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData();
    }
}
