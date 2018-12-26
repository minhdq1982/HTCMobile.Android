package com.tvo.htc.view.main.buycar.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.lib.dialog.SimpleDialog;
import com.android.lib.model.Car;
import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.BuyCarResponse;
import com.android.lib.model.response.CityResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnButton;
import com.tvo.htc.view.component.CpnNavigationBar;
import com.tvo.htc.view.component.CpnSpinner;
import com.tvo.htc.view.component.CpnTab;
import com.tvo.htc.view.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class RegistrationFragment extends BaseFragment<RegistrationContract.Presenter> implements RegistrationContract.View {

    @BindView(R.id.spListCar)
    CpnSpinner spListCar;
    @BindView(R.id.spCity)
    CpnSpinner spCity;
    @BindView(R.id.spAgency)
    CpnSpinner spAgency;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtNote)
    EditText edtNote;
    @BindView(R.id.cpnRegister)
    CpnButton cpnRegister;
    @BindView(R.id.chkUpdateProfile)
    CheckBox chkUpdateProfile;

    public static RegistrationFragment newInstance() {

        Bundle args = new Bundle();

        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected RegistrationContract.Presenter createPresenterInstance() {
        return new RegistrationPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_registration;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().loadData();
    }

    @Override
    public void onNavigationBtClick(CpnNavigationBar.NavigationType type) {
        switch (type) {
            case BT_BACK:
                if (((MainActivity) getActivity()).getTabTypeSelected() == CpnTab.TabType.HOME) {
                    ((MainActivity) getActivity()).setTabSelected(CpnTab.TabType.CAR);
                } else {
                    super.onNavigationBtClick(type);
                }
                break;
            default:
                super.onNavigationBtClick(type);
                break;
        }
    }

    @Override
    public void displayCity(List<CityResponse.City> cities) {
        List<CityResponse.City> listCity = new ArrayList<>();
        listCity.add(new CityResponse.City(getResources().getString(R.string.registration_select_city), -1, -1));
        listCity.addAll(cities);
        spCity.setData(listCity, true);
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getPresenter().loadAgency(listCity.get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void displayAgency(List<AgenciesResponse.Agency> agencies) {
        List<AgenciesResponse.Agency> listAgency = new ArrayList<>();
        listAgency.add(new AgenciesResponse.Agency(getResources().getString(R.string.registration_select_agency), -1, -1));
        listAgency.addAll(agencies);
        spAgency.setData(listAgency, true);
    }

    @Override
    public void displayCar(List<Car> cars) {
        List<Car> list = new ArrayList<>(cars);
        for (int i = list.size() - 1; i >= 0; i--) {
            if (!list.get(i).isHasTestDrive()) {
                list.remove(i);
            }
        }
        spListCar.setData(list);
    }

    @Override
    public void showMessageSuccess(String message) {
        showMessage(message, () -> {
            FragmentUtil.removeFragment(getContext());
        });
    }

    @OnClick({R.id.cpnRegister})
    public void onViewClicked() {
        getPresenter().onRegister(
                spListCar,
                spCity,
                spAgency,
                edtName.getText().toString(),
                edtPhone.getText().toString(),
                edtEmail.getText().toString(),
                edtNote.getText().toString(),
                chkUpdateProfile.isChecked()
        );
    }
}
