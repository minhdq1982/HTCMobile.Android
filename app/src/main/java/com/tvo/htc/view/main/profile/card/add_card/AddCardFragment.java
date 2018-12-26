package com.tvo.htc.view.main.profile.card.add_card;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.lib.model.Card;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class AddCardFragment extends BaseFragment<AddCardContract.Presenter> implements AddCardContract.View {

    @BindView(R.id.editCardNo)
    EditText editCardNo;
    @BindView(R.id.imPreview)
    ImageView imPreview;
    @BindView(R.id.sBottom)
    LinearLayout sBottom;

    public static AddCardFragment newInstance() {

        Bundle args = new Bundle();

        AddCardFragment fragment = new AddCardFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected AddCardContract.Presenter createPresenterInstance() {
        return new AddCardPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_add_card;
    }

    @Override
    protected boolean isShowGridBackground() {
        return true;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        imPreview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                sBottom.getLayoutParams().width = imPreview.getWidth() - (Utils.getDimensions(getContext(), R.dimen.margin_padding_2x) * 2);
                sBottom.requestLayout();
                imPreview.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        FragmentUtil.showKeyboard(getContext());
    }

    @Override
    public void displayErrorAddCard(String message) {
        showMessage(message);
    }

    @Override
    public void displaySuccessAddCard(Card card) {
        SuccessAddCardDialog dialog = new SuccessAddCardDialog();
        dialog.setData(card, () -> FragmentUtil.removeFragment(getActivity()));
        dialog.show(getChildFragmentManager(), null);
    }

    @OnClick(R.id.cpnbAdd)
    public void onViewClicked() {
        getPresenter().saveCard(editCardNo.getText().toString());
    }
}
