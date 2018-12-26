package com.tvo.htc.view.main.profile.card;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.model.Card;
import com.tvo.htc.R;
import com.tvo.htc.model.WrapperCard;
import com.tvo.htc.model.event.EventUpdateListCard;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.main.profile.card.add_card.AddCardFragment;
import com.tvo.htc.view.main.profile.card.detail.ProfileCardDetailFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ProfileCardFragment extends BaseFragment<ProfileCardContract.Presenter> implements ProfileCardContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CardAdapter mAdapter;

    public static ProfileCardFragment newInstance() {

        Bundle args = new Bundle();

        ProfileCardFragment fragment = new ProfileCardFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected ProfileCardContract.Presenter createPresenterInstance() {
        return new ProfileCardPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_card;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        EventBusUtils.register(this);
        getPresenter().loadData();
    }

    @Override
    protected boolean isBackgroundTransparent() {
        return true;
    }

    @Override
    public void displayListCard(List<WrapperCard> cards) {
        mAdapter = new CardAdapter(getContext(), cards, getChildFragmentManager());
        mAdapter.setSubListener((adapter, view, position) -> {
            Card item = (Card) adapter.getItemAtPosition(position);
            FragmentUtil.startFragment(getContext(), ProfileCardDetailFragment.newInstance(item.getId()), null);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @OnClick(R.id.btnAdd)
    public void onAddClicked() {
        FragmentUtil.startFragment(getActivity(), AddCardFragment.newInstance(), null);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void updateListCard(EventUpdateListCard event) {
        getPresenter().handleUpdateListCard(event);
    }

    @Override
    public void onDestroy() {
        EventBusUtils.unregister(this);
        super.onDestroy();
    }
}
