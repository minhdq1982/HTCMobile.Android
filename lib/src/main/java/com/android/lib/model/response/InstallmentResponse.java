package com.android.lib.model.response;

public class InstallmentResponse extends BaseResponse{
    private int Id;
    private int Term;
    private int TotalDebt;
    private int OriginalInMonth;
    private int Interest;
    private int OriginalAndInterest;
    private int TotalLoan;
    private int TotalPay;

    public InstallmentResponse(int term, int totalDebt, int originalInMonth, int interest, int originalAndInterest,int totalLoan, int totalPay) {
        Term = term;
        TotalDebt = totalDebt;
        OriginalInMonth = originalInMonth;
        Interest = interest;
        OriginalAndInterest = originalAndInterest;
        TotalLoan = totalLoan;
        TotalPay = totalPay;
    }

    public int getTotalLoan() {
        return TotalLoan;
    }

    public void setTotalLoan(int totalLoan) {
        TotalLoan = totalLoan;
    }

    public int getTotalPay() {
        return TotalPay;
    }

    public void setTotalPay(int totalPay) {
        TotalPay = totalPay;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTerm() {
        return Term;
    }

    public void setTerm(int term) {
        Term = term;
    }

    public int getTotalDebt() {
        return TotalDebt;
    }

    public void setTotalDebt(int totalDebt) {
        TotalDebt = totalDebt;
    }

    public int getOriginalInMonth() {
        return OriginalInMonth;
    }

    public void setOriginalInMonth(int originalInMonth) {
        OriginalInMonth = originalInMonth;
    }

    public int getInterest() {
        return Interest;
    }

    public void setInterest(int interest) {
        Interest = interest;
    }

    public int getOriginalAndInterest() {
        return OriginalAndInterest;
    }

    public void setOriginalAndInterest(int originalAndInterest) {
        OriginalAndInterest = originalAndInterest;
    }
}
