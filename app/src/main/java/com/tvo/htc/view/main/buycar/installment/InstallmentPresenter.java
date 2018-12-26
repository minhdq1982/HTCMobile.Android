package com.tvo.htc.view.main.buycar.installment;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;

import com.android.lib.model.response.InstallmentResponse;
import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.util.FileUtils;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BasePresenter;

import java.util.Calendar;
import java.util.List;

public class InstallmentPresenter extends BasePresenter<InstallmentContract.View> implements InstallmentContract.Presenter {
    public InstallmentPresenter(Context context) {
        super(context);
    }

    @Override
    public void percentageCalculation(String total, String loan) {
        double newTotal = Double.parseDouble(total.replace(".", ""));
        double newLoan = Double.parseDouble(loan.replace(".", ""));
        String newPercent = Math.round((newLoan / newTotal) * 100) + "";

        getView().showPercent(newPercent, newLoan);
    }

    @Override
    public void startCalculation(String total, String loan, String time, String firstInterest, String nextInterest) {
        if (TextUtils.isEmpty(total) || TextUtils.isEmpty(loan) || TextUtils.isEmpty(time) || TextUtils.isEmpty(firstInterest) || TextUtils.isEmpty(nextInterest)) {
            getView().showDialogMessage(getContext().getResources().getString(R.string.installment_empty));
            return;
        }

        List<InstallmentResponse> arr = Utils.installmentCalculation(total, loan, time, firstInterest, nextInterest);
        getView().displayData(arr);
    }

    @Override
    public void loanCalculation(String total, String loan, String percent) {
        double newTotal = Double.parseDouble(total.replace(".", ""));
        double newPercent = Double.parseDouble(percent);
        if (newPercent > 100)
            newPercent = 100;

        getView().showLoan(Math.round((newTotal * newPercent) / 100));
    }

    @Override
    public String checkLoan(String total, String loan) {

        long newTotal = Long.parseLong(total.replace(".", ""));
        long newLoan = Long.parseLong(loan.replace(".", ""));

        if (newLoan > newTotal) {
            newLoan = newTotal;
        }

        return newLoan + "";
    }

    @Override
    public void extrasPdf(String borrow, String numberMonth, String interestRate1, String interestRate2) {
        if (borrow.isEmpty() || numberMonth.isEmpty() || interestRate1.isEmpty() || interestRate2.isEmpty()) {
            getView().showDialogMessage(getContext().getResources().getString(R.string.installment_empty));
            return;
        }
        try {
            double borrowResult = Double.parseDouble(borrow.replace(".", ""));
            int numberMonthResult = Integer.parseInt(numberMonth);
            double interestRateResult = Double.parseDouble(interestRate1);
            double interestRateResult2 = Double.parseDouble(interestRate2);
            Pair<Double, Double> pairRate = Utils.installmentCalculation(borrowResult, numberMonthResult, interestRateResult, interestRateResult2);
            getView().showDownloadDialog(borrowResult, numberMonthResult, pairRate.first, pairRate.second, FileUtils.getFileNameCalculate());
        } catch (Exception e) {
            Logger.e("ERROR: " + e);
            getView().showDialogMessage(getContext().getResources().getString(R.string.installment_error));
        }
    }
}


