package com.tvo.htc.view.main.support.guide.guide_book.detail;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.GuildBookItemDetailResponse;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.view.BasePresenter;

import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class GuideBookDetailPresenter extends BasePresenter<GuideBookDetailContract.View> implements GuideBookDetailContract.Presenter {
    public GuideBookDetailPresenter(Context context) {
        super(context);
        httpRequestOption = new HTTPRequestOption(true, false);
    }

    private List<GuildBookItemDetailResponse.Item> mList;

    private int mPrePosition = 0;
    private int mScalePercentInScreen = 100;
    private int percentUnit = 20;
    private HTTPRequestOption httpRequestOption;


    @Override
    public void loadData(int id, String title) {
        mList = SessionDataManager.getInstance().getGuideBookItem(id);
        if (mList == null) {
            RESTManager.getInstance().getDetailGuideBook(id, new IRequestListener<GuildBookItemDetailResponse>(httpRequestOption) {
                @Override
                public void onCompleted(GuildBookItemDetailResponse data) {
                    super.onCompleted(data);
                    if (data.isSuccess() && data.getData() != null) {
                        mList = data.getData();
                        SessionDataManager.getInstance().setGuideBookItem(id, data.getData());
                        handleSuccess(title);
                    } else {
                        getView().showErrorMessage();
                    }
                }
            });
        } else {
            handleSuccess(title);
        }
    }

    @Override
    public void handleItemClicked(int position) {
        if (mPrePosition != -1) {
            mList.get(mPrePosition).setSelected(false);
        }
        mList.get(position).setSelected(true);
        getView().updateSelectedItem(mPrePosition, position);
        getView().showProgress();
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

    private void handleSuccess(String title) {
        getView().updateTitle(title);
        if (mList.isEmpty()) {
            getView().hideProgress();
            getView().showEmptyContent();
        } else {
            getView().hideEmptyContent();
            getView().displayListHistory(mList);
            getView().updateScaleWebView(mScalePercentInScreen);
            getView().showData(mList.get(0).getContent());
        }

    }
}
