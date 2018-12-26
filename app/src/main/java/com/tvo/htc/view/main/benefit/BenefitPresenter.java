package com.tvo.htc.view.main.benefit;

import android.content.Context;

import com.android.lib.model.Card;
import com.android.lib.model.WrapperBenefit;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class BenefitPresenter extends BasePresenter<BenefitContract.View> implements BenefitContract.Presenter {
    public BenefitPresenter(Context context) {
        super(context);
    }

    private List<Card> mListCard;

    @Override
    public void loadData() {
//        LoginResponse loginResponse = LocalDataManager.getInstance(getContext()).getLoginResponse();
//        if (loginResponse != null && loginResponse.displayListQuestion() != null && loginResponse.displayListQuestion().getCards() != null) {
//            //mListCard = loginResponse.displayListQuestion().getCards();
//            mListCard = SessionDataManager.getInstance().getCards();
//            createWrapListBenefit(mListCard);
//        } else {
//        }
        mListCard = SessionDataManager.getInstance().getCards();
        createWrapListBenefit(mListCard);
        //TODO: init load data benefit
    }

    private void createWrapListBenefit(List<Card> list) {

        List<WrapperBenefit> listDisplay = new ArrayList<>();
        String currentId = "";
        for (Card item : list) {
            if (currentId.isEmpty() || !item.getLicensePlates().equals(currentId)) {
                listDisplay.add(getWrapperBenefit(item, list));
                currentId = item.getLicensePlates();
            }
        }
        getView().displayListBenefit(listDisplay);
    }

    private WrapperBenefit getWrapperBenefit(Card card, List<Card> listCard) {
        WrapperBenefit wrapperBenefit = new WrapperBenefit();
        wrapperBenefit.setCarId(card.getId());
        wrapperBenefit.setCarName(card.getModel());
        wrapperBenefit.setCarLicensePlates(card.getLicensePlates());
        wrapperBenefit.setAgencyName(card.getAgency().getName());
        wrapperBenefit.setCard(card);
        List<WrapperBenefit.WrapperDetailBenefit> listBenefit = new ArrayList<>();
        for (Card item : listCard) {
            if (item.getLicensePlates().equals(card.getLicensePlates())) {
                List<Card.Benefit> itemList = item.getBenefits();
                for (int i = 0; i < itemList.size(); i++) {
                    Card.Benefit benefit = itemList.get(i);
                    if (checkAvailableAdd(listBenefit, benefit.getId())) {
                        WrapperBenefit.WrapperDetailBenefit detail = new WrapperBenefit.WrapperDetailBenefit();
                        detail.setCarLisencePlate(card.getLicensePlates());
                        detail.setId(benefit.getId());
                        detail.setAddress(item.getAgency().getAddress());
                        detail.setDetail(benefit.getDetail());
                        detail.setContent(benefit.getContent());
                        if (card.getAgency() != null) {
                            detail.setWebsite(card.getAgency().getWebsite());
                            detail.setHotline(card.getAgency().getHotline());
                        }
                        listBenefit.add(detail);
                    }
                }
            }
        }

        wrapperBenefit.setList(listBenefit);

        return wrapperBenefit;
    }

    private boolean checkAvailableAdd(List<WrapperBenefit.WrapperDetailBenefit> originalList, int idCheck) {
        for (WrapperBenefit.WrapperDetailBenefit item :
                originalList) {
            if (item.getId() == idCheck) {
                return false;
            }
        }
        return true;
    }

    private void saveSessionData(List<WrapperBenefit> list) {
        //TODO: save session data benefit

    }

    @Override
    public void handleStartDetailBenefit(WrapperBenefit.WrapperDetailBenefit item) {
        Card.Benefit benefit = new Card.Benefit();
        benefit.setId(item.getId());
        benefit.setContent(item.getContent());
        benefit.setDetail(item.getDetail());
        ArrayList<Card> listCard = new ArrayList<>();
        if (mListCard != null) {
            for (Card card : mListCard) {
                if (card.getLicensePlates().equals(item.getCarLisencePlate())) {
                    for (Card.Benefit inner :
                            card.getBenefits()) {
                        if (inner.getId() == item.getId()) {
                            listCard.add(card);
                            break;
                        }
                    }
                }
            }
        }
        getView().startBenefitDetail(listCard, benefit);
    }
}
