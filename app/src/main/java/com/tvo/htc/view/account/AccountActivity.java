package com.tvo.htc.view.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.switch_page.SwitchPageImpl;
import com.tvo.htc.view.BaseActivity;
import com.tvo.htc.view.account.term.TermFragment;
import com.tvo.htc.view.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.OnClick;

/**
 * Created by ThinhNH on 09/08/2016
 */
public class AccountActivity extends BaseActivity implements AccountContract.View {

    private Timer mTimer;
    public static long mStartTime;

    @Override
    protected AccountContract.Presenter createPresenterInstance() {
        return new AccountPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    public boolean isReturn() {
        if (SwitchPageImpl.getInstance().handleIntent(this, getIntent())) {
            return true;
        }
        return super.isReturn();
    }

    @Override
    protected void onCreateBaseActivity(Bundle savedInstanceState) {
        mStartTime = System.currentTimeMillis();
        LoginResponse loginResponse = LocalDataManager.getInstance(this).getLoginResponse();
        startNewActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        try {
            mTimer.cancel();
            mTimer = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.rlRootView)
    void onClick(View view) {
        if (view.getId() == R.id.rlRootView) {
            try {
                mTimer.cancel();
                mTimer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }

            startMainActivity();
        }
    }

    /**
     * Go to home screen after waiting time.
     */
    public void startNewActivity() {
        long endTime = System.currentTimeMillis();

        if (endTime - mStartTime >= AppConstants.SPLASH_WAITING_TIME) {
            startMainActivity();
        } else {
            TimerTask timerTask = new TimerTask() {

                public void run() {
                    startMainActivity();
                }
            };

            mTimer = new Timer();
            mTimer.schedule(timerTask, AppConstants.SPLASH_WAITING_TIME);
        }
    }

    public void startMainActivity() {
        if (!LocalDataManager.getInstance(this).hasAcceptedTermConditional()) {
            FragmentUtil.replaceFragment(this, TermFragment.newInstance(), null);
        } else {
            Intent intent = new Intent(AccountActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}