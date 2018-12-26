package com.tvo.htc.view.main.services.appointment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.model.response.AppointmentResponse;
import com.android.lib.model.response.PostAppointmentResponse;
import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.BaseLoadMoreAdapter;
import com.tvo.htc.view.main.login.LoginFragment;
import com.tvo.htc.view.main.services.appointment.detail.AppointmentDetailFragment;
import com.tvo.htc.view.main.services.make_appointment.MakeAppointmentFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class AppointmentFragment extends BaseFragment<AppointmentContract.Presenter> implements AppointmentContract.View, BaseLoadMoreAdapter.OnLoadMoreListener, BaseAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;
    @BindView(R.id.layoutLogin)
    LinearLayout layoutLogin;

    private AppointmentAdapter adapter;


    public static AppointmentFragment newInstance() {

        Bundle args = new Bundle();

        AppointmentFragment fragment = new AppointmentFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected AppointmentContract.Presenter createPresenterInstance() {
        return new AppointmentPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_appointment;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        EventBusUtils.register(this);
        if (hasLogin()) {
            layoutLogin.setVisibility(View.GONE);
            getPresenter().loadListAppointment();
        } else {
            layoutLogin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void displayListAppointment(List<AppointmentResponse.Data> list) {
        if (list.size() == 0) {
            txtEmpty.setVisibility(View.VISIBLE);
        } else {
            txtEmpty.setVisibility(View.GONE);
            //TODO: edit pager limit when hava api
            adapter = new AppointmentAdapter(getContext(), recyclerView, list, AppConstants.PAGE_LIMIT);
            adapter.setOnItemClickListener(this);
            adapter.setOnLoadMoreListener(this);
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void updateListAppointment(List<AppointmentResponse.Data> list) {
        if (list.size() == 0) {
            txtEmpty.setVisibility(View.VISIBLE);
        } else {
            txtEmpty.setVisibility(View.GONE);
            adapter.addMoreData(list);
        }
    }

    @OnClick(R.id.btnAdd)
    public void onAddAppointmentClicked() {
        FragmentUtil.startFragmentNoTabbar(getActivity(), MakeAppointmentFragment.newInstance(), null);
    }

    @Override
    public void onItemClick(BaseAdapter adapter, View view, int position) {
        getPresenter().handleAppointmentClicked(position);
    }

    @Override
    public void onLoadMore(int skipCount) {
        getPresenter().loadListAppointment();
    }

    @Override
    public void displayDetailAppointment(AppointmentResponse.Data data) {
        FragmentUtil.startFragment(getActivity(), AppointmentDetailFragment.newInstance(data), null);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void makeAppointmentSuccess(PostAppointmentResponse response) {
        Logger.e("On data event: runn--> " + response);
        /*if (response != null && response.displayListQuestion() != null) {
            adapter.getDisplayItems().add(response.displayListQuestion());
            adapter.notifyItemInserted(adapter.getItemCount() - 1);
        }*/
        getPresenter().loadListAppointment();
    }

    @Override
    public void onDestroy() {
        EventBusUtils.unregister(this);
        super.onDestroy();
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        FragmentUtil.startFragment(getContext(), LoginFragment.newInstance(), null);
    }
}
