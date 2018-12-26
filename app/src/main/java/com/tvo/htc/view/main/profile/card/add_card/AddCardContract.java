package com.tvo.htc.view.main.profile.card.add_card;

import com.android.lib.model.Card;
import com.tvo.htc.view.BaseContract;

/**
 * Created by Ngocji on 3/21/2018.
 */

public interface AddCardContract {
    interface View extends BaseContract.View {
        void displayErrorAddCard(String message);

        void displaySuccessAddCard(Card card);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void saveCard(String cardNo);
    }
}
