package com.tvo.htc.view.main.buycar.allcar;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.model.Car;
import com.android.lib.model.response.CarCategoryResponse;
import com.android.lib.model.response.DetailCarResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.CategoryCustomTabScrollAdapter;
import com.tvo.htc.view.component.CpnCustomRecyclerView;
import com.tvo.htc.view.component.CpnNavigationBar;
import com.tvo.htc.view.dialog.SelectVersionDialog;
import com.tvo.htc.view.main.buycar.detailcar.DetailCarFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AllCarFragment extends BaseFragment<AllCarContract.Presenter> implements AllCarContract.View, View.OnClickListener {

    @BindView(R.id.llFilter)
    LinearLayout llFilter;
    @BindView(R.id.cpnRecyclerViewCar)
    CpnCustomRecyclerView recyclerView;
    @BindView(R.id.rvFilterCar)
    RecyclerView rvFilterCar;
    @BindView(R.id.rvOptionCar)
    RecyclerView rvOptionCar;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.tvLager)
    TextView tvLager;
    @BindView(R.id.tvSmaller)
    TextView tvSmaller;
    @BindView(R.id.tvBellow)
    TextView tvBellow;
    @BindView(R.id.tvAbove)
    TextView tvAbove;
    @BindView(R.id.tvAT)
    TextView tvAT;
    @BindView(R.id.tvMT)
    TextView tvMT;
    @BindView(R.id.tvCTV)
    TextView tvCTV;
    @BindView(R.id.blurView)
    View blurView;

    private AllCarAdapter mAdapter;
    private CategoryCustomTabScrollAdapter mAdapterFilter;
    private AllCarOptionAdapter mAdapterOption;
    private int mCarComparisonIndex;
    private SelectCarListener mListener;

    public interface SelectCarListener {
        void onCarSelected(int carComparisonIndex, DetailCarResponse detailCarResponse);
    }

    public static AllCarFragment newInstance() {
        return newInstance(0, null);
    }

    public static AllCarFragment newInstance(int carComparisonIndex,
                                             SelectCarListener selectCarListener) {

        Bundle args = new Bundle();

        AllCarFragment fragment = new AllCarFragment();
        fragment.setArguments(args);
        fragment.mCarComparisonIndex = carComparisonIndex;
        fragment.mListener = selectCarListener;
        return fragment;
    }

    @Override
    protected AllCarContract.Presenter createPresenterInstance() {
        return new AllCarPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_car;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);

        getPresenter().loadData();
    }

    @Override
    public void onNavigationBtClick(CpnNavigationBar.NavigationType type) {
        super.onNavigationBtClick(type);
        switch (type) {
            case BT_FILTER:
                mNavigationBar.setBtFilterVisibility(View.GONE);
                mNavigationBar.setBtCloseVisibility(View.VISIBLE);
                llFilter.setVisibility(View.VISIBLE);
                blurView.setVisibility(View.VISIBLE);
                break;
            case BT_CLOSE:
                mNavigationBar.setBtFilterVisibility(View.VISIBLE);
                mNavigationBar.setBtCloseVisibility(View.GONE);
                llFilter.setVisibility(View.GONE);
                blurView.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void displayListPreviewCar(List<Object> cars) {
        recyclerView.setEnableRefresh(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, 1, false));
        mAdapter = new AllCarAdapter(getContext(), cars);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            getPresenter().handleClickedItem(adapter.getItemAtPosition(position), mListener != null);
        });
    }

    @Override
    public void showDialogSelectVersion(DetailCarResponse detailCarResponse) {
        SelectVersionDialog dialog = SelectVersionDialog.newInstance(getActivity(),
                detailCarResponse, new SelectVersionDialog.Callbacks() {
                    @Override
                    public void clickOKButton(DetailCarResponse detailCarResponse) {
                        mListener.onCarSelected(mCarComparisonIndex, detailCarResponse);
                        FragmentUtil.removeFragment(getActivity());
                    }

                    @Override
                    public void errorVersion() {
                        showErrorCarVersion();
                    }
                });
        dialog.show(getFragmentManager(), null);
    }

    @Override
    public void gotoComparision(DetailCarResponse detailCarResponse) {
        mListener.onCarSelected(mCarComparisonIndex, detailCarResponse);
        FragmentUtil.removeFragment(getActivity());
    }

    @Override
    public void showErrorCarVersion() {
        showMessage(getString(R.string.error_car_version));
    }

    @Override
    public void gotoDetailCar(int id) {
        FragmentUtil.startFragment(getContext(), DetailCarFragment.newInstance(id), null);
    }

    @Override
    public void updateListPreviewCar(List<Object> cars) {
        mAdapter.updateData(cars);
    }

    @Override
    public void displayFilterCar(List<CarCategoryResponse.Category> categories) {
        mAdapterFilter = new CategoryCustomTabScrollAdapter(getContext(), categories);
        rvFilterCar.setAdapter(mAdapterFilter);
        mAdapterFilter.setOnItemClickListener((adapter, view, position) -> {
            getPresenter().filterCarWithCategory(((CarCategoryResponse.Category) adapter.getItemAtPosition(position)).getId());
        });

    }

    @Override
    public void displayCarOption(List<String> options) {
        rvOptionCar.setVisibility(View.VISIBLE);
        rvFilterCar.setVisibility(View.GONE);
        mNavigationBar.setBtFilterVisibility(View.VISIBLE);
        mNavigationBar.setBtCloseVisibility(View.GONE);
        llFilter.setVisibility(View.GONE);
        blurView.setVisibility(View.GONE);
        if (mAdapterOption == null) {
            mAdapterOption = new AllCarOptionAdapter(getContext(), options);
            rvOptionCar.setAdapter(mAdapterOption);
        } else {
            mAdapterOption.notifyDataSetChanged();
        }
        mAdapterOption.setOnItemClickListener((adapter, view, position) -> {
            getPresenter().handleRemoveOption(position);
        });
    }

    @Override
    public void updateCarOption(List<String> options) {
        mAdapterOption.updateData(options);
    }

    @Override
    public void hideFilterCar() {
        rvOptionCar.setVisibility(View.GONE);
        rvFilterCar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeOptionPrice() {
        setBgUnSelect(tvLager);
        setBgUnSelect(tvSmaller);
    }

    @Override
    public void removeOptionCapacity() {
        setBgUnSelect(tvBellow);
        setBgUnSelect(tvAbove);
    }

    @Override
    public void removeOptionGear() {
        setBgUnSelect(tvMT);
        setBgUnSelect(tvAT);
        setBgUnSelect(tvCTV);
    }

    @Override
    public void hideWait() {
        dismissWaitDialog();
    }

    @Override
    public void showWait() {
        showWaitDialog();
    }

    @Override
    public void showDialog(String message) {
        showMessage(message);
    }

    @OnClick({R.id.ivSearch, R.id.tvLager, R.id.tvSmaller,
            R.id.tvBellow, R.id.tvAbove, R.id.tvAT, R.id.tvMT, R.id.tvCTV, R.id.btnSearch, R.id.blurView, R.id.llFilter})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivSearch:
//                getPresenter().dataSearch(mPrice, mCapacity, mGear, position);
                break;
            case R.id.tvLager:
                setBgSelect(tvLager);
                setBgUnSelect(tvSmaller);
                getPresenter().savePriceFilter(tvLager.getText().toString(), true);
                break;
            case R.id.tvSmaller:
                setBgSelect(tvSmaller);
                setBgUnSelect(tvLager);
                getPresenter().savePriceFilter(tvSmaller.getText().toString(), false);
                break;
            case R.id.tvBellow:
                setBgSelect(tvBellow);
                setBgUnSelect(tvAbove);
                getPresenter().saveCapacity(tvBellow.getText().toString(), false);
                break;
            case R.id.tvAbove:
                setBgSelect(tvAbove);
                setBgUnSelect(tvBellow);
                getPresenter().saveCapacity(tvAbove.getText().toString(), true);
                break;
            case R.id.tvAT:
                setBgSelect(tvAT);
                setBgUnSelect(tvMT);
                setBgUnSelect(tvCTV);
                getPresenter().saveGear(tvAT.getText().toString(), 1);
                break;
            case R.id.tvMT:
                setBgSelect(tvMT);
                setBgUnSelect(tvAT);
                setBgUnSelect(tvCTV);
                getPresenter().saveGear(tvMT.getText().toString(), 2);
                break;
            case R.id.tvCTV:
                setBgSelect(tvCTV);
                setBgUnSelect(tvMT);
                setBgUnSelect(tvAT);
                getPresenter().saveGear(tvCTV.getText().toString(), 3);
                break;
            case R.id.btnSearch:
                getPresenter().filterCar(false);
                break;
            case R.id.blurView:
            case R.id.llFilter:
                break;
        }
    }

    private void setBgSelect(TextView tv) {
        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_button_white_border_accent));
        tv.setTextColor(getResources().getColor(R.color.colorOptionCar));
//        tv.setTypeface(Typeface.DEFAULT_BOLD);
    }

    private void setBgUnSelect(TextView tv) {
        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_button_white_border_gray));
        tv.setTextColor(getResources().getColor(R.color.colorGray));
//        tv.setTypeface(Typeface.DEFAULT);
    }


}
