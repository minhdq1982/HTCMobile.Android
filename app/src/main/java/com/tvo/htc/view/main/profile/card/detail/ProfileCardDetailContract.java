package com.tvo.htc.view.main.profile.card.detail;

import android.os.Bundle;

import com.android.lib.model.Card;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface ProfileCardDetailContract {
    interface View extends BaseContract.View {

        void displayListCard(List<Card> cards, int indexSelected);

        void displayCardInto(Card card);

        void displayHistoryCard(int cardId, String membershipCode,String cardNumber);

        void showConfirmDeleteCard();

        void showErrorPreview();

        void showMessageSussess(String string);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void initData(Bundle arguments);

        void handleViewPagerChange(int position);

        void handleDeleteCard();

        void handleHistoryCard();
    }
}
