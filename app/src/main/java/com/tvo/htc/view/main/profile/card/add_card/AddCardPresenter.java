package com.tvo.htc.view.main.profile.card.add_card;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.Card;
import com.android.lib.model.response.CardResponse;
import com.android.lib.util.LibUtils;
import com.tvo.htc.R;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.model.event.EventUpdateListCard;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class AddCardPresenter extends BasePresenter<AddCardContract.View> implements AddCardContract.Presenter {

    private List<Card> mList;

    public AddCardPresenter(Context context) {
        super(context);
        mList = SessionDataManager.getInstance().getCards();
    }

    @Override
    public void saveCard(String cardNo) {
        if(cardNo.isEmpty()) {
            getView().displayErrorAddCard(getContext().getString(R.string.profile_add_card_error_add_card_empty));
            return;
        }
        if(cardNo.length() != 12) {
            getView().displayErrorAddCard(getContext().getString(R.string.profile_add_card_error_add_card_no));
            return;
        }
        if (mList == null) {
            mList = new ArrayList<>();
        }
        if (Utils.normalizeString(cardNo, false).isEmpty()) {
            getView().displayErrorAddCard(getContext().getString(R.string.profile_add_card_error_add_card_no));
        } else {
            if (!LibUtils.checkSpecialCharacters(cardNo)) {
                RESTManager.getInstance().addCard(cardNo, new IRequestListener<CardResponse>() {
                    @Override
                    public void onCompleted(CardResponse data) {
                        super.onCompleted(data);
                        if (data.getData() != null) {
                            mList.add(data.getData());
                            EventBusUtils.postEvent(new EventUpdateListCard(mList));
                            SessionDataManager.getInstance().setCards(mList);
                            getView().displaySuccessAddCard(data.getData());
                        } else {
                            getView().displayErrorAddCard(getContext().getString(R.string.profile_add_card_error_add_card_is_exist));
                        }
                    }
                });
            } else {
                getView().displayErrorAddCard(getContext().getString(R.string.profile_add_card_error_add_card_no));
            }
        }

    }
}
