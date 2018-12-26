package com.tvo.htc.view.main.profile.card.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.Card;
import com.android.lib.util.LibUtils;
import com.google.gson.Gson;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.dialog.ConfirmDialog;
import com.tvo.htc.view.main.benefit.detail.BenefitDetailFragment;
import com.tvo.htc.view.main.home.CardPagerAdapter;
import com.tvo.htc.view.main.profile.card.history.ProfileCardHistoryFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tvo.htc.util.Utils.getHotline;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ProfileCardDetailFragment extends BaseFragment<ProfileCardDetailContract.Presenter> implements ProfileCardDetailContract.View {
    public static final String KEY_CARD_ID = "KEY_CARD_ID";
    public static final String KEY_CARD_LIST = "KEY_CARD_LIST";
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tvCarDateActive)
    TextView tvCarDateActive;
    @BindView(R.id.tvPoint)
    TextView tvPoint;
    @BindView(R.id.tvValue)
    TextView tvValue;
    @BindView(R.id.tvBalance)
    TextView tvBalance;
    @BindView(R.id.tvDateEndGrade)
    TextView tvDateEndGrade;
    @BindView(R.id.tvAgencyRegister)
    TextView tvAgencyRegister;
    @BindView(R.id.tvAgencyAddress)
    TextView tvAgencyAddress;
    @BindView(R.id.tvAgencyContact)
    TextView tvAgencyContact;
    @BindView(R.id.imCarRegister)
    ImageView imCarRegister;
    @BindView(R.id.tvCarName)
    TextView tvCarName;
    @BindView(R.id.tvVIN)
    TextView tvVIN;
    @BindView(R.id.tvCarLicensePlate)
    TextView tvCarLicensePlate;
    @BindView(R.id.tvCarDatePurchase)
    TextView tvCarDatePurchase;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static ProfileCardDetailFragment newInstance(int cardId, List<Card> list) {
        Bundle args = new Bundle();
        args.putInt(KEY_CARD_ID, cardId);
        if (list != null) {
            args.putString(KEY_CARD_LIST, new Gson().toJson(list));
        }
        ProfileCardDetailFragment fragment = new ProfileCardDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public static ProfileCardDetailFragment newInstance(int cardId) {
        return newInstance(cardId, null);
    }


    @Override
    protected ProfileCardDetailContract.Presenter createPresenterInstance() {
        return new ProfileCardDetailPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_card_detail;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        viewPager.setPageMargin((int) getResources().getDimension(R.dimen.card_margin));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                getPresenter().handleViewPagerChange(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        getPresenter().initData(getArguments());
    }

    @Override
    public void displayListCard(List<Card> cards, int indexSelected) {
        CardPagerAdapter adapter = new CardPagerAdapter(getActivity(), cards, null);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(indexSelected);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void displayCardInto(Card card) {
        BenefitAdapter adapter = new BenefitAdapter(getContext(), card.getBenefits());
        adapter.setOnItemClickListener((baseAdapter, view, position) -> {
            FragmentUtil.startFragment(getContext(), BenefitDetailFragment.newInstance(card, (Card.Benefit) baseAdapter.getItemAtPosition(position)), null);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        tvCarDateActive.setText(LibUtils.getFormatCardDate(card.getActiveDate()));
        tvPoint.setText(String.valueOf(card.getPointsInThePeriod()));
        tvBalance.setText(String.valueOf(card.getBalance()));
        tvValue.setText(String.valueOf(card.getSpentValueInThePeriod()));
        tvDateEndGrade.setText(LibUtils.getFormatCardDate(card.getRankExpiredDate()));
        tvAgencyRegister.setText(card.getAgency() == null ? "" : card.getAgency().getName());
        tvAgencyAddress.setText(card.getAgency() == null ? "" : card.getAgency().getAddress());
        tvAgencyContact.setText(card.getAgency() == null ? "" : getHotline(card.getAgency().getHotline()));

        tvCarDatePurchase.setText(LibUtils.getFormatCardDate(card.getActiveDate()));
        tvCarName.setText(card.getBrand() + " " + card.getModel());
        tvCarLicensePlate.setText(card.getLicensePlates());
        tvVIN.setText(card.getVinNo());
        ImageLoader.loadImage(getContext(), imCarRegister, R.drawable.ic_car_1, "");
    }

    @Override
    public void displayHistoryCard(int cardId, String membershipCode, String cardNumber) {
        FragmentUtil.startFragment(getContext(), ProfileCardHistoryFragment.newInstance(cardId, cardNumber, membershipCode), null);
    }

    @Override
    public void showConfirmDeleteCard() {
        ConfirmDialog confirmDialog = new ConfirmDialog.Builder()
                .setTextMessage(getString(R.string.profile_card_detail_message_confirm_delete))
                .create();
        confirmDialog.addListener(new ConfirmDialog.Callback() {
            @Override
            public void onConfirmClicked() {
                getPresenter().handleDeleteCard();
            }

            @Override
            public void onCancelClicked() {

            }
        });
        confirmDialog.show(getChildFragmentManager(), null);
    }

    @Override
    public void showErrorPreview() {
        showMessage(getString(R.string.profile_card_detai_error_load_preview), () -> FragmentUtil.removeFragment(getContext()));
    }

    @Override
    public void showMessageSussess(String string) {
        showMessage(string);
    }

    @OnClick({R.id.cpnbHistory, R.id.cpnbDeleteCard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cpnbHistory:
                getPresenter().handleHistoryCard();
                break;
            case R.id.cpnbDeleteCard:
                showConfirmDeleteCard();
                break;
        }
    }
}
