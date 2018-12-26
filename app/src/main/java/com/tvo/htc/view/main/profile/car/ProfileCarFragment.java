package com.tvo.htc.view.main.profile.car;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.R;
import com.tvo.htc.model.event.EventCar;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.dialog.ConfirmDialog;
import com.tvo.htc.view.main.profile.car.add_car.AddCarFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ProfileCarFragment extends BaseFragment<ProfileCarContract.Presenter> implements ProfileCarContract.View, BaseAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ProfileCarAdapter adapter;

    private ConfirmDialog.Builder mBuilder;
    private ConfirmDialog mConfirmDialog;

    public static ProfileCarFragment newInstance() {

        Bundle args = new Bundle();

        ProfileCarFragment fragment = new ProfileCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_car;
    }

    @Override
    protected ProfileCarContract.Presenter createPresenterInstance() {
        return new ProfileCarPresenter(getContext());
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        EventBusUtils.register(this);
        getPresenter().loadListCar();
        mBuilder = new ConfirmDialog.Builder();
        mBuilder.setTextMessage(getString(R.string.add_car_confirm_message));
        mBuilder.setTextButtonConfirm(getString(R.string.add_car_confirm_yes));
        mBuilder.setTextButtonCancel(getString(R.string.add_car_confirm_no));
        mConfirmDialog = mBuilder.create();
    }

    @Override
    protected boolean isBackgroundTransparent() {
        return true;
    }

    @Override
    public void displayListCar(List<LoginResponse.Data.Car> listCar) {
        adapter = new ProfileCarAdapter(getContext(), listCar);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void updateListCar() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void scrollToPosition(int position) {
        recyclerView.scrollToPosition(position);
    }

    @Override
    public void onItemClick(BaseAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.btnDelete:
                mConfirmDialog.show(getFragmentManager(), null);
                mConfirmDialog.addListener(new ConfirmDialog.Callback() {
                    @Override
                    public void onConfirmClicked() {
                        getPresenter().handleDeleteCar(position);
                    }

                    @Override
                    public void onCancelClicked() {
                    }
                });

                break;
            default:
                getPresenter().handleEditCar(position);
        }
    }

    @Override
    public void showSuccessDeleteCar() {
        showMessage(getString(R.string.profile_car_delete_success));
    }

    @Override
    public void startEditCar(LoginResponse.Data.Car car) {
        FragmentUtil.startFragment(getActivity(), AddCarFragment.newInstance(car), null);
    }

    @OnClick(R.id.btnAdd)
    public void onAddClicked() {
        FragmentUtil.startFragment(getActivity(), AddCarFragment.newInstance(), null);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEventCar(EventCar event) {
        getPresenter().handleEventCar(event);
    }


    @Override
    public void onDestroy() {
        EventBusUtils.unregister(this);
        super.onDestroy();
    }
}
