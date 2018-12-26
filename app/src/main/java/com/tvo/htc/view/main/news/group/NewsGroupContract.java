package com.tvo.htc.view.main.news.group;

import com.android.lib.model.response.FacebookGroupResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

public interface NewsGroupContract {

    interface View extends BaseContract.View {

        void getData(List<FacebookGroupResponse.Group.Items> data);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadData();
    }
}
