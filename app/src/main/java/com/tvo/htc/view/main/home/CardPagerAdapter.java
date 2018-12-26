package com.tvo.htc.view.main.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.model.Card;
import com.tvo.htc.view.component.CpnMembershipCard;

import java.util.List;

import static com.tvo.htc.view.component.CpnMembershipCard.Type.ADD;

public class CardPagerAdapter extends PagerAdapter {
    private final Context mContext;
    private List<Card> mCards;
    private final LayoutInflater mInflater;
    private final CallBack mCallBack;

    public CardPagerAdapter(Context context, List<Card> cards, CallBack callback) {
        this.mCallBack = callback;
        this.mContext = context;
        this.mCards = cards;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (mCards != null) {
            return mCards.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((View) o);
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        CpnMembershipCard cpnMembershipCard = new CpnMembershipCard(container.getContext());
        cpnMembershipCard.setData(mCards.get(position));
        cpnMembershipCard.setOnClickListener(v -> {
            if (mCallBack != null) {
                if(cpnMembershipCard.getType() != ADD) {
                    mCallBack.onItemClick(mCards.get(position).getId(),mCards);
                } else {
                    mCallBack.onAddCard();
                }
            }
        });
        container.addView(cpnMembershipCard);
        return cpnMembershipCard;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void updateCards(List<Card> list) {
        mCards = list;
        notifyDataSetChanged();
    }

   public interface CallBack {
        void onItemClick(int id,List<Card> list);
        void onAddCard();
    }
}
