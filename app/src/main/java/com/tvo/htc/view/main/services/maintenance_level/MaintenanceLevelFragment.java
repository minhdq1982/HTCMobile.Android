package com.tvo.htc.view.main.services.maintenance_level;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.model.Car;
import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.CarVersionNameResponse.CarVersion;
import com.android.lib.model.response.MaintenanceDetailResponse.MaintenanceList;
import com.android.lib.model.response.MaintenanceLevelResponse.MaintenanceLevel;
import com.tvo.htc.R;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnSpinner;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class MaintenanceLevelFragment extends BaseFragment<MaintenanceLevelContract.Presenter> implements MaintenanceLevelContract.View {

    @BindView(R.id.spinerAgency)
    CpnSpinner spinerAgencys;
    @BindView(R.id.spinerListCar)
    CpnSpinner spinerCars;
    @BindView(R.id.spinerLevels)
    CpnSpinner spinerLevels;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.layoutMaintenance)
    LinearLayout layoutMaintenance;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;

    private AccessaryAdapter accessaryAdapter;

    public static MaintenanceLevelFragment newInstance() {

        Bundle args = new Bundle();

        MaintenanceLevelFragment fragment = new MaintenanceLevelFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected MaintenanceLevelContract.Presenter createPresenterInstance() {
        return new MaintenanceLevelPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_maintenance_level;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().loadData();
        spinerCars.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                getPresenter().loadMaintenance(position,
                        spinerAgencys.getSelectedPosition(),
                        spinerLevels.getSelectedPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinerAgencys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                getPresenter().loadMaintenance(
                        spinerCars.getSelectedPosition(),
                        position,
                        spinerLevels.getSelectedPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinerLevels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                getPresenter().loadMaintenance(
                        spinerCars.getSelectedPosition(),
                        spinerAgencys.getSelectedPosition(),
                        position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void displayAgencyList(List<AgenciesResponse.Agency> mList) {
        spinerAgencys.setData(mList);
    }

    @Override
    public void displayCarCategoryList(List<CarVersion> carList) {
        if (carList != null)
            spinerCars.setData(carList, true);
    }

    @Override
    public void displayMaintenanceLevelList(List<MaintenanceLevel> levelList) {
        spinerLevels.setData(levelList, true);
    }

    @Override
    public void displayItemsMaintenance(List<MaintenanceList> list) {
        accessaryAdapter = new AccessaryAdapter(getContext(), list);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(accessaryAdapter);
        layoutMaintenance.setVisibility(View.VISIBLE);
        txtEmpty.setVisibility(View.GONE);
    }

    @Override
    public void displayTotal(String total) {
        tvTotal.setText(Utils.getMoney(total));
    }

    @Override
    public void displayEmptyItems() {
        layoutMaintenance.setVisibility(View.GONE);
        txtEmpty.setVisibility(View.VISIBLE);
    }

}
