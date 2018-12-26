package com.tvo.htc.view.main.services.make_appointment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.model.response.ServicesListResponse;
import com.tvo.htc.R;
import com.tvo.htc.model.WrapperDataMakeAppoint;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnLockableViewPager;
import com.tvo.htc.view.component.CpnNavigationBar;
import com.tvo.htc.view.component.CpnTab;
import com.tvo.htc.view.dialog.ConfirmDialog;
import com.tvo.htc.view.main.MainActivity;
import com.tvo.htc.view.main.services.make_appointment.preview.PreviewFragment;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class MakeAppointmentFragment extends BaseFragment<MakeAppointmentContract.Presenter> implements MakeAppointmentContract.View {

    public static final String TAG = MakeAppointmentFragment.class.getSimpleName();
    @BindView(R.id.cpnLockableViewPager)
    CpnLockableViewPager cpnLockableViewPager;

    public static MakeAppointmentFragment newInstance() {

        Bundle args = new Bundle();

        MakeAppointmentFragment fragment = new MakeAppointmentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_service_make_appointment;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        showWaitDialog();
        getPresenter().loadData();
    }

    @Override
    public void onNavigationBtClick(CpnNavigationBar.NavigationType type) {
        switch (type) {
            case BT_BACK:
                getPresenter().handleBackPressNav();
                break;
            default:
                break;
        }
    }

    @Override
    public void displayStepMakeAppointment(List<Fragment> listStep) {
        dismissWaitDialog();
        MakeAppointmentViewPagerAdapter adapter = new MakeAppointmentViewPagerAdapter(getChildFragmentManager(), listStep);
        cpnLockableViewPager.setAdapter(adapter);
        cpnLockableViewPager.setPagingEnabled(false);
    }

    @Override
    public void displayDataPreview(WrapperDataMakeAppoint wrapperDataMakeAppoint) {
        Fragment fragment = (Fragment) Objects.requireNonNull(cpnLockableViewPager.getAdapter()).instantiateItem(cpnLockableViewPager, cpnLockableViewPager.getCurrentItem());
        if (fragment instanceof PreviewFragment) {
            ((PreviewFragment) fragment).displayDataPreview(wrapperDataMakeAppoint);
        }
    }

    @Override
    public void displayStep(int currentStep) {
        cpnLockableViewPager.setCurrentItem(currentStep);
    }

    @Override
    public void finishMakeAppointment() {
        if (((MainActivity) getActivity()).getTabTypeSelected() == CpnTab.TabType.HOME) {
            ((MainActivity) getActivity()).setTabSelected(CpnTab.TabType.SERVICE);
        } else {
            FragmentUtil.removeFragment(getActivity());
        }
    }

    @Override
    public void displayConfirmFinishMakeAppointment() {
        ConfirmDialog dialog = new ConfirmDialog.Builder()
                .setTextMessage(getString(R.string.dialog_confirm_exit_make_appoint_text))
                .create();

        dialog.addListener(new ConfirmDialog.Callback() {
            @Override
            public void onConfirmClicked() {
                FragmentUtil.removeFragment(getActivity());
            }

            @Override
            public void onCancelClicked() {
            }
        });

        dialog.show(getChildFragmentManager(), ConfirmDialog.TAG);
    }

    @Override
    public void hideBackButton() {
        mNavigationBar.setBackVisibility(View.GONE);
    }

    @Override
    public void showSuccessMakeAppointment() {
        FragmentUtil.removeFragment(getActivity());
    }

    public void onNextStepClicked(String date,
                                  String time,
                                  String staffName,
                                  int cityId,
                                  String cityName,
                                  int agencyId,
                                  String agencyName) {
        getPresenter().handleNextStep(date, time, staffName, cityId, cityName, agencyId, agencyName);
    }

    public void onNextStepClicked(List<ServicesListResponse.Data> listSelectedService) {
        getPresenter().handleNextStep(listSelectedService);
    }

    public void onNextStepClicked(String honorifics,
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
        getPresenter().handleNextStep(honorifics, fullName, email, phone, isUpdateProfile, note, carId, carName, licensePlates, versionId, versionName, isAddNewCar);
    }

    public void onPreviousStepClicked() {
        getPresenter().handlePreviousStep();
    }

    public void onDoneAppointmentClicked() {
        getPresenter().handleSaveAppointment();
    }

    @Override
    protected MakeAppointmentContract.Presenter createPresenterInstance() {
        return new MakeAppointmentPresenter(getContext());
    }
}
