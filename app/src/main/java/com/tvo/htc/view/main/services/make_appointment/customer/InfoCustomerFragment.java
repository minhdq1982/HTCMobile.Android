package com.tvo.htc.view.main.services.make_appointment.customer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.lib.model.Car;
import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnSpinner;
import com.tvo.htc.view.main.services.make_appointment.MakeAppointmentFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class InfoCustomerFragment extends BaseFragment<InfoCustomerContract.Presenter> implements InfoCustomerContract.View {

    @BindView(R.id.spinerHonorific)
    CpnSpinner spinerHonorific;
    @BindView(R.id.editFullName)
    EditText editFullName;
    @BindView(R.id.editEmail)
    EditText editEmail;
    @BindView(R.id.editPhone)
    EditText editPhone;
    @BindView(R.id.editNote)
    EditText editNote;
    @BindView(R.id.spinerCar)
    CpnSpinner spinerCar;
    @BindView(R.id.spinerProfileCar)
    CpnSpinner spinnerProfileCar;

    @BindView(R.id.spinerVersion)
    CpnSpinner spinerVersion;
    @BindView(R.id.editLicensePlate)
    EditText editLicensePlate;

    @BindView(R.id.rdCarInfo)
    RadioGroup rdCardInfo;
    @BindView(R.id.chkUpdateProfile)
    CheckBox chkUpdateProfile;
    @BindView(R.id.chkAddNewCar)
    CheckBox chkAddNewCar;
    @BindView(R.id.llSelectCar)
    View llSelectCar;
    @BindView(R.id.llUseCar)
    View llUseCar;

    @BindView(R.id.rbUseMyCar)
    RadioButton rbUseMyCar;
    @BindView(R.id.rbSelectCar)
    RadioButton rbSelectCar;

    public static InfoCustomerFragment newInstance() {

        Bundle args = new Bundle();

        InfoCustomerFragment fragment = new InfoCustomerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_make_appoint_info_customer;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        rdCardInfo.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbSelectCar:
                    llSelectCar.setVisibility(View.VISIBLE);
                    llUseCar.setVisibility(View.GONE);
                    break;
                case R.id.rbUseMyCar:
                    llSelectCar.setVisibility(View.GONE);
                    llUseCar.setVisibility(View.VISIBLE);
                    break;
            }
        });
        getPresenter().loadData();
    }

    @Override
    protected InfoCustomerContract.Presenter createPresenterInstance() {
        return new InfoCustomerPresenter(getContext());
    }


    @OnClick({R.id.cpnbPrevious, R.id.cpnbNext})
    public void onViewClicked(View view) {
        if (!Utils.isEnabledClick(Utils.CLICK_DELAY_MSEC_500)) {
            return;
        }
        switch (view.getId()) {
            case R.id.cpnbPrevious:
                Fragment fragment = FragmentUtil.findFragment(getActivity(), MakeAppointmentFragment.class);
                if (fragment instanceof MakeAppointmentFragment) {
                    ((MakeAppointmentFragment) fragment).onPreviousStepClicked();
                }
                break;
            case R.id.cpnbNext:
                getPresenter().handleNextStep(
                        spinerHonorific.getSelectedData().toString(),
                        editFullName.getText().toString(),
                        editEmail.getText().toString(),
                        editPhone.getText().toString(),
                        chkUpdateProfile.isChecked(),

                        editNote.getText().toString(),

                        editLicensePlate.getText().toString(),
                        spinnerProfileCar.getSelectedPosition(),
                        spinerCar.getSelectedPosition(),
                        spinerVersion.getSelectedPosition(),
                        rdCardInfo.getCheckedRadioButtonId() == R.id.rbUseMyCar,
                        chkAddNewCar.isChecked());
                break;
        }

    }

    @Override
    public void displayHonorifics(List<String> honorifics) {
        spinerHonorific.setData(honorifics);
    }

    @Override
    public void displayListCar(List<Car> cars) {
        spinerCar.setData(cars);
        spinerCar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getPresenter().filterVersion(cars.get(position).getId());
//                getPresenter().filterLicensePlates(cars.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void displayListUseCar(List<LoginResponse.Data.Car> cars) {
        spinnerProfileCar.setData(cars);
    }

    @Override
    public void disableProfileCar() {
        rbUseMyCar.setEnabled(false);
        rbSelectCar.setChecked(true);
    }

    @Override
    public void updateSelectionCar(int i) {
        spinerCar.setSelection(i);
    }

    @Override
    public void updateVersionByCar(List<Car.Version> version) {
        spinerVersion.setData(version);
    }

    @Override
    public void updateLicensePlates(String licensePlate) {
        editLicensePlate.setText(licensePlate);
    }

    @Override
    public void displayInfo(String name, String emailAddress, String phoneNumber) {
        editFullName.setText(name);
        editEmail.setText(emailAddress);
        editPhone.setText(phoneNumber);
    }

    @Override
    public void showErrorEmpty() {
        showMessage(getString(R.string.make_appointment_error_empty));
    }

    @Override
    public void showErrorName() {
        showMessage(getString(R.string.make_appointment_error_name));
    }

    @Override
    public void showErrorEmail() {
        showMessage(getString(R.string.make_appointment_error_email));
    }

    @Override
    public void showErrorPhoneNumber() {
        showMessage(getString(R.string.make_appointment_error_phone));
    }

    @Override
    public void showErrorLicensePlates() {
        showMessage(getString(R.string.make_appointment_error_license_plates));
    }

    @Override
    public void onNextStep(String honorifics,
                           String fullName,
                           String email,
                           String phone,
                           boolean isUpdateProfile,
                           String note,
                           int carId,
                           String carName,
                           String licensePlates,
                           int versionId,
                           String versionName,
                           boolean isAddNewCar) {
        Fragment fragment = FragmentUtil.findFragment(getActivity(), MakeAppointmentFragment.class);
        if (fragment instanceof MakeAppointmentFragment) {
            ((MakeAppointmentFragment) fragment).onNextStepClicked(honorifics, fullName, email, phone, isUpdateProfile, note, carId, carName, licensePlates, versionId, versionName,isAddNewCar);
        }
    }
}
