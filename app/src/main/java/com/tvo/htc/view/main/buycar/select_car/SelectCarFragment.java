package com.tvo.htc.view.main.buycar.select_car;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.dialog.SimpleDialog;
import com.android.lib.model.Car;
import com.android.lib.model.response.CarsResponse;
import com.android.lib.model.response.SelectCarResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnButton;
import com.tvo.htc.view.main.buycar.select_car.adapter.SelectCarAdapter;
import com.tvo.htc.view.main.buycar.select_car.choose_car.ChooseCarFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectCarFragment extends BaseFragment<SelectCarContract.Presenter> implements SelectCarContract.View {

    @BindView(R.id.rvListOption)
    RecyclerView rvListOption;
    @BindView(R.id.btnSearch)
    CpnButton btnSearch;

    private SelectCarAdapter mAdapter;

    public static SelectCarFragment newInstance() {
        Bundle args = new Bundle();

        SelectCarFragment fragment = new SelectCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected SelectCarContract.Presenter createPresenterInstance() {
        return new SelectCarPresenter(getContext());
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().loadData();
    }

    @Override
    public void showErrorLoadQuestion(String displayMsg) {
        showMessage(displayMsg, () -> FragmentUtil.removeFragment(getActivity()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_select_car;
    }

    @OnClick({R.id.rvListOption, R.id.btnSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rvListOption:
                break;
            case R.id.btnSearch:
                getPresenter().handleSearch(mAdapter.getListAnswerQuestion());
                break;
        }
    }

    @Override
    public void displayListQuestion(List<SelectCarResponse.Item> mList) {
        mAdapter = new SelectCarAdapter(getContext(), mList);
        rvListOption.setAdapter(mAdapter);
    }

    @Override
    public void showErrorRequireAnswerQuestion() {
        showMessage(getString(R.string.select_car_error_require));
    }

    @Override
    public void goToListCarRecommend(List<Car> listCar) {
        FragmentUtil.startFragment(getContext(), ChooseCarFragment.newInstance(listCar), null);
    }
}
