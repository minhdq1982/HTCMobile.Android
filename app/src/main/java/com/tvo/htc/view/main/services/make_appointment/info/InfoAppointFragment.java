package com.tvo.htc.view.main.services.make_appointment.info;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.CityResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnSpinner;
import com.tvo.htc.view.main.profile.car.add_car.listener.DateWatcher;
import com.tvo.htc.view.main.services.make_appointment.MakeAppointmentFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class InfoAppointFragment extends BaseFragment<InfoAppointmentContract.Presenter> implements InfoAppointmentContract.View {

    @BindView(R.id.editDate)
    EditText editDate;
    @BindView(R.id.spinerTime)
    CpnSpinner spinerTime;
    @BindView(R.id.spinerCity)
    CpnSpinner spinerCity;
    @BindView(R.id.spinerAgency)
    CpnSpinner spinerAgency;
    @BindView(R.id.edtServiceStaff)
    EditText edtServiceStaff;

    public static InfoAppointFragment newInstance() {

        Bundle args = new Bundle();

        InfoAppointFragment fragment = new InfoAppointFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_make_appoint_info;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        editDate.addTextChangedListener(new DateWatcher(editDate));
        getPresenter().loadData();
    }

    @Override
    protected InfoAppointmentContract.Presenter createPresenterInstance() {
        return new InfoAppointmentPresenter(getContext());
    }

    @Override
    public void displayTime(List<String> listTime) {
        spinerTime.setData(listTime);
    }

    @Override
    public void displayListAgency(List<AgenciesResponse.Agency> agencies) {
        spinerAgency.setData(agencies);
    }

    @Override
    public void displayCity(List<CityResponse.City> cities) {
        spinerCity.setData(cities);
        spinerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getPresenter().filterAgency(cities.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void showDatePicker(int year, int month, int dayOfMonth) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(), (view1, rYear, rMonth, rDayOfMonth) -> {
            editDate.setText(Utils.validateTimeInput(rDayOfMonth) + "/" + Utils.validateTimeInput(rMonth + 1) + "/" + rYear);
        }, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    @OnClick({R.id.cpnbPrevious, R.id.cpnbNext, R.id.imPickDate})
    public void onViewClicked(View view) {
        if (!Utils.isEnabledClick(Utils.CLICK_DELAY_MSEC_500)) {
            return;
        }
        switch (view.getId()) {
            case R.id.imPickDate:
                getPresenter().handlePickDate(editDate.getText().toString());
                break;
            case R.id.cpnbPrevious:
                Fragment fragment = FragmentUtil.findFragment(getActivity(), MakeAppointmentFragment.class);
                if (fragment instanceof MakeAppointmentFragment) {
                    ((MakeAppointmentFragment) fragment).onPreviousStepClicked();
                }
                break;
            case R.id.cpnbNext:
                CityResponse.City city = spinerCity.getSelectedData();
                AgenciesResponse.Agency agency = spinerAgency.getSelectedData();

                getPresenter().handleDataInfoAppointment(
                        editDate.getText().toString(),
                        spinerTime.getSelectedData().toString(),
                        edtServiceStaff.getText().toString(),
                        city.getId(),
                        city.getName(),
                       agency!=null ? agency.getId() : -1,
                       agency!=null ? agency.getName() : "Unknown");
                break;
        }
    }

    @Override
    public void onNextStep(String date, String time, String staffName, int cityId, String cityName, int agencyId, String agencyName) {
        Fragment fragment = FragmentUtil.findFragment(getActivity(), MakeAppointmentFragment.class);
        if (fragment instanceof MakeAppointmentFragment) {
            ((MakeAppointmentFragment) fragment).onNextStepClicked(date, time, staffName, cityId, cityName, agencyId, agencyName);
        }
    }
}
