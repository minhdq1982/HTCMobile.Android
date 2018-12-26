package com.tvo.htc.view.main.benefit;

import com.android.lib.model.Card;
import com.android.lib.model.WrapperBenefit;
import com.tvo.htc.view.BaseContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface BenefitContract {
    interface View extends BaseContract.View {
        void displayListBenefit(List<WrapperBenefit> wrapperBenefits);

        void startBenefitDetail(ArrayList<Card> listCard, Card.Benefit benefit);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData();

        void handleStartDetailBenefit(WrapperBenefit.WrapperDetailBenefit item);
    }
}
