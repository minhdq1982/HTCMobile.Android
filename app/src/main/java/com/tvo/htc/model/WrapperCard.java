package com.tvo.htc.model;

import com.android.lib.model.Card;

import java.util.List;

/**
 * Create by Ngocji on 11/6/2018
 **/


public class WrapperCard {
    private String name;
    private List<Card> cards;
    private boolean isShowExpand;

    public WrapperCard() {
    }

    public WrapperCard(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isShowExpand() {
        return isShowExpand;
    }

    public void setShowExpand(boolean showExpand) {
        isShowExpand = showExpand;
    }
}
