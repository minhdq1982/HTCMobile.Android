package com.tvo.htc.view.main.buycar.price;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.model.Car;
import com.android.lib.model.response.AreaResponse;
import com.android.lib.model.response.PriceAdviceResponse.*;
import com.tvo.htc.R;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnButton;
import com.tvo.htc.view.component.CpnSpinner;
import com.tvo.htc.view.component.SimpleSpinnerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PriceAdviceFragment extends BaseFragment<PriceAdviceContract.Presenter> implements PriceAdviceContract.View {

    @BindView(R.id.spListCar)
    CpnSpinner spListCar;
    @BindView(R.id.spYear)
    CpnSpinner spYear;
    @BindView(R.id.spArena)
    CpnSpinner spArena;
    @BindView(R.id.cpnCostPrediction)
    CpnButton cpnCostPrediction;
    @BindView(R.id.imgCarProduct)
    ImageView imgCarProduct;
    @BindView(R.id.rvPriceAdvice)
    RecyclerView rvPriceAdvice;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;
    @BindView(R.id.llContentCar)
    LinearLayout llContentCar;

    private int mVersionId = -1, mAreaId = -1;

    public static PriceAdviceFragment newInstance() {

        Bundle args = new Bundle();

        PriceAdviceFragment fragment = new PriceAdviceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected PriceAdviceContract.Presenter createPresenterInstance() {
        return new PriceAdvicePresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_price_advice;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().loadCar();
        getPresenter().loadArea();

        cpnCostPrediction.setOnClickListener((View v) -> {
            loadPrice(true);
        });
    }

    @Override
    public void displayCar(List<Car> cars) {
        spListCar.setOnItemSelectedListener(new SimpleSpinnerListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getPresenter().loadCarVersion(position);
                loadPrice(false);
            }
        });
        spListCar.setData(cars);
    }

    @Override
    public void displayVersion(List<Car.Version> versions) {
        List<Car.Version> listVersions = new ArrayList<>();
        listVersions.add(new Car.Version(getResources().getString(R.string.price_advice_select_year), -1));
        listVersions.addAll(versions);
        spYear.setOnItemSelectedListener(new SimpleSpinnerListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mVersionId = listVersions.get(position).getId();
                loadPrice(false);
            }
        });
        spYear.setData(listVersions, true);
    }

    @Override
    public void displayArena(List<AreaResponse.Data> item) {
        List<AreaResponse.Data> listAreas = new ArrayList<>();
        listAreas.add(new AreaResponse.Data(getResources().getString(R.string.price_advice_select_arena), -1));
        listAreas.addAll(item);
        spArena.setOnItemSelectedListener(new SimpleSpinnerListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAreaId = listAreas.get(position).getId();
                loadPrice(false);
            }
        });
        spArena.setData(listAreas, true);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void displayPrice(Price data) {
        llContentCar.setVisibility(View.VISIBLE);
        txtEmpty.setVisibility(View.GONE);
        ImageLoader.loadImage(getContext(), imgCarProduct, Utils.getImagePath(data.getImage()));

        List<PriceAdviceList> list = new ArrayList<>();
        list.add(new PriceAdviceList(getString(R.string.price_advice_cost_title), Utils.getMoney(data.getPrice() + "")));
        list.add(new PriceAdviceList(getString(R.string.price_advice_registration_fee), Utils.getMoney(data.getStampDuty() + "")));
        list.add(new PriceAdviceList(getString(R.string.price_advice_register_money), Utils.getMoney(data.getRegistrationFee() + "")));

        for (int i = 0; i < data.getOtherFees().size(); i++) {
            Pair<String, Integer> pair = new Pair<>(data.getOtherFees().get(i).getFeeName(), data.getOtherFees().get(i).getFeeValue());
            list.add(new PriceAdviceList(pair.first, Utils.getMoney(pair.second + "")));
        }

        list.add(new PriceAdviceList(getString(R.string.price_advice_total_register_car), Utils.getMoney(data.getTotalRegistrationFee() + "")));
        list.add(new PriceAdviceList(getString(R.string.price_advice_total_money_pay), Utils.getMoney(data.getTotalPay() + "")));

        rvPriceAdvice.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPriceAdvice.setAdapter(new PriceAdviceAdapter(getContext(), list));

    }

    @Override
    public void emptyPrice() {
        llContentCar.setVisibility(View.GONE);
        txtEmpty.setVisibility(View.VISIBLE);
    }

    public void loadPrice(boolean isClick) {
        if (spYear.getSelectedPosition() > 0 && spArena.getSelectedPosition() > 0) {
            getPresenter().loadPrice(mVersionId, mAreaId);
        } else {
            if (isClick) {
                showMessage(getResources().getString(R.string.add_car_error_add_empty));
            } else {
                emptyPrice();
            }
        }
    }

}
