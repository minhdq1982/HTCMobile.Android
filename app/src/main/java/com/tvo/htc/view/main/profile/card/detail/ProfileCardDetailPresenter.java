package com.tvo.htc.view.main.profile.card.detail;

import android.content.Context;
import android.os.Bundle;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.Card;
import com.android.lib.model.response.CardListResponse;
import com.android.lib.model.response.LoginResponse;
import com.android.lib.model.response.SimpleResponse;
import com.android.lib.util.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.event.EventUpdateListCard;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BasePresenter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.tvo.htc.view.main.profile.card.detail.ProfileCardDetailFragment.KEY_CARD_ID;
import static com.tvo.htc.view.main.profile.card.detail.ProfileCardDetailFragment.KEY_CARD_LIST;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ProfileCardDetailPresenter extends BasePresenter<ProfileCardDetailContract.View> implements ProfileCardDetailContract.Presenter {
    public ProfileCardDetailPresenter(Context context) {
        super(context);
    }

    private List<Card> mListCard = new ArrayList<>();

    private int mCardIdSelect = -1;
    private int mCurrentIndexSelected = 0;

    @Override
    public void initData(Bundle arguments) {
        mCardIdSelect = arguments.getInt(KEY_CARD_ID, -1);
        String listCardEncode = arguments.getString(KEY_CARD_LIST, "");
        if (!listCardEncode.isEmpty()) {
            Type listType = new TypeToken<List<Card>>() {
            }.getType();
            mListCard = new Gson().fromJson(listCardEncode, listType);
            if (!mListCard.isEmpty()) {
                onSuccessLoadedList();
            } else {
                loadApi();
            }
        } else {
            LoginResponse response = LocalDataManager.getInstance(getContext()).getLoginResponse();
            if (response != null &&
                    response.getData() != null &&
                    response.getData().getCards() != null &&
                    !response.getData().getCards().isEmpty()) {
                mListCard = response.getData().getCards();
                if (!mListCard.isEmpty()) {
                    onSuccessLoadedList();
                } else {
                    loadApi();
                }
            } else {
                loadApi();
            }
        }
    }


    @Override
    public void handleViewPagerChange(int position) {
        mCurrentIndexSelected = position;
        getView().displayCardInto(mListCard.get(position));
    }

    @Override
    public void handleDeleteCard() {
        if (mListCard == null || mListCard.isEmpty()) return;
        int cardId = mListCard.get(mCurrentIndexSelected).getId();
        int membershipId = mListCard.get(mCurrentIndexSelected).getMembershipId();

        RESTManager.getInstance().deleteCard(cardId, new IRequestListener<SimpleResponse>() {
            @Override
            public void onCompleted(SimpleResponse data) {
                super.onCompleted(data);
                if (data.isSuccess()) {
                    for (int i = 0; i < mListCard.size(); i++) {
                        if (mListCard.get(i).getId() == cardId) {
                            mListCard.remove(i);
                            mCurrentIndexSelected = 0;

                            if (mListCard.isEmpty()) {
                                FragmentUtil.removeFragment(getContext());
                            } else {
                                initViewPager();
                            }
                            EventBusUtils.postEvent(new EventUpdateListCard(mListCard));
                            getView().showMessageSussess(getContext().getResources().getString(R.string.profile_card_detail_message_sussess_delete));
                            return;
                        }

                    }
                }
            }

            @Override
            public void onFailed(Throwable throwable) {
                super.onFailed(throwable);
            }
        });
    }

    @Override
    public void handleHistoryCard() {
        getView().displayHistoryCard(mListCard.get(mCurrentIndexSelected).getId(), mListCard.get(mCurrentIndexSelected).getMembershipCode(), mListCard.get(mCurrentIndexSelected).getCardNo());
    }

    private void initViewPager() {
        getView().displayListCard(mListCard, mCurrentIndexSelected);
    }

    private int findIndexOfCardId(int cardId) {
        if (cardId < 0) return 0;
        for (int i = 0; i < mListCard.size(); i++) {
            if (mListCard.get(i).getId() == cardId) {
                return i;
            }
        }
        return 0;

    }

    private void onErrorPreviewLoad() {
        getView().showErrorPreview();
    }

    private void onSuccessLoadedList() {
        mCurrentIndexSelected = findIndexOfCardId(mCardIdSelect);
        initViewPager();
        if (mCurrentIndexSelected == 0) {
            handleViewPagerChange(mCurrentIndexSelected);
        }
    }

    private void loadApi() {
        RESTManager.getInstance().getCardList(new IRequestListener<CardListResponse>() {
            @Override
            public void onCompleted(CardListResponse data) {
                super.onCompleted(data);
                if (data != null && data.getData() != null) {
                    mListCard = data.getData();
                    Logger.d(data.toString());
                    if (!mListCard.isEmpty()) {
                        onSuccessLoadedList();
                    } else {
                        onErrorPreviewLoad();
                    }
                }
            }
        });
    }
}
