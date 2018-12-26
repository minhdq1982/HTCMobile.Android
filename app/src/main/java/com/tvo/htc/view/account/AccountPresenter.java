package com.tvo.htc.view.account;

import android.content.Context;

import com.tvo.htc.view.BasePresenter;

/**
 * Created by ThinhNH on 3/20/2018.
 */

public class AccountPresenter extends BasePresenter<AccountContract.View> implements AccountContract.Presenter{
    public AccountPresenter(Context context) {
        super(context);
    }
}
