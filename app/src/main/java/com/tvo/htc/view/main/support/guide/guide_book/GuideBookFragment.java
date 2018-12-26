package com.tvo.htc.view.main.support.guide.guide_book;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.android.lib.model.response.GuideBookResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class GuideBookFragment extends BaseFragment<GuideBookContract.Presenter> implements GuideBookContract.View, BaseAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.emptyView)
    View emptyView;

    private GuideBookAdapter adapter;

    public static GuideBookFragment newInstance() {

        Bundle args = new Bundle();

        GuideBookFragment fragment = new GuideBookFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected GuideBookContract.Presenter createPresenterInstance() {
        return new GuideBookPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_support_guide_book;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    getPresenter().handleSearch(s.toString());
                }
            }
        });
        edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                FragmentUtil.hideKeyboard(getContext());
                getPresenter().handleSearch(edtSearch.getText().toString());
            }
            return false;
        });
        getPresenter().loadData(0,0,"",0);
    }

    @Override
    public void displayListGuideBook(List<GuideBookResponse.Data.Items> list) {
        adapter = new GuideBookAdapter(getContext(), list);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, 1, false));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void updateSearchList(List<GuideBookResponse.Data.Items> listSearch) {
        adapter.updateData(listSearch);
    }

    @Override
    public void hideEmptyView() {
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(BaseAdapter adapter, View view, int position) {
        getPresenter().handleBookClick(position);
    }

    @Override
    public void showErrorShowDetailBook() {
        showMessage(getString(R.string.support_book_guide_error_show));
    }

    @OnClick(R.id.imSearch)
    public void onViewClicked() {
        getPresenter().handleSearch(edtSearch.getText().toString());
    }
}
