package com.tvo.htc.model.event;

import com.android.lib.model.Card;

import java.util.List;

/**
 * Create by Ngocji on 10/24/2018
 **/


public class EventUpdateListCard {
    List<Card> listCard;

    public EventUpdateListCard(List<Card> listCard) {
        this.listCard = listCard;
    }

    public List<Card> getListCard() {
        return listCard;
    }
}
