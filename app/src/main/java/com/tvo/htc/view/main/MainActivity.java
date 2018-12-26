package com.tvo.htc.view.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.util.LibConstants;
import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.switch_page.SwitchPageImpl;
import com.tvo.htc.view.BaseActivity;
import com.tvo.htc.view.component.CpnTab;
import com.tvo.htc.view.main.buycar.BuyCarFragment;
import com.tvo.htc.view.main.home.HomeFragment;
import com.tvo.htc.view.main.login.LoginFragment;
import com.tvo.htc.view.main.news.NewsFragment;
import com.tvo.htc.view.main.services.ServicesFragment;
import com.tvo.htc.view.main.support.SupportFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View,
        CpnTab.TabListener {

    @BindView(R.id.cpnTab)
    CpnTab cpnTab;

    private CpnTab.TabType mTabTypeSelected;
    private UnauthorizedBroadcastReceiver mReceiver;

    @Override
    protected MainContract.Presenter createPresenterInstance() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreateBaseActivity(Bundle savedInstanceState) {
        cpnTab.setMenuListener(this);
        onTabMenuSelected(CpnTab.TabType.HOME);

        mReceiver = new UnauthorizedBroadcastReceiver();
        IntentFilter filter = new IntentFilter(LibConstants.ACTION_UNAUTHORIZED);
        this.registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        SwitchPageImpl.getInstance().handleIntent(this, intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onTabMenuSelected(CpnTab.TabType type) {
        Logger.d("type: " + type);
        mTabTypeSelected = type;
        Fragment fragment = null;
        switch (type) {
            case HOME:
                fragment = HomeFragment.newInstance();
                break;
            case CAR:
                fragment = BuyCarFragment.newInstance();
                break;
            case SERVICE:
                fragment = ServicesFragment.newInstance();
                break;
            case NEWS:
                fragment = NewsFragment.newInstance();
                break;
            case SUPPORT:
                fragment = SupportFragment.newInstance();
                break;
            default:
                break;
        }

        FragmentUtil.replaceFragment(this, fragment, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    public void setTabSelected(CpnTab.TabType type) {
        cpnTab.setTabMenuSelected(type);
    }

    public void setTabSelected(CpnTab.TabType type, boolean onlyChangeTab) {
        cpnTab.setTabMenuSelected(type, onlyChangeTab);
    }

    public CpnTab.TabType getTabTypeSelected() {
        return mTabTypeSelected;
    }

    class UnauthorizedBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MainActivity.this == null) {
                return;
            }
            FragmentUtil.startFragment(MainActivity.this, LoginFragment.newInstance(), null);
        }
    }
}
