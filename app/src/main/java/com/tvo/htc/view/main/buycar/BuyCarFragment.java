package com.tvo.htc.view.main.buycar;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.model.Car;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnButton;
import com.tvo.htc.view.main.buycar.allcar.AllCarFragment;
import com.tvo.htc.view.main.buycar.detailcar.DetailCarFragment;
import com.tvo.htc.view.main.buycar.installment.InstallmentFragment;
import com.tvo.htc.view.main.buycar.price.PriceAdviceFragment;
import com.tvo.htc.view.main.buycar.registration.RegistrationFragment;
import com.tvo.htc.view.main.buycar.select_car.SelectCarFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tvo.htc.view.main.buycar.BuyCarProductAdapter.view_header;
import static com.tvo.htc.view.main.buycar.BuyCarProductAdapter.view_item;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public class BuyCarFragment extends BaseFragment<BuyCarContract.Presenter> implements
        BuyCarContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvEmpty)
    View emptyView;
    @BindView(R.id.btnAllCar)
    CpnButton btnAllCar;

    private BuyCarProductAdapter mAdapter;

    public static BuyCarFragment newInstance() {

        Bundle args = new Bundle();

        BuyCarFragment fragment = new BuyCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected BuyCarContract.Presenter createPresenterInstance() {
        return new BuyCarPresenter(getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_buy_car;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        mNavigationBar.addSearchListener(key -> {
            getPresenter().handleSearch(key);
        });
        getPresenter().loadListProduct();
    }

    @Override
    protected boolean isShowGridBackground() {
        return true;
    }

    @Override
    public void displayListProduct(List<Car> listProduct) {
        mAdapter = new BuyCarProductAdapter(getContext(), listProduct);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Object item = listProduct.get(position);
            if (item instanceof Car) {
                FragmentUtil.startFragment(getContext(), DetailCarFragment.newInstance(((Car) item).getId()), null);
            }
        });
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mAdapter.getItemViewType(position)) {
                    case view_header:
                        return manager.getSpanCount();
                    case view_item:
                        return 1;
                    default:
                        return -1;
                }
            }
        });
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void updateSearch(List<Car> searchList) {
        mAdapter.updateSearchQuery(searchList);
    }

    @Override
    public void hideEmptyView() {
        emptyView.setVisibility(View.GONE);
        btnAllCar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
        btnAllCar.setVisibility(View.GONE);
    }

    @OnClick({R.id.llRegisterCar, R.id.llSelectCar, R.id.llAdvisoryPrice, R.id.llAdvisoryInstallment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llRegisterCar:
                FragmentUtil.startFragment(getContext(), RegistrationFragment.newInstance(), null);
                break;
            case R.id.llSelectCar:
                FragmentUtil.startFragment(getContext(), SelectCarFragment.newInstance(), null);
                break;
            case R.id.llAdvisoryPrice:
                FragmentUtil.startFragment(getContext(), PriceAdviceFragment.newInstance(), null);
                break;
            case R.id.llAdvisoryInstallment:
                FragmentUtil.startFragment(getContext(), InstallmentFragment.newInstance(), null);
                break;
        }
    }

    @OnClick(R.id.btnAllCar)
    public void onViewClicked() {
        FragmentUtil.startFragment(getContext(), AllCarFragment.newInstance(), null);
    }
}
