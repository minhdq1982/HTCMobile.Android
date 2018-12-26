package com.tvo.htc.view.main.benefit.detail;

import android.content.Context;
import android.os.Bundle;

import com.android.lib.model.Card;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;

import static com.tvo.htc.view.main.benefit.detail.BenefitDetailFragment.KEY_DATA_BENEFIT;
import static com.tvo.htc.view.main.benefit.detail.BenefitDetailFragment.KEY_DATA_CARDS;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class BenefitDetailPresenter extends BasePresenter<BenefitDetailContract.View> implements BenefitDetailContract.Presenter {
    public BenefitDetailPresenter(Context context) {
        super(context);
    }

    @Override
    public void initData(Bundle arguments) {
        if (arguments != null) {
            ArrayList<Card> list = arguments.getParcelableArrayList(KEY_DATA_CARDS);
            Card.Benefit benefit = arguments.getParcelable(KEY_DATA_BENEFIT);
            if (list != null && benefit != null) {
                getView().displayBenefitInfo(benefit);
                getView().displayListCardApply(list);
            } else {
                getView().displayErrorLoadBenefit();
            }
        }
    }
}
