package com.tvo.htc.view.main.support.guide.guide_book;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.GuideBookResponse;
import com.tvo.htc.util.DefineChart;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BasePresenter;
import com.tvo.htc.view.main.support.guide.guide_book.detail.GuideBookDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class GuideBookPresenter extends BasePresenter<GuideBookContract.View> implements GuideBookContract.Presenter {
    public GuideBookPresenter(Context context) {
        super(context);
    }

    private List<GuideBookResponse.Data.Items> mListBook = new ArrayList<>();

    @Override
    public void loadData(int skipCount, int maxResultCount, String keySearch, int carId) {
        RESTManager.getInstance().getListGuideBook(skipCount, maxResultCount, keySearch, carId, new IRequestListener<GuideBookResponse>() {
            @Override
            public void onCompleted(GuideBookResponse data) {
                super.onCompleted(data);
                mListBook = data.getData().getItems();
                getView().displayListGuideBook(data.getData().getItems());
            }
        });
    }

    @Override
    public void handleSearch(String key) {
        if (!mListBook.isEmpty()) {
            if (key.isEmpty()) {
                getView().updateSearchList(mListBook);
                getView().hideEmptyView();
            } else {
                List<GuideBookResponse.Data.Items> listSearch = new ArrayList<>();
                for (GuideBookResponse.Data.Items item : mListBook) {
                    if (DefineChart.removeAccent(item.getTitle().toLowerCase()).contains(DefineChart.removeAccent(key.toLowerCase()))) {
                        listSearch.add(item);
                    }
                }
                if (listSearch.isEmpty())
                    getView().showEmptyView();
                else
                    getView().hideEmptyView();

                getView().updateSearchList(listSearch);
            }
        }
    }

    @Override
    public void handleBookClick(int position) {
        GuideBookResponse.Data.Items item = mListBook.get(position);
        if (item != null && item.getId() != -1) {
            FragmentUtil.startFragmentNoTabbar(getContext(), GuideBookDetailFragment.newInstance(item.getId(), item.getTitle()), null);
        } else {
            getView().showErrorShowDetailBook();
        }
    }
}
