package com.tvo.htc.view.main.buycar.installment;

import com.android.lib.model.response.InstallmentResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

public interface InstallmentContract {
    interface View extends BaseContract.View{
        void displayData(List<InstallmentResponse> installmentResponses);
        void showPercent(String percent, double loan);

        void showDialogMessage(String string);

        void showLoan(long l);

        void showDownloadDialog(double borrowResult, int numberMonthResult, double interestRateResult, double interestRateResult2, String name);
    }

    interface Presenter extends BaseContract.Presenter<View>{

        void percentageCalculation(String total, String loan);

        void startCalculation(String total, String loan, String time, String firstInterest, String nextInterest);

        void loanCalculation(String total, String loan, String percent);

        String checkLoan(String total, String loan);

        void extrasPdf(String borrow, String numberMonth, String interestRate1, String interestRate2);
    }
}
