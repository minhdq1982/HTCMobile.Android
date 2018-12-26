package com.tvo.htc.view.main.buycar.installment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.lib.model.response.InstallmentResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;

public class InstallmentAdapter extends BaseAdapter<InstallmentResponse, InstallmentAdapter.ViewHolderNews> {


    public InstallmentAdapter(Context context, List<InstallmentResponse> items) {
        super(context, items);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_installment;
    }

    @Override
    protected ViewHolderNews createViewHolder(View view) {
        return new ViewHolderNews(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void bindView(ViewHolderNews h, InstallmentResponse item, int position) {
        h.txtTerm.setText(item.getTerm() + "");
        h.txtTotalDebt.setText(Utils.getMoney(item.getTotalDebt() + ""));
        h.txtOriginal.setText(Utils.getMoney(item.getOriginalInMonth() + ""));
        h.txtInterest.setText(Utils.getMoney(item.getInterest() + ""));
        h.txtOriginalInterest.setText(Utils.getMoney(item.getOriginalAndInterest() + ""));
    }


    class ViewHolderNews extends BaseViewHolder {
        @BindView(R.id.txtTerm)
        TextView txtTerm;
        @BindView(R.id.txtTotalDebt)
        TextView txtTotalDebt;
        @BindView(R.id.txtOriginal)
        TextView txtOriginal;
        @BindView(R.id.txtInterest)
        TextView txtInterest;
        @BindView(R.id.txtOriginalInterest)
        TextView txtOriginalInterest;


        public ViewHolderNews(View view) {
            super(view);
        }

    }

}
