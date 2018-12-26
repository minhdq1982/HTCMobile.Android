package com.tvo.htc.view.main.support.guide.technical_guide;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.android.lib.model.response.TechnicalGuideResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.BaseLoadMoreAdapter;
import com.tvo.htc.view.component.CpnCustomRecyclerView;
import com.tvo.htc.view.main.support.guide.technical_guide.detail.TechnicalGuideDetailFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tvo.htc.util.AppConstants.PAGE_LIMIT;
import static com.tvo.htc.util.AppConstants.START_OFFSET;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class TechnicalGuideFragment extends BaseFragment<TechnicalGuideContract.Presenter> implements TechnicalGuideContract.View, BaseLoadMoreAdapter.OnLoadMoreListener, BaseAdapter.OnItemClickListener {

    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.cpnRecyclerView)
    CpnCustomRecyclerView cpnRecyclerView;

    private TechnicalGuideAdapter adapter;

    public static TechnicalGuideFragment newInstance() {
        Bundle args = new Bundle();
        TechnicalGuideFragment fragment = new TechnicalGuideFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected TechnicalGuideContract.Presenter createPresenterInstance() {
        return new TechnicalGuidePresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_support_guide_technical;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.toString().isEmpty()) {
                    getPresenter().revertCurrentList();
                }
            }
        });
        editSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                FragmentUtil.hideKeyboard(getContext());
                getPresenter().handleSearch(editSearch.getText().toString(), START_OFFSET,false);
            }
            return false;
        });
        getPresenter().loadData(false);
    }

    @Override
    public void displayList(List<TechnicalGuideResponse.Item> items) {
        adapter = new TechnicalGuideAdapter(getContext(), cpnRecyclerView.getRecyclerView(), items, PAGE_LIMIT);
        adapter.setOnLoadMoreListener(this);
        adapter.setOnItemClickListener(this);
        cpnRecyclerView.setCustomRecyclerViewListener(() -> {
            getPresenter().loadData(true);
        });
        cpnRecyclerView.setAdapter(adapter);
    }

    @Override
    public void changeRefreshing(boolean refreshing) {
        cpnRecyclerView.setRefreshing(refreshing);
    }

    @Override
    public void addMoreData(List<TechnicalGuideResponse.Item> items) {
        adapter.addMoreData(items);
    }

    @Override
    public void updateListSearch(List<TechnicalGuideResponse.Item> listSearch) {
        adapter.updateData(listSearch);
    }

    @OnClick(R.id.imSearch)
    public void onViewClicked() {
        getPresenter().handleSearch(editSearch.getText().toString(), START_OFFSET, false);
    }

    @Override
    public void onLoadMore(int skipCount) {
        getPresenter().loadMoreData(skipCount);
    }

    @Override
    public void onItemClick(BaseAdapter adapter, View view, int position) {
        TechnicalGuideResponse.Item item = (TechnicalGuideResponse.Item) adapter.getItemAtPosition(position);
        FragmentUtil.startFragment(getActivity(), TechnicalGuideDetailFragment.newInstance(item), null);
    }
}
