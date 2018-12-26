package com.tvo.htc.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.android.lib.RESTManager;
import com.android.lib.model.LibLocalDataManager;
import com.android.lib.model.response.LoginResponse;
import com.android.lib.util.Logger;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.ScreenUtils;
import com.tvo.htc.view.component.CpnNavigationBar;

import butterknife.ButterKnife;


/**
 * Created by ThinhNH on 08/08/2016.
 */
public abstract class BaseActivity<P extends BaseContract.Presenter> extends AppCompatActivity
        implements BaseContract.View {

    private P presenter;

    protected abstract P createPresenterInstance();

    protected abstract int getLayoutId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adjustFontScale(getResources().getConfiguration());

        if (isReturn()) {
            return;
        }
        initLib();
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(getLayoutId());

        presenter = createPresenterInstance();
        if (presenter != null) {
            //noinspection unchecked
            presenter.attachView(this);
        }

        ScreenUtils.translucentScreen(this);

        Logger.d("");
        if (savedInstanceState != null) {
            Intent intent = getPackageManager().getLaunchIntentForPackage(getApplication()
                    .getPackageName());
            intent.setAction(Intent.ACTION_MAIN);
            startActivity(intent);
            this.finish();
            return;
        }
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        onCreateBaseActivity(savedInstanceState);
    }

    public void adjustFontScale(Configuration configuration) {
        if (configuration.fontScale > 1) {
            configuration.fontScale = 1;
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            getResources().updateConfiguration(configuration, metrics);
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        adjustFontScale(getResources().getConfiguration());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = FragmentUtil.getCurrentFragment(this);
        if (fragment instanceof BaseFragment) {
//            ((BaseFragment) fragment).onBackPressed();
            ((BaseFragment) fragment).onNavigationBtClick(CpnNavigationBar.NavigationType.BT_BACK);
        } else {
            if (fragment == null) {
                finish();
            } else {
                FragmentUtil.removeFragment(this);
            }
        }
    }

    protected void onCreateBaseActivity(Bundle savedInstanceState) {

    }

    private void initLib() {
        // setup for libs
        RESTManager.getInstance().init(this);

        // init LibLocalDataManager
        LoginResponse loginResponse = LocalDataManager.getInstance(this).getLoginResponse();
        String accessToken = LocalDataManager.getInstance(this).getAccessToken();
        LibLocalDataManager.getInstance().setLoginResponse(loginResponse);
        LibLocalDataManager.getInstance().setAccessToken(accessToken);
    }

    public boolean isReturn() {
        return false;
    }
}
