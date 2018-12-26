package com.tvo.htc.view.main.profile.card.add_card;

import android.view.View;
import android.widget.TextView;

import com.android.lib.model.Card;
import com.tvo.htc.R;
import com.tvo.htc.view.dialog.BaseDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by Ngocji on 10/23/2018
 **/


public class SuccessAddCardDialog extends BaseDialogFragment {
    String agency, cardNumber, customerName, model, licensePlates;
    Callback callback;

    @BindView(R.id.txtAgency)
    TextView txtAgency;
    @BindView(R.id.txtCardNumber)
    TextView txtCardNumber;
    @BindView(R.id.txtCustomerName)
    TextView txtCustomerName;
    @BindView(R.id.txtModel)
    TextView txtModel;
    @BindView(R.id.txtLicensePlates)
    TextView txtLicensePlates;


    @OnClick(R.id.cpnbOk)
    public void onViewClicked() {
        dismiss();
        callback.onCancelClicked();
    }

    interface Callback {
        void onCancelClicked();
    }

    public void setData(Card card, Callback callback) {
        this.agency = card.getAgency().getName();
        this.cardNumber = card.getCardNo();
        this.customerName = card.getCustomerName();
        this.model = card.getModel();
        this.licensePlates = card.getLicensePlates();
        this.callback = callback;
    }

    @Override
    protected void init(View view) {
        txtAgency.setText(agency);
        txtCardNumber.setText(cardNumber);
        txtCustomerName.setText(customerName);
        txtModel.setText(model);
        txtLicensePlates.setText(licensePlates);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.dialog_profile_add_card_success;
    }
}
