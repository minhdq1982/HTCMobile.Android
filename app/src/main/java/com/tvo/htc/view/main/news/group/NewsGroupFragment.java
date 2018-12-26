package com.tvo.htc.view.main.news.group;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.model.response.FacebookGroupResponse;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;

import java.util.List;

import butterknife.BindView;

public class NewsGroupFragment extends BaseFragment<NewsGroupContract.Presenter> implements NewsGroupContract.View, BaseAdapter.OnItemClickListener {

    @BindView(R.id.rvGroup)
    RecyclerView rvGroup;

    List<FacebookGroupResponse.Group.Items> mList;

    private boolean isVisibleToUser;

    public static Fragment newInstance() {
        Bundle args = new Bundle();

        NewsGroupFragment fragment = new NewsGroupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected NewsGroupContract.Presenter createPresenterInstance() {
        return new NewsGroupPresenter(getContext());
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);

        if (isVisibleToUser && mList == null) {
            getPresenter().loadData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && mList == null && getPresenter() != null) {
            getPresenter().loadData();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_group;
    }

    @Override
    public void getData(List<FacebookGroupResponse.Group.Items> data) {
        mList = data;
        NewsGroupAdapter newsGroupAdapter = new NewsGroupAdapter(getContext(), data);
        rvGroup.setLayoutManager(new LinearLayoutManager(getContext()));
        rvGroup.setAdapter(newsGroupAdapter);
        newsGroupAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseAdapter adapter, View view, int position) {
        String url = mList.get(position).getLink();
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPageURL(getContext(), url);
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
    }


    public String getFacebookPageURL(Context context, String id) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            ApplicationInfo ai = getActivity().getPackageManager().getApplicationInfo("com.facebook.katana", 0);
            if (ai.enabled) {
                if (versionCode >= 3002850) { //newer versions of fb app
                    return "fb://facewebmodal/f?href=" + id;
                } else { //older versions of fb app
                    return id;
                }
            } else {
                return id;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return id; //normal web url
        } catch (ActivityNotFoundException e) {
            return id; //normal web url
        }
    }
}
