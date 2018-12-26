package com.tvo.htc.view.main.support.guide.warranty_policy.detail;

import com.android.lib.model.response.WarrantyPolicyResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface WarrantyDetailContract {
    interface View extends BaseContract.View {
        void displayListHistory(List<WarrantyPolicyResponse.WarrantyPolicy> list);

        void showErrorMessage();

        void updateSelectedItem(int prePosition, int position);

        void showData(String link);

        void updateScaleWebView(int scalePercentInScreen);

    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData(List<WarrantyPolicyResponse.WarrantyPolicy> list, int position);

        void handleItemClicked(int position);

        void handleNextItem();

        void handleScaleUp();

        void handleScaleDown();
    }
}
