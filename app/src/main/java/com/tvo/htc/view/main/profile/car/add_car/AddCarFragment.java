package com.tvo.htc.view.main.profile.car.add_car;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.lib.model.Car;
import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.LoginResponse;
import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnButton;
import com.tvo.htc.view.component.CpnSpinner;
import com.tvo.htc.view.main.profile.car.add_car.listener.DateWatcher;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class AddCarFragment extends BaseFragment<AddCarContract.Presenter> implements AddCarContract.View {
    public static final String KEY_DATA = "KEY_DATA";
    @BindView(R.id.cpnSpinnerListCar)
    CpnSpinner cpnSpinnerListCar;

    @BindView(R.id.edtNumberVIN)
    EditText edtNumberVIN;
    @BindView(R.id.edtLicensePlate)
    EditText edtLicensePlate;

    @BindView(R.id.imCarPreview)
    ImageView imCarPreview;

    @BindView(R.id.cpnbSave)
    CpnButton cpnbSave;

    public static AddCarFragment newInstance() {
        return newInstance(null);
    }

    public static AddCarFragment newInstance(LoginResponse.Data.Car data) {
        Bundle args = new Bundle();
        if (data != null) {
            args.putParcelable(KEY_DATA, data);
        }
        AddCarFragment fragment = new AddCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected AddCarContract.Presenter createPresenterInstance() {
        return new AddCarPresenter(getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_car;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        // validate format input date
        cpnSpinnerListCar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Car item = (Car) parent.getItemAtPosition(position);
                if (item.getImages() != null && item.getImages().size() != 0) {
                    ImageLoader.loadImage(getContext(), imCarPreview, R.drawable.img_no_image, Utils.getImagePath(item.getImages().get(0)));
                } else {
                    ImageLoader.loadImage(getContext(), imCarPreview, R.drawable.img_no_image, "");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //init data in bundle
        getPresenter().initData(getArguments());
        //load data car list and agency list
        getPresenter().loadData();
    }

    @Override
    public void displayListCar(List<Car> listCars) {
        cpnSpinnerListCar.setData(listCars);
    }

    @Override
    public void displayListAgency(List<AgenciesResponse.Agency> listAgencies) {
    }

    @Override
    public void displayCarEdit(int carPosition, String vinNo, String licensePlate, int agencyPosition, String datePurchase) {
        cpnSpinnerListCar.setSelection(carPosition);

        edtNumberVIN.setText(vinNo);
        edtLicensePlate.setText(licensePlate);
    }

    @Override
    public void displayMessage(String message) {
        showMessage(message);
    }

    @Override
    public void displayWaitDialog(boolean isShowing) {
        if (isShowing) showWaitDialog();
        else dismissWaitDialog();
    }


    @Override
    public void displaySuccessAddCar() {
        showMessage(getString(R.string.add_car_success_add), () -> FragmentUtil.removeFragment(getActivity()));
    }

    @Override
    public void displaySuccessEditCar() {
        showMessage(getString(R.string.add_car_success_edit), () -> FragmentUtil.removeFragment(getActivity()));
    }

    @Override
    public void displayEditCar() {
        mNavigationBar.setTitle(getString(R.string.add_car_edit_title));
    }

    @Override
    public void displayAddNewCar() {
        mNavigationBar.setTitle(getString(R.string.add_car_add_title));
    }

    @OnClick(R.id.cpnbSave)
    public void onSaveClicked() {
        int carId = ((Car) cpnSpinnerListCar.getSelectedData()).getId();
        String VINNo = edtNumberVIN.getText().toString();
        String licensePlate = edtLicensePlate.getText().toString();
        getPresenter().handleSaveCar(carId, VINNo, licensePlate);
    }
}
