package com.tvo.htc.view.main.services.make_appointment.preview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.tvo.htc.R;
import com.tvo.htc.model.WrapperDataMakeAppoint;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.main.services.make_appointment.MakeAppointmentFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class PreviewFragment extends BaseFragment<PreviewContract.Presenter> implements PreviewContract.View {

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
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvAgency)
    TextView tvAgency;
    @BindView(R.id.tvServiceStaff)
    TextView tvServiceStaff;

    public static PreviewFragment newInstance() {

        Bundle args = new Bundle();

        PreviewFragment fragment = new PreviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
    }


    @Override
    protected PreviewContract.Presenter createPresenterInstance() {
        return new PreviewPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_make_appoint_preview;
    }

    @SuppressLint("SetTextI18n")
    public void displayDataPreview(WrapperDataMakeAppoint wrapperDataMakeAppoint) {
        tvCustomerFullName.setText(wrapperDataMakeAppoint.getHonorifics() + " " + wrapperDataMakeAppoint.getCustomerName());
        tvCustomerPhone.setText(wrapperDataMakeAppoint.getCustomerPhone());
        tvCustomerEmail.setText(wrapperDataMakeAppoint.getCustomerEmail());

        tvServices.setText(wrapperDataMakeAppoint.getServiceName());
        tvServiceStaff.setText(wrapperDataMakeAppoint.getStaffName());

        tvCarCategory.setText(wrapperDataMakeAppoint.getCarName());
        tvCarVersion.setText(wrapperDataMakeAppoint.getVersionName());
        tvCarLicensePlate.setText(wrapperDataMakeAppoint.getLicensePlates());

        tvDate.setText(wrapperDataMakeAppoint.getDateAppointment());
        tvAgency.setText(wrapperDataMakeAppoint.getAgencyName());
    }

    @OnClick(R.id.cpnbDone)
    public void onViewClicked() {
        if (!Utils.isEnabledClick(Utils.CLICK_DELAY_MSEC_500)) {
            return;
        }
        Fragment fragment = FragmentUtil.findFragment(getActivity(), MakeAppointmentFragment.class);
        if (fragment instanceof MakeAppointmentFragment) {
            ((MakeAppointmentFragment) fragment).onDoneAppointmentClicked();
        }
    }
}
