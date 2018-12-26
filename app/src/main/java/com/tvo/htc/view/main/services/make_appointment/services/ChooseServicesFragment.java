package com.tvo.htc.view.main.services.make_appointment.services;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.model.response.ServicesListResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.main.services.make_appointment.MakeAppointmentFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ChooseServicesFragment extends BaseFragment<ChooseServicesContract.Presenter> implements ChooseServicesContract.View, BaseAdapter.OnItemClickListener {

    @BindView(R.id.recyclerViewServiceList)
    RecyclerView recyclerViewServiceList;

    private ChooseServicesAdapter adapter;

    public static ChooseServicesFragment newInstance() {

        Bundle args = new Bundle();

        ChooseServicesFragment fragment = new ChooseServicesFragment();
        fragment.setArguments(args);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_make_appoint_service_list;
    }


    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().loadServiceList();
    }

    @Override
    protected ChooseServicesContract.Presenter createPresenterInstance() {
        return new ChooseServicesPresenter(getContext());
    }

    @Override
    public void displayServiceList(List<ServicesListResponse.Data> services) {
        adapter = new ChooseServicesAdapter(getContext(), services);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, 1, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                if (i == services.size() - 1) {
                    return manager.getSpanCount();
                } else {
                    return 1;
                }
            }
        });
        recyclerViewServiceList.setLayoutManager(manager);
        recyclerViewServiceList.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void showErrorServicesEmpty() {
        showMessage(getString(R.string.make_appointment_error_empty_services));
    }

    @Override
    public void onItemClick(BaseAdapter adapter, View view, int position) {
        getPresenter().changeSelectService(position);
    }

    @Override
    public void notifyItemChanged(int position) {
        adapter.notifyItemChanged(position);
    }

    @OnClick(R.id.cpnbNext)
    public void onViewClicked() {
        if (!Utils.isEnabledClick(Utils.CLICK_DELAY_MSEC_500)) {
            return;
        }
        getPresenter().handleNext();
    }

    @Override
    public void onNextStep(List<ServicesListResponse.Data> listSelected) {
        Fragment fragment = FragmentUtil.findFragment(getActivity(), MakeAppointmentFragment.class);
        if (fragment instanceof MakeAppointmentFragment) {
            ((MakeAppointmentFragment) fragment).onNextStepClicked(listSelected);
        }
    }

    @Override
    public void finish() {
        showMessage(getString(R.string.make_appointment_service_list_error), () -> {
            FragmentUtil.removeFragment(getContext());
        });
    }
}
