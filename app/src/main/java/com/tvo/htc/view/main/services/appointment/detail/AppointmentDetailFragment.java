package com.tvo.htc.view.main.services.appointment.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.lib.model.response.AppointmentResponse;
import com.android.lib.util.LibUtils;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseFragment;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class AppointmentDetailFragment extends BaseFragment<AppointmentDetailContract.Presenter> implements AppointmentDetailContract.View {
    public static final String KEY_DATA = "KEY_DATA";
    @BindView(R.id.tvCustomerFullName)
    TextView tvCustomerFullName;
    @BindView(R.id.tvCustomerPhone)
    TextView tvCustomerPhone;
    @BindView(R.id.tvCustomerEmail)
    TextView tvCustomerEmail;
    @BindView(R.id.tvServices)
    TextView tvServices;
    @BindView(R.id.tvCarCategory)
    TextView tvCarCategory;
    @BindView(R.id.tvCarVersion)
    TextView tvCarVersion;
    @BindView(R.id.tvCarLicensePlate)
    TextView tvCarLicensePlate;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.tvAgency)
    TextView tvAgency;
    @BindView(R.id.tvServiceStaff)
    TextView tvServiceStaff;
    @BindView(R.id.tvNote)
    TextView tvNote;

    public static AppointmentDetailFragment newInstance(AppointmentResponse.Data data) {

        Bundle args = new Bundle();
        args.putParcelable(KEY_DATA, data);
        AppointmentDetailFragment fragment = new AppointmentDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected AppointmentDetailContract.Presenter createPresenterInstance() {
        return new AppointmentDetailPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_appointment_detail;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().initData(getArguments());
    }

    @Override
    public void displayErrorGetAppointmentDetail() {
        //TODO: display error get data appoint...
        FragmentUtil.removeFragment(getActivity());
    }

    @Override
    public void displayAppointmentDetail(AppointmentResponse.Data data) {
        tvCustomerFullName.setText(data.getCustomerName());
        tvCustomerPhone.setText(data.getCustomerPhone());
        tvCustomerEmail.setText(data.getCustomerEmail());
        tvCarCategory.setText(data.getCarName());
        tvCity.setText(data.getCityName());
        tvAgency.setText(data.getAgencyName());
        tvServices.setText(data.getServiceName());
        tvCarVersion.setText(data.getVersionName());
        tvCarLicensePlate.setText(data.getLicensePlates());
        tvServiceStaff.setText(data.getStaffName());
        tvNote.setText(data.getCustomerNote());
        tvTime.setText(LibUtils.getDateTimeAppointment(data.getDateAppointment()));
    }
}
