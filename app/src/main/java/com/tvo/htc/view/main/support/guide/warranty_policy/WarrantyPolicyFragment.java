package com.tvo.htc.view.main.support.guide.warranty_policy;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.model.response.WarrantyPolicyResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.main.support.guide.warranty_policy.detail.WarrantyDetailFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class WarrantyPolicyFragment extends BaseFragment<WarrantyPolicyContract.Presenter> implements WarrantyPolicyContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static WarrantyPolicyFragment newInstance() {

        Bundle args = new Bundle();

        WarrantyPolicyFragment fragment = new WarrantyPolicyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected WarrantyPolicyContract.Presenter createPresenterInstance() {
        return new WarrantyPolicyPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_support_guide_warranty_policy;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().loadData();
    }

    @Override
    public void displayListWarrantyPolicy(List<WarrantyPolicyResponse.WarrantyPolicy> items) {
        WarrantyPolicyAdapter adapter = new WarrantyPolicyAdapter(getContext(), items);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            FragmentUtil.startFragmentNoTabbar(getActivity(), WarrantyDetailFragment.newInstance(items, position), null);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
