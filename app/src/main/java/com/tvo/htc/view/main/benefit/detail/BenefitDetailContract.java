package com.tvo.htc.view.main.benefit.detail;

import android.os.Bundle;

import com.android.lib.model.Card;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface BenefitDetailContract {
    interface View extends BaseContract.View {
        void displayBenefitInfo(Card.Benefit benefit);

        void displayListCardApply(List<Card> cards);

        void displayErrorLoadBenefit();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void initData(Bundle arguments);
    }
}
