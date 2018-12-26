package com.tvo.htc.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.model.Card;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.main.profile.card.UseCardDialog;
import com.tvo.htc.view.main.profile.card.add_card.AddCardFragment;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.Gravity.CENTER;
import static com.tvo.htc.util.Utils.clearMemory;

/**
 * Created by ThinhNH on 10/08/2016
 * display and control action on navigation bar
 */
public class CpnMembershipCard extends LinearLayout {
    @BindView(R.id.flRootView)
    FrameLayout flRootView;
    @BindView(R.id.tvUseCard)
    TextView tvUseCard;
    @BindView(R.id.tvAgency)
    TextView tvAgency;
    @BindView(R.id.tvCardNumber)
    GradientTextView tvCardNumber;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvCarInfo)
    TextView tvCarInfo;
    @BindView(R.id.tvCarLicensePlate)
    TextView tvCarLicensePlate;
    @BindView(R.id.vLine)
    View line;

    @BindView(R.id.llAddNewCard)
    LinearLayout llAddNewCard;
    @BindView(R.id.llExistCard)
    LinearLayout llExistCard;

    @BindView(R.id.flContainerScale)
    FrameLayout flContainerScale;


    @BindDimen(R.dimen.membership_card_width)
    int mFixWidth;
    @BindDimen(R.dimen.membership_card_height)
    int mFixHeight;

    private Type mType;
    private Card mCard;

    private int mScaleWidth;
    private int mScaleHeight;

    private int mVisibleUseCard;

    public enum Type {
        ADD("NONE", null),
        GOLD("Gold", null),
        SILVER("Silver", null),
        PLATINUM("Platinum", new int[]{
                Color.parseColor("#FFDA78"),
                Color.parseColor("#FFDA78"),
                Color.parseColor("#FFEEC3"),
                Color.parseColor("#FFEEC3")});

        public String rankName;
        public int[] colors;

        Type(String rankName, int[] colors) {
            this.rankName = rankName;
            this.colors = colors;
        }
    }

    public CpnMembershipCard(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CpnMembershipCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CpnMembershipCard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Load attributes
        clearMemory();
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable
                .CpnMembershipCard, defStyle, 0);
        mScaleWidth = a.getDimensionPixelSize(R.styleable.CpnMembershipCard_scale_width, -1);
        mScaleHeight = a.getDimensionPixelSize(R.styleable.CpnMembershipCard_scale_height, -1);
        mVisibleUseCard = a.getInt(R.styleable.CpnMembershipCard_visible_use_card, VISIBLE);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.cpn_membership_card, this, true);
        ButterKnife.bind(this);

        updateView();
    }

    private void updateView() {
        if (mScaleWidth != -1 && mScaleHeight != -1) {
            float scaleX = (mScaleWidth * 1f) / (mFixWidth * 1f);
            float scaleY = (mScaleHeight * 1f) / (mFixHeight * 1f);
            LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(mScaleWidth, mScaleHeight, CENTER);
            flRootView.setLayoutParams(containerParams);

            FrameLayout.LayoutParams scaleParams = new FrameLayout.LayoutParams(mFixWidth, mFixHeight);
            scaleParams.setMargins((mScaleWidth - mFixWidth) / 2, (mScaleHeight - mFixHeight) / 2, 0, 0);
            flContainerScale.setLayoutParams(scaleParams);
            flContainerScale.setScaleX(scaleX);
            flContainerScale.setScaleY(scaleY);
        }

        tvUseCard.setVisibility(mVisibleUseCard);
    }

    public void setData(Card card) {
        if (card == null) return;
        mCard = card;
        if (card.getRank() == null || card.getRank().isEmpty()) {
            // type Add
            mType = Type.ADD;
            llAddNewCard.setVisibility(VISIBLE);
            llExistCard.setVisibility(GONE);
        } else {
            if (card.getRank().equalsIgnoreCase(Type.SILVER.rankName)) {
                mType = Type.SILVER;
                llExistCard.setBackgroundResource(R.drawable.img_membership_silver);
                tvUseCard.setTextColor(ContextCompat.getColor(getContext(), R.color.colorHideTextSilver));
                tvUseCard.setBackgroundResource(R.drawable.bg_hide_card_silver);
            } else if (card.getRank().equalsIgnoreCase(Type.GOLD.rankName)) {
                mType = Type.GOLD;
                llExistCard.setBackgroundResource(R.drawable.img_membership_gold);
                tvUseCard.setTextColor(ContextCompat.getColor(getContext(), R.color.colorHideTextGold));
                tvUseCard.setBackgroundResource(R.drawable.bg_hide_card_gold);
            } else if (card.getRank().equalsIgnoreCase(Type.PLATINUM.rankName)) {
                mType = Type.PLATINUM;
                llExistCard.setBackgroundResource(R.drawable.img_membership_platinum);
                tvUseCard.setTextColor(ContextCompat.getColor(getContext(), R.color.colorHideTextPlatinum));
                tvUseCard.setBackgroundResource(R.drawable.bg_hide_card_platinum);
                int colorText = ContextCompat.getColor(getContext(), R.color.white);
                tvAgency.setTextColor(colorText);
                tvName.setTextColor(colorText);
                line.setBackgroundColor(colorText);
                tvCarInfo.setTextColor(colorText);
                tvCarLicensePlate.setTextColor(colorText);

            }
            llAddNewCard.setVisibility(GONE);
            llExistCard.setVisibility(VISIBLE);
        }

        if (mType == Type.ADD) {
            return;
        }
        tvName.setText(card.getCustomerName());
        tvCardNumber.setText(card.getCardNo());
        changeGradientTextNo(tvCardNumber, mType.colors);
        String agencyName = card.getAgency() == null ? "" : card.getAgency().getName();
        if (agencyName != null && !agencyName.isEmpty() && agencyName.contains(" ")) {
            String[] agencyNames = agencyName.split(" ");
            agencyName = agencyNames[0] + "\n";
            for (int i = 1; i < agencyNames.length; i++) {
                agencyName += agencyNames[i] + " ";
            }
            agencyName.trim();
        }
        tvAgency.setText(agencyName);
        tvCarInfo.setText(card.getModel());
        tvCarLicensePlate.setText(card.getLicensePlates());
    }

    @OnClick({R.id.ivAddCard, R.id.tvUseCard})
    void onClick(View view) {
        Fragment fragment;
        switch (view.getId()) {
            case R.id.ivAddCard:
                fragment = AddCardFragment.newInstance();
                FragmentUtil.startFragment(getContext(), fragment, null);
                break;
            case R.id.tvUseCard:
                UseCardDialog dialog = new UseCardDialog();
                dialog.setData(mCard);
                dialog.show(((AppCompatActivity) getContext()).getSupportFragmentManager(), null);
                break;
            default:
                break;
        }
    }

    public Type getType() {
        return mType;
    }

    private void changeGradientTextNo(GradientTextView tv, int[] colors) {
        if (colors != null) {
            tv.setGradient(colors);
        }
    }
}
