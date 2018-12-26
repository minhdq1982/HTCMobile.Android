package com.tvo.htc.view.main.support.guide.technical_guide;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.TechnicalGuideResponse;
import com.android.lib.model.response.TechnicalGuideResponse.Item;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.tvo.htc.util.AppConstants.PAGE_LIMIT;
import static com.tvo.htc.util.AppConstants.START_OFFSET;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class TechnicalGuidePresenter extends BasePresenter<TechnicalGuideContract.View> implements TechnicalGuideContract.Presenter {
    public TechnicalGuidePresenter(Context context) {
        super(context);
        httpRequestOption = new HTTPRequestOption(true, false);

        mCurrentList = new ArrayList<>();
        if (SessionDataManager.getInstance().getTechnicalList() != null) {
            mCurrentList.addAll(SessionDataManager.getInstance().getTechnicalList());
        }
    }

    private HTTPRequestOption httpRequestOption;

    private List<Item> mCurrentList;


    private boolean mSearching = false;

    private String mKeySearch = "";

    @Override
    public void loadData(boolean refreshing) {
        if (refreshing) {
            mCurrentList.clear();
            if (mKeySearch.isEmpty()) {
                loadList(START_OFFSET,true);
            } else {
                handleSearch(mKeySearch, START_OFFSET, true);
            }
        } else {
            if (mCurrentList.isEmpty()) {
                loadList(START_OFFSET,false);
            } else {
                getView().displayList(mCurrentList);
            }
        }
    }

    @Override
    public void loadMoreData(int skipCount) {
        if (mSearching) {
            handleSearch(mKeySearch, skipCount, false);
        } else {
            loadList(skipCount, false);
        }
    }

    @Override
    public void handleSearch(String key, int skipCount, boolean refresh) {
        getView().changeRefreshing(refresh);
        mKeySearch = key;
        RESTManager.getInstance().searchTechnicalGuide(mKeySearch, skipCount, PAGE_LIMIT, new IRequestListener<TechnicalGuideResponse>() {
            @Override
            public void onCompleted(TechnicalGuideResponse data) {
                super.onCompleted(data);
                List<Item> list = data.getData() != null ? data.getData() : Collections.EMPTY_LIST;
                getView().updateListSearch(list);
                getView().changeRefreshing(false);
            }

            @Override
            public void onFailed(Throwable throwable) {
                super.onFailed(throwable);
                getView().changeRefreshing(false);
            }
        });
    }

    @Override
    public void revertCurrentList() {
        mKeySearch = "";
        mSearching = false;
        getView().updateListSearch(mCurrentList);
    }

    @Override
    public void saveSessionData() {
        SessionDataManager.getInstance().setTechnicalList(mCurrentList);
    }

    private void loadList(int skipCount, boolean refreshing) {
        getView().changeRefreshing(refreshing);
        RESTManager.getInstance().getListTechnicalGuide(skipCount, PAGE_LIMIT, new IRequestListener<TechnicalGuideResponse>(httpRequestOption) {
            @Override
            public void onCompleted(TechnicalGuideResponse data) {
                super.onCompleted(data);
                List<Item> list = data.getData() != null ? data.getData() : Collections.EMPTY_LIST;
                mCurrentList.addAll(list);
                saveSessionData();
                if (skipCount == START_OFFSET) {
                    getView().displayList(list);
                    getView().changeRefreshing(false);
                } else {
                    getView().addMoreData(list);
                }
            }

            @Override
            public void onFailed(Throwable throwable) {
                super.onFailed(throwable);
                getView().changeRefreshing(false);
            }
        });
    }
}
