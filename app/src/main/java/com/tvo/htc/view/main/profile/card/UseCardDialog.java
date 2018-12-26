package com.tvo.htc.view.main.profile.card;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.Pair;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.Card;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.tvo.htc.R;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.component.CpnMembershipCard;
import com.tvo.htc.view.dialog.BaseDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tvo.htc.util.AppConstants.QR_CODE_SIZE_IMAGE_HEIGHT;
import static com.tvo.htc.util.AppConstants.QR_CODE_SIZE_IMAGE_WIDTH;
import static com.tvo.htc.util.Utils.encodeAsBitmap;

/**
 * Create by Ngocji on 10/29/2018
 **/


public class UseCardDialog extends BaseDialogFragment {
    @BindView(R.id.imQRCode)
    ImageView imQRCode;
    @BindView(R.id.tvAgency)
    TextView tvAgency;
    @BindView(R.id.imgHide)
    ImageView imgHide;
    @BindView(R.id.txtHide)
    TextView txtHide;
    @BindView(R.id.txtCode)
    TextView txtCode;
    @BindView(R.id.flRootView)
    FrameLayout flRootView;
    private Card mCard;

    @Override
    protected void init(View view) {
        if (mCard != null && mCard.getCardNo() != null) {
            Pair styling = getStylingFromCard(mCard.getRank());
            Pair hideStyling = getHideStylingFromCard(mCard.getRank());
            //init background
            flRootView.setBackgroundResource((int) styling.first);
            tvAgency.setTextColor((int) styling.second);

            imgHide.setImageResource((int) hideStyling.first);
            txtHide.setTextColor((int) hideStyling.second);

            txtCode.setText(mCard.getCardNo());

            //init data
            tvAgency.setText(getAgencyName());

            Bitmap bitmap = null;

            try {
                bitmap = encodeAsBitmap(mCard.getCardNo(), BarcodeFormat.CODE_128, QR_CODE_SIZE_IMAGE_WIDTH, QR_CODE_SIZE_IMAGE_HEIGHT);
            } catch (WriterException e) {
                e.printStackTrace();
            }

            ImageLoader.loadImage(getContext(), imQRCode, bitmap);
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.dialog_use_card;
    }

    @OnClick(R.id.cpnbOk)
    public void onViewClicked() {
        dismiss();
    }

    public void setData(Card card) {
        this.mCard = card;
    }

    private Pair<Integer, Integer> getStylingFromCard(String rank) {
        String bg = "bg_membership_platinum";
        int color = ContextCompat.getColor(getContext(), R.color.colorTextPlatinum);
        if (rank != null && !rank.isEmpty()) {
            bg = "bg_membership_" + rank.toLowerCase();
            if (!rank.equalsIgnoreCase(CpnMembershipCard.Type.PLATINUM.rankName)) {
                color = ContextCompat.getColor(getContext(), R.color.colorTextOtherCard);
            }
        }
        return Pair.create(Utils.getDrawableResId(getContext(), bg), color);
    }

    private Pair<Integer, Integer> getHideStylingFromCard(String rank) {
        String bg = "bg_hide_card_platinum";
        int color = ContextCompat.getColor(getContext(), R.color.colorHideTextPlatinum);
        if (rank != null && !rank.isEmpty()) {
            bg = "bg_hide_card_" + rank.toLowerCase();
            if (rank.equalsIgnoreCase(CpnMembershipCard.Type.GOLD.rankName)) {
                color = ContextCompat.getColor(getContext(), R.color.colorHideTextGold);
            }
            if (rank.equalsIgnoreCase(CpnMembershipCard.Type.SILVER.rankName)) {
                color = ContextCompat.getColor(getContext(), R.color.colorHideTextSilver);
            }
        }
        return Pair.create(Utils.getDrawableResId(getContext(), bg), color);
    }

    private String getAgencyName() {
        String agencyName = mCard.getAgency() == null ? "" : mCard.getAgency().getName();
        if (!agencyName.isEmpty() && agencyName.contains(" ")) {
            String[] agencyNames = agencyName.split(" ");
            agencyName = agencyNames[0] + "\n";
            for (int i = 1; i < agencyNames.length; i++) {
                agencyName += agencyNames[i] + " ";
            }
            agencyName.trim();
        }
        return agencyName;
    }
}
