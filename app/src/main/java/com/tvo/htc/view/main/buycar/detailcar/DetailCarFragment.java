package com.tvo.htc.view.main.buycar.detailcar;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.model.Car;
import com.android.lib.model.response.DetailCarResponse;
import com.google.gson.Gson;
import com.tvo.htc.R;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.PermissionUtil;
import com.tvo.htc.util.ScreenUtils;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.SimpleCustomTabScrollAdapter;
import com.tvo.htc.view.component.CpnSpinner;
import com.tvo.htc.view.dialog.DownloadDialog;
import com.tvo.htc.view.dialog.SelectVersionDialog;
import com.tvo.htc.view.main.buycar.car_comparison.CarComparisonFragment;
import com.tvo.htc.view.main.buycar.price.PriceAdviceFragment;
import com.tvo.htc.view.main.buycar.registration.RegistrationFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class DetailCarFragment extends BaseFragment<DetailCarContract.Presenter> implements DetailCarContract.View, View.OnClickListener {
    public static final String EXTRAS_DATA = "extrasData";
    public static final String EXTRAS_ID = "extrasId";

    private static final long DURATION_DEFAULT_HIDE = 100;
    private static final int REQ_PERMISSION = 1010;

    @BindView(R.id.viewPagerSlideCar)
    ViewPager viewPagerSlideCar;
    @BindView(R.id.tvCarName)
    TextView tvCarName;
    @BindView(R.id.reTitleOption)
    RecyclerView reTitleOption;

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.reCatalog)
    RecyclerView reCatalog;
    @BindView(R.id.flCatalog)
    View flVehicle;
    @BindView(R.id.cpnSpinVersion)
    CpnSpinner cpnSpinVersion;

    @BindView(R.id.flProgress)
    FrameLayout flProgress;
    @BindView(R.id.bgStatusBar)
    View bgStatusBar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.collapsingToolBar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.tvCatalogState)
    TextView tvCatalogState;
    @BindView(R.id.llRegistration)
    LinearLayout llRegistration;


    private CatalogAdapter mCatalogAdapter;
    private SimpleCustomTabScrollAdapter mSimpleCustomTabAdapter;

    public static DetailCarFragment newInstance(DetailCarResponse response) {
        Bundle args = new Bundle();
        args.putString(EXTRAS_DATA, new Gson().toJson(response));
        args.putInt(EXTRAS_ID, response.getData().getId());
        DetailCarFragment fragment = new DetailCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static DetailCarFragment newInstance(int carId) {
        Bundle args = new Bundle();
        args.putInt(EXTRAS_ID, carId);
        DetailCarFragment fragment = new DetailCarFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected DetailCarContract.Presenter createPresenterInstance() {
        return new DetailCarPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_car;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        int statusBarHeight = ScreenUtils.getStatusBarHeight(getActivity());
        mNavigationBar.setPadding(0, statusBarHeight, 0, 0);
        bgStatusBar.getLayoutParams().height = statusBarHeight;

        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            int absVerticalOffset = Math.abs(verticalOffset);
            int fullHeight = collapsingToolbar.getHeight();
            if (absVerticalOffset == fullHeight && bgStatusBar.getAlpha() == 0f) {
                bgStatusBar.animate().alpha(1f).setDuration(AppConstants.DURATION_DEFAULT).start();
            } else if ((absVerticalOffset < fullHeight && bgStatusBar.getAlpha() == 1f) || absVerticalOffset == 0) {
                bgStatusBar.setAlpha(0f);
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setInitialScale(100);
        webView.getSettings().setDefaultFontSize((int) getResources().getDimension(R.dimen.text_size));
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 0 && flProgress.getVisibility() == View.GONE) {
                    flProgress.setVisibility(View.VISIBLE);
                }
                if (newProgress >= 90) {
                    flProgress.setVisibility(View.GONE);
                }
            }
        });

        getPresenter().loadData(getArguments().getInt(EXTRAS_ID, -1), getArguments().getString(EXTRAS_DATA, ""));
    }

    @Override
    protected boolean isBackgroundTransparent() {
        return true;
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    public void displayInfoCar(String carName, List<String> images) {
        SlideImageAdapter adapter = new SlideImageAdapter(images);
        viewPagerSlideCar.setAdapter(adapter);
        tvCarName.setText(carName);
    }

    @Override
    public void showDownloadDialog(String url, String name) {
        DownloadDialog downloadDialog = new DownloadDialog();
        downloadDialog.show(getFragmentManager(), null);
        downloadDialog.startDownload(url, name, path -> {
            getPresenter().savePathCatalog(path);
            showOpenCatalog();
        });
    }

    @Override
    public void displayTitleOption(List<String> title) {
        mSimpleCustomTabAdapter = new SimpleCustomTabScrollAdapter(getContext(), title);
        reTitleOption.setAdapter(mSimpleCustomTabAdapter);
        mSimpleCustomTabAdapter.setOnItemClickListener((adapter, view, position) -> {
            getPresenter().handleChangePreviewOption(position);
        });
    }

    @Override
    public void displaySpinnerVersion(List<Car.Version> version) {
        cpnSpinVersion.setData(version);
        cpnSpinVersion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                displayListCatalog(version.get(position).getSpec());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cpnSpinVersion.setSelection(0);
    }

    @Override
    public void showErrorDetailCar(String message) {
        showMessage(message, () -> FragmentUtil.removeFragment(getContext()));
    }

    @Override
    public void hideWebViewData() {
        webView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showVehicleData() {
        flVehicle.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideVehicleData() {
        flVehicle.setVisibility(View.GONE);
    }

    @Override
    public void showWebViewData(String dataContent) {
        webView.setVisibility(View.VISIBLE);
        webView.loadData(dataContent, "text/html; charset=utf-8", "UTF-8");
    }

    @Override
    public void showErrorLinkDownloadCatalog() {
        showMessage(getString(R.string.detail_car_error_catalog));
    }

    @Override
    public void showOpenCatalog() {
        tvCatalogState.setText(getString(R.string.detail_car_open));
    }

    @Override
    public void showSelectCarVersion(DetailCarResponse detailCarResponse) {
        SelectVersionDialog dialog = SelectVersionDialog.newInstance(getActivity(),
                detailCarResponse, new SelectVersionDialog.Callbacks() {
                    @Override
                    public void clickOKButton(DetailCarResponse detailCarResponse) {
                        FragmentUtil.startFragment(getContext(), CarComparisonFragment.newInstance(detailCarResponse), null);
                    }

                    @Override
                    public void errorVersion() {
                        showErrorVersion();
                    }
                });
        dialog.show(getFragmentManager(), null);
    }

    @Override
    public void gotoCompare(DetailCarResponse detailCarResponse) {
        FragmentUtil.startFragment(getContext(), CarComparisonFragment.newInstance(detailCarResponse), null);
    }

    @Override
    public void showErrorVersion() {
        showMessage(getString(R.string.error_car_version));
    }

    @Override
    public void displayTestDrive(boolean hasTestDrive) {
        if (hasTestDrive) {
            llRegistration.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.llCompare, R.id.llRegistration, R.id.llAdvicePrice, R.id.cardDownloadCatalog})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llCompare:
                getPresenter().handleCompare();
                break;
            case R.id.llRegistration:
                FragmentUtil.startFragment(getContext(), RegistrationFragment.newInstance(), null);
                break;
            case R.id.llAdvicePrice:
                FragmentUtil.startFragment(getContext(), PriceAdviceFragment.newInstance(), null);
                break;
            case R.id.cardDownloadCatalog:
                if (!PermissionUtil.checkReadWriteExternal(this, REQ_PERMISSION)) {
                    getPresenter().handleDownloadCatalog();
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_PERMISSION && grantResults[0] == PERMISSION_GRANTED) {
            getPresenter().handleDownloadCatalog();
        }
    }

    private void displayListCatalog(List<Car.Version.Spec> specs) {
        if (mCatalogAdapter != null) {
            mCatalogAdapter.updateData(specs);
        } else {
            mCatalogAdapter = new CatalogAdapter(getContext(), specs);
            reCatalog.setLayoutManager(new LinearLayoutManager(getContext()));
            reCatalog.setAdapter(mCatalogAdapter);
        }
    }
}
