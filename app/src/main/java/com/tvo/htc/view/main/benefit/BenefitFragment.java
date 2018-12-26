package com.tvo.htc.view.main.benefit;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.model.Card;
import com.android.lib.model.WrapperBenefit;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.main.benefit.detail.BenefitDetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class BenefitFragment extends BaseFragment<BenefitContract.Presenter> implements BenefitContract.View, BaseAdapter.OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private BenefitAdapter adapter;

    public static BenefitFragment newInstance() {

        Bundle args = new Bundle();

        BenefitFragment fragment = new BenefitFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected BenefitContract.Presenter createPresenterInstance() {
        return new BenefitPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_benefit;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().loadData();
    }

    @Override
    public void displayListBenefit(List<WrapperBenefit> wrapperBenefits) {
        adapter = new BenefitAdapter(getContext(), wrapperBenefits);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void startBenefitDetail(ArrayList<Card> listCard, Card.Benefit benefit) {
        FragmentUtil.startFragment(getContext(), BenefitDetailFragment.newInstance(listCard, benefit), null);
    }

    @Override
    public void onItemClick(BaseAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.llInnerBenefit:
                WrapperBenefit.WrapperDetailBenefit item = (WrapperBenefit.WrapperDetailBenefit) adapter.getItemAtPosition(position);
                getPresenter().handleStartDetailBenefit(item);
                break;
            default:
                break;
        }
    }
}
