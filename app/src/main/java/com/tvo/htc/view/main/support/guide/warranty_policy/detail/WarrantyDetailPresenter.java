package com.tvo.htc.view.main.support.guide.warranty_policy.detail;

import android.content.Context;

import com.android.lib.model.response.WarrantyPolicyResponse.WarrantyPolicy;
import com.tvo.htc.view.BasePresenter;

import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class WarrantyDetailPresenter extends BasePresenter<WarrantyDetailContract.View> implements WarrantyDetailContract.Presenter {
    public WarrantyDetailPresenter(Context context) {
        super(context);
    }

    private List<WarrantyPolicy> mList;

    private int mPrePosition = 0;
    private int mScalePercentInScreen = 100;
    private int percentUnit = 20;

    @Override
    public void loadData(List<WarrantyPolicy> list, int position) {
        if (list == null) {
            getView().showErrorMessage();
        } else {
            getView().updateScaleWebView(mScalePercentInScreen);
            mList = list;
            mPrePosition = position;
            getView().displayListHistory(mList);
            getView().updateSelectedItem(0, mPrePosition);
            getView().showData(mList.get(position).getContent());
        }
    }

    @Override
    public void handleItemClicked(int position) {
        if (mPrePosition != -1) {
            mList.get(mPrePosition).setSelected(false);
        }
        mList.get(position).setSelected(true);
        getView().updateSelectedItem(mPrePosition, position);
        getView().showData(mList.get(position).getContent());
        mPrePosition = position;
    }

    @Override
    public void handleNextItem() {
        int position = mPrePosition + 1;
        if (position <= mList.size() - 1) {
            handleItemClicked(position);
        }
    }

    @Override
    public void handleScaleUp() {
        mScalePercentInScreen += percentUnit;
        getView().updateScaleWebView(mScalePercentInScreen);
    }

    @Override
    public void handleScaleDown() {
        mScalePercentInScreen -= percentUnit;
        getView().updateScaleWebView(mScalePercentInScreen);
    }
}
