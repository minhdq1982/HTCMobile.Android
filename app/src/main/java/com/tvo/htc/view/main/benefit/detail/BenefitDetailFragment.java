package com.tvo.htc.view.main.benefit.detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.lib.model.Card;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class BenefitDetailFragment extends BaseFragment<BenefitDetailContract.Presenter> implements BenefitDetailContract.View {
    public static final String KEY_DATA_CARDS = "KEY_DATA_CARDS";
    public static final String KEY_DATA_BENEFIT = "KEY_DATA_BENEFIT";
    @BindView(R.id.tvBenefitDetail)
    TextView tvBenefitDetail;
    @BindView(R.id.tvBenefitContent)
    TextView tvBenefitContent;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static BenefitDetailFragment newInstance(ArrayList<Card> cards, Card.Benefit benefit) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY_DATA_CARDS, cards);
        args.putParcelable(KEY_DATA_BENEFIT, benefit);
        BenefitDetailFragment fragment = new BenefitDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static BenefitDetailFragment newInstance(Card card, Card.Benefit benefit) {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card);
        return newInstance(cards, benefit);
    }


    @Override
    protected BenefitDetailContract.Presenter createPresenterInstance() {
        return new BenefitDetailPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_benefit_detail;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().initData(getArguments());
    }

    @Override
    public void displayBenefitInfo(Card.Benefit benefit) {
        tvBenefitContent.setText(benefit.getContent());
        tvBenefitDetail.setText(benefit.getDetail());
    }

    @Override
    public void displayListCardApply(List<Card> cards) {
        BenefitDetailAdapter adapter = new BenefitDetailAdapter(getContext(), cards);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayErrorLoadBenefit() {
        showMessage(getString(R.string.benefit_detail_error_load_item),
                () -> FragmentUtil.removeFragment(getContext()));
    }
}
