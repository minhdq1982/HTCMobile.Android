package com.tvo.htc.view.main.support.comment_complaint;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.lib.model.Car;
import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnSpinner;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class CommentComplaintFragment extends BaseFragment<CommentComplaintContract.Presenter> implements CommentComplaintContract.View {
    @BindView(R.id.editFullName)
    EditText editFullName;
    @BindView(R.id.editEmail)
    EditText editEmail;
    @BindView(R.id.editPhone)
    EditText editPhone;
    @BindView(R.id.editLicensePlate)
    EditText editLicensePlate;
    @BindView(R.id.spinerProblems)
    CpnSpinner spinerProblems;
    @BindView(R.id.editProblemsTitle)
    EditText editProblemsTitle;
    @BindView(R.id.editProblemsContent)
    EditText editProblemsContent;

    @BindView(R.id.spinerCar)
    CpnSpinner spinerCar;
    @BindView(R.id.spinerProfileCar)
    CpnSpinner spinnerProfileCar;
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

    public static CommentComplaintFragment newInstance() {

        Bundle args = new Bundle();

        CommentComplaintFragment fragment = new CommentComplaintFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected CommentComplaintContract.Presenter createPresenterInstance() {
        return new CommentComplaintPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_support_comment_complaint;
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
    public void displayProblems(List<String> problems, int selectionPosition) {
        spinerProblems.setData(problems);
        spinerProblems.setSelection(selectionPosition);
    }

    @Override
    public void displayErrorEmpty(String message) {
        showMessage(message);
    }

    @Override
    public void displaySuccessSent(String message) {
        showMessage(message, () -> FragmentUtil.removeFragment(getActivity()));
    }

    @Override
    public void displayListUseCar(List<LoginResponse.Data.Car> cars) {
        spinnerProfileCar.setData(cars);
    }

    @Override
    public void displaySelectCar(List<Car> cars) {
        spinerCar.setData(cars);
    }

    @Override
    public void disableUseCar() {
        rbUseMyCar.setEnabled(false);
        rbSelectCar.setChecked(true);
    }

    @Override
    public void displayUserInfo(LoginResponse.Data mUserData) {
        editEmail.setText(mUserData.getEmailAddress());
        editFullName.setText(mUserData.getName());
        editPhone.setText(mUserData.getPhoneNumber());
    }

    @OnClick(R.id.cpnbSent)
    public void onSentClicked() {
        getPresenter().sentCommentComplaint(editFullName.getText().toString(),
                editPhone.getText().toString(),
                editEmail.getText().toString(),
                editLicensePlate.getText().toString(),
                spinerProblems.getSelectedPosition(),
                editProblemsTitle.getText().toString(),
                editProblemsContent.getText().toString(),
                chkUpdateProfile.isChecked(),
                spinnerProfileCar.getSelectedPosition(),
                spinerCar.getSelectedPosition(),
                rdCardInfo.getCheckedRadioButtonId() == R.id.rbUseMyCar,
                chkAddNewCar.isChecked()
        );
    }
}
