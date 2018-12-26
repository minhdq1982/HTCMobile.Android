package com.tvo.htc.view.main.buycar.select_car.choose_car;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.lib.dialog.SimpleDialog;
import com.android.lib.model.Car;
import com.android.lib.model.response.CarsResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnButton;
import com.tvo.htc.view.main.buycar.detailcar.DetailCarFragment;
import com.tvo.htc.view.main.buycar.select_car.adapter.ChooseCarAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChooseCarFragment extends BaseFragment<ChooseCarContract.Presenter> implements ChooseCarContract.View {
    private static final String EXTRAS_DATA = "extrasData";
    @BindView(R.id.rvListCar)
    RecyclerView rvListCar;
    @BindView(R.id.btnChoose)
    CpnButton btnChoose;
    @BindView(R.id.txtMessage)
    TextView txtMessage;


    public static ChooseCarFragment newInstance(List<Car> dataQuestion) {
        Bundle args = new Bundle();
        args.putString(EXTRAS_DATA, Utils.listToString(dataQuestion));
        ChooseCarFragment fragment = new ChooseCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected ChooseCarContract.Presenter createPresenterInstance() {
        return new ChooseCarPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choose_car;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().loadData(getArguments().getString(EXTRAS_DATA, ""));
    }

    @Override
    public void displayListCar(List<Car> list) {
        ChooseCarAdapter adapter = new ChooseCarAdapter(getContext(), list);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            FragmentUtil.startFragment(getContext(),DetailCarFragment.newInstance(adapter.getItemAtPosition(position).getId()),null);
        });
        rvListCar.setAdapter(adapter);
        txtMessage.setText(String.format("%s %s", list.size(), getString(R.string.choose_car_message)));
    }

    @Override
    public void showErrorParseCar() {
        showMessage(getString(R.string.choose_car_error), () -> FragmentUtil.removeFragment(getContext()));
    }

    @OnClick({R.id.rvListCar, R.id.btnChoose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rvListCar:
                break;
            case R.id.btnChoose:
                FragmentUtil.removeFragment(getContext());
                break;
        }
    }
}
