package com.tvo.htc.view.main.profile.card;

import android.content.Context;

import com.android.lib.model.Card;
import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.WrapperCard;
import com.tvo.htc.model.event.EventUpdateListCard;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ProfileCardPresenter extends BasePresenter<ProfileCardContract.View> implements ProfileCardContract.Presenter {
    public ProfileCardPresenter(Context context) {
        super(context);
        mListCar = new ArrayList<>();
    }

    private LoginResponse mLoginResponse;
    private List<WrapperCard> mListCar;

    @Override
    public void loadData() {
        mLoginResponse = LocalDataManager.getInstance(getContext()).getLoginResponse();
        if (mLoginResponse != null && mLoginResponse.getData() != null) {
            filterListCard(mLoginResponse.getData().getCards());
            getView().displayListCard(mListCar);
        }
    }

    @Override
    public void handleUpdateListCard(EventUpdateListCard event) {
        filterListCard(event.getListCard());
        getView().displayListCard(mListCar);
    }


    private void filterListCard(List<Card> cards) {
        mListCar.clear();
        if (cards != null && !cards.isEmpty() && cards.get(0).getAgency() != null) {
            Collections.sort(cards, (o1, o2) -> o1.getAgency().getCodeX().compareTo(o2.getAgency().getCodeX()));
            WrapperCard wrapperCard = new WrapperCard();

            String currentAgencyId = cards.get(0).getAgency().getCodeX();
            String name = cards.get(0).getAgency().getName();
            List<Card> listCard = new ArrayList<>();

            for (Card card : cards) {
                if (!currentAgencyId.equals(card.getAgency().getCodeX())) {
                    wrapperCard.setName(name);
                    wrapperCard.setCards(listCard);
                    mListCar.add(wrapperCard);

                    listCard = new ArrayList<>();
                    wrapperCard = new WrapperCard();

                    currentAgencyId = card.getAgency().getCodeX();
                    name = card.getAgency().getName();
                    listCard.add(card);
                } else {
                    listCard.add(card);
                }
            }
            wrapperCard = new WrapperCard();
            wrapperCard.setName(name);
            wrapperCard.setCards(listCard);
            mListCar.add(wrapperCard);
        }
    }
}
