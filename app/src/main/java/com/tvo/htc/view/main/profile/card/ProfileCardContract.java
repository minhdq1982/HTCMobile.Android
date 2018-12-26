package com.tvo.htc.view.main.profile.card;

import com.tvo.htc.model.WrapperCard;
import com.tvo.htc.model.event.EventUpdateListCard;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface ProfileCardContract {
    interface View extends BaseContract.View {
        void displayListCard(List<WrapperCard> cards);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData();

        void handleUpdateListCard(EventUpdateListCard event);
    }
}
