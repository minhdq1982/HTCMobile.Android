package com.tvo.htc.view.main.survey.question;

import android.content.Context;

import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Ngocji on 11/23/2018
 **/


public class BaseQuestion {
    protected Context mContext;
    protected LoginResponse mLoginResponse;

    public BaseQuestion(Context mContext) {
        this.mContext = mContext;
        mLoginResponse = LocalDataManager.getInstance(mContext).getLoginResponse();
    }

    protected boolean hasPrefData() {
        return mLoginResponse != null && mLoginResponse.getData() != null;
    }

    protected String getString(int id) {
        return mContext.getString(id);
    }

    protected List<String> getListString(int id) {
        return Utils.getArrayStringResId(mContext, id);
    }

    protected <T> List<String> convertListObjectToString(List<T> list) {
        List<String> newList = new ArrayList<>();
        if (list != null) {
            for (T o : list) {
                newList.add(o.toString());
            }
        } else {
            newList.add(getString(R.string.survey_default_error));
        }
        return newList;
    }

    public enum HeaderType {
        NONE,
        REQUIRE,
        TIP_LEVEL
    }
}
