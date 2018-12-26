package com.tvo.htc.view.main.support.guide.technical_guide.detail;

import android.content.Context;

import com.android.lib.model.response.TechnicalGuideResponse;
import com.android.lib.util.LibUtils;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BasePresenter;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class TechnicalGuideDetailPresenter extends BasePresenter<TechnicalGuideDetailContract.View> implements TechnicalGuideDetailContract.Presenter {
    public TechnicalGuideDetailPresenter(Context context) {
        super(context);
    }

    @Override
    public void loadData(TechnicalGuideResponse.Item data) {
        if (data != null) {
            String imgLink = Utils.getImagePath(data.getImage());
            String title = data.getTitle();
            String time = "";
            if (data.getLastModificationTime() != null) {
                time = LibUtils.getFormatTitleDate(data.getLastModificationTime());
            }
            String content = data.getContent();
            getView().showInfo(imgLink, title, time);
            getView().showData(content);
        }
    }
}
