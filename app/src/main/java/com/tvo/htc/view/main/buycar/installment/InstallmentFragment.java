package com.tvo.htc.view.main.buycar.installment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.lib.model.response.InstallmentResponse;
import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.util.CurrencyTextWatcher;
import com.tvo.htc.util.PermissionUtil;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnButton;
import com.tvo.htc.view.dialog.DownloadDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class InstallmentFragment extends BaseFragment<InstallmentContract.Presenter> implements InstallmentContract.View, TextWatcher {
    private static final int REQ_PERMISSION = 1019;
    @BindView(R.id.edtTotalPrice)
    EditText edtTotalPrice;
    @BindView(R.id.edtLoan)
    EditText edtLoan;
    @BindView(R.id.edtTime)
    EditText edtTime;
    @BindView(R.id.edtInterest1)
    EditText edtInterest1;
    @BindView(R.id.edtInterest2)
    EditText edtInterest2;
    @BindView(R.id.edtPercent)
    EditText edtPercent;
    @BindView(R.id.cpnCalculation)
    CpnButton cpnCalculation;
    @BindView(R.id.rvDetail)
    RecyclerView rvDetail;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.llCalculate)
    LinearLayout llCalculate;
    @BindView(R.id.llTotal)
    LinearLayout llTotal;
    @BindView(R.id.txtTotalLoan)
    TextView txtTotalLoan;
    @BindView(R.id.txtTotalPay)
    TextView txtTotalPay;
    private InstallmentAdapter mAdapter;


    public static InstallmentFragment newInstance() {

        Bundle args = new Bundle();

        InstallmentFragment fragment = new InstallmentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected InstallmentContract.Presenter createPresenterInstance() {
        return new InstallmentPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_installment;
    }

    TextWatcher edtLoanTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().equals("") || edtTotalPrice.getText().toString().equals("")) {
                return;
            }
            getPresenter().percentageCalculation(edtTotalPrice.getText().toString(), s.toString());

            if (getActivity().getCurrentFocus() == edtPercent) {
                return;
            }
            edtLoan.removeTextChangedListener(edtLoanTextWatcher);
            edtLoan.setText(getPresenter().checkLoan(edtTotalPrice.getText().toString(), edtLoan.getText().toString()));
            edtLoan.addTextChangedListener(edtLoanTextWatcher);
            edtLoan.setSelection(edtLoan.length());
        }
    };

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);

        edtTotalPrice.addTextChangedListener(new CurrencyTextWatcher());
        edtLoan.addTextChangedListener(new CurrencyTextWatcher());

        edtLoan.addTextChangedListener(edtLoanTextWatcher);
        edtTotalPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (getActivity().getCurrentFocus() == edtPercent) {
                    return;
                }
                if (s.toString().equals("") || edtLoan.getText().toString().equals("")) {
                    return;
                }
                getPresenter().percentageCalculation(s.toString(), edtLoan.getText().toString());

            }
        });
        edtPercent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                if (getActivity().getCurrentFocus() == edtTotalPrice) {
                    return;
                }
                if (s.toString().equals("") || edtTotalPrice.getText().toString().equals("")) {
                    return;
                }
                getPresenter().loanCalculation(edtTotalPrice.getText().toString(), edtLoan.getText().toString(), s.toString());

                if (getActivity().getCurrentFocus() == edtLoan) {
                    return;
                }
                if (s.toString().equals("")) {
                    edtPercent.setText("0");
                } else {
                    int percent = Integer.parseInt(s.toString());
                    if (percent > 100) {
                        edtPercent.setText("100");
                    }
                }

                edtPercent.setSelection(edtPercent.length());
            }
        });

        edtInterest1.addTextChangedListener(new WatcherInterestRate(edtInterest1));
        edtInterest2.addTextChangedListener(new WatcherInterestRate(edtInterest2));

    }


    @Override
    public void displayData(List<InstallmentResponse> installmentResponses) {
        rvDetail.setVisibility(View.VISIBLE);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvDetail.setLayoutManager(llm);
        mAdapter = new InstallmentAdapter(getContext(), installmentResponses);
        rvDetail.setAdapter(mAdapter);
        nestedScrollView.post(() -> nestedScrollView.smoothScrollTo(0, llCalculate.getMeasuredHeight()));
//        llTotal.setVisibility(View.VISIBLE);
        showTotal();

        int totalLoan = installmentResponses.get(installmentResponses.size() - 1).getTotalLoan();
        int totalRoot = installmentResponses.get(0).getTotalDebt();

        txtTotalLoan.setText(Utils.getMoney(totalLoan + ""));
        txtTotalPay.setText(Utils.getMoney(totalRoot + totalLoan + ""));
    }

    @Override
    public void showDownloadDialog(double borrowResult, int numberMonthResult, double interestRateResult, double interestRateResult2, String name) {
        DownloadDialog downloadDialog = new DownloadDialog();
        downloadDialog.show(getFragmentManager(), null);
        downloadDialog.startDownload(borrowResult, numberMonthResult, interestRateResult, interestRateResult2, name, path -> {
            Logger.e("Save success---> " + name + "/" + path);
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showPercent(String percent, double loan) {
        if (getActivity().getCurrentFocus() == edtPercent) {
            return;
        }
        edtPercent.setText(percent);
    }

    @Override
    public void showDialogMessage(String string) {
        showMessage(string);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showLoan(long p) {
        if (getActivity().getCurrentFocus() == edtLoan) {
            return;
        }

        edtLoan.setText(p + "");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().equals("")) {
            return;
        }
        getPresenter().percentageCalculation(edtTotalPrice.getText().toString(), s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @OnClick({R.id.cpnCalculation, R.id.cpnExtrasPdf})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cpnCalculation:
                String total = edtTotalPrice.getText().toString();
                String loan = edtLoan.getText().toString();
                String time = edtTime.getText().toString();
                String firstInterest = edtInterest1.getText().toString();
                String nextInterest = edtInterest2.getText().toString();
                getPresenter().startCalculation(total, loan, time, firstInterest, nextInterest);
                break;
            case R.id.cpnExtrasPdf:
                if (!PermissionUtil.checkReadWriteExternal(this, REQ_PERMISSION)) {
                    getPresenter().extrasPdf(
                            edtLoan.getText().toString(),
                            edtTime.getText().toString(),
                            edtInterest1.getText().toString(),
                            edtInterest2.getText().toString());
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_PERMISSION && grantResults[0] == PERMISSION_GRANTED) {
            getPresenter().extrasPdf(
                    edtLoan.getText().toString(),
                    edtTime.getText().toString(),
                    edtInterest1.getText().toString(),
                    edtInterest2.getText().toString());
        }
    }


    class WatcherInterestRate extends SimpleTextWatcher {
        EditText edt;

        public WatcherInterestRate(EditText edt) {
            this.edt = edt;
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!s.toString().isEmpty()) {
                double percent = Double.parseDouble(s.toString());
                if (percent > 100.0) {
                    edt.setText("100");
                }
            }
            edt.setSelection(edt.length());
        }
    }

    private void showTotal() {
        llTotal.setVisibility(View.VISIBLE);
        Animation translateAnim;
        translateAnim = AnimationUtils.loadAnimation(getContext(),
                R.anim.anim_total_installment_in);
        llTotal.startAnimation(translateAnim);
    }
}
