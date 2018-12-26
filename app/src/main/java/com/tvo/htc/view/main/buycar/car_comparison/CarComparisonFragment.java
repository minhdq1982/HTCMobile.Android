package com.tvo.htc.view.main.buycar.car_comparison;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.response.DetailCarResponse;
import com.tvo.htc.R;
import com.tvo.htc.model.CarComparisonSpec;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.main.buycar.allcar.AllCarFragment;
import com.tvo.htc.view.main.buycar.detailcar.DetailCarFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class CarComparisonFragment extends BaseFragment<CarComparisonContract.Presenter>
        implements CarComparisonContract.View, BaseAdapter.OnItemClickListener,
        AllCarFragment.SelectCarListener {

    @BindView(R.id.ivCar1)
    ImageView ivCar1;
    @BindView(R.id.tvCar1)
    TextView tvCar1;
    @BindView(R.id.ivCar2)
    ImageView ivCar2;
    @BindView(R.id.tvCar2)
    TextView tvCar2;
    @BindView(R.id.rvCategory)
    RecyclerView rvCategory;
    @BindView(R.id.reDetail)
    RecyclerView rvDetail;

    private CarSpecGroupAdapter mCarSpecGroupAdapter;
    private List<CarComparisonSpec> mCarComparisonSpecs;
    private DetailCarResponse mDetailCarResponse1;
    private DetailCarResponse mDetailCarResponse2;
    private int mCarComparisonIndex = 1;

    public static CarComparisonFragment newInstance(DetailCarResponse detailCarResponse) {

        Bundle args = new Bundle();

        CarComparisonFragment fragment = new CarComparisonFragment();
        fragment.setArguments(args);
        fragment.mDetailCarResponse1 = detailCarResponse;
        return fragment;
    }


    @Override
    protected CarComparisonContract.Presenter createPresenterInstance() {
        return new CarComparisonPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_car_comparison;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);

        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));

        if (mDetailCarResponse1 != null) {
            tvCar1.setText(mDetailCarResponse1.getData().getName());
        }

        getPresenter().wrapToDisplay(mDetailCarResponse1, null);
    }

    @OnClick({R.id.ivCarInfo1, R.id.ivSwitchCar1, R.id.ivCarInfo2, R.id.ivSwitchCar2})
    void onClick(View view) {
        Fragment fragment;
        switch (view.getId()) {
            case R.id.ivCarInfo1:
                if (mDetailCarResponse1 != null && mDetailCarResponse1.getData() != null) {
                    FragmentUtil.startFragment(getActivity(), DetailCarFragment.newInstance(
                            mDetailCarResponse1.getData().getId()), null);
                }
                break;
            case R.id.ivSwitchCar1:
                fragment = AllCarFragment.newInstance(1, this);
                FragmentUtil.startFragment(getActivity(), fragment, null);
                break;
            case R.id.ivCarInfo2:
                if (mDetailCarResponse2 != null && mDetailCarResponse2.getData() != null) {
                    FragmentUtil.startFragment(getActivity(), DetailCarFragment.newInstance(
                            mDetailCarResponse2.getData().getId()), null);
                }
                break;
            case R.id.ivSwitchCar2:
                fragment = AllCarFragment.newInstance(2, this);
                FragmentUtil.startFragment(getActivity(), fragment, null);
                break;
            default:
                break;
        }
    }

    @Override
    public void displayData(DetailCarResponse carResponse1, DetailCarResponse carResponse2,
                            List<CarComparisonSpec> carComparisonSpecs) {

        mDetailCarResponse1 = carResponse1;
        mDetailCarResponse2 = carResponse2;
        mCarComparisonSpecs = carComparisonSpecs;

        updateInfo();

        mCarSpecGroupAdapter = new CarSpecGroupAdapter(getContext(), carComparisonSpecs);
        mCarSpecGroupAdapter.setOnItemClickListener(this);
        rvCategory.setAdapter(mCarSpecGroupAdapter);

        if (!mCarComparisonSpecs.isEmpty()) {
            setContent(mCarComparisonSpecs.get(0).getItems());
        }
    }

    @Override
    public void onItemClick(BaseAdapter adapter, View view, int position) {
        setContent(mCarComparisonSpecs.get(position).getItems());
    }

    @Override
    public void onCarSelected(int carComparisonIndex, DetailCarResponse detailCarResponse) {
        if (detailCarResponse == null && detailCarResponse.getData() == null) {
            return;
        }

        if (carComparisonIndex == 1) {
            mDetailCarResponse1 = detailCarResponse;
        } else {
            mDetailCarResponse2 = detailCarResponse;
        }

        updateInfo();

        getPresenter().getCarDetail(carComparisonIndex, detailCarResponse.getData());
    }

    private void updateInfo() {
        String image = "";
        if (mDetailCarResponse1 != null && mDetailCarResponse1.getData() != null) {
            if (!mDetailCarResponse1.getData().getImages().isEmpty()) {
                image = Utils.getImagePath(mDetailCarResponse1.getData().getImages().get(0));
            }
            ImageLoader.loadImage(getActivity(), ivCar1, R.drawable.img_no_image, image);
            tvCar1.setText(mDetailCarResponse1.getData().getName());
        }

        if (mDetailCarResponse2 != null && mDetailCarResponse2.getData() != null) {
            if (!mDetailCarResponse2.getData().getImages().isEmpty()) {
                image = Utils.getImagePath(mDetailCarResponse2.getData().getImages().get(0));
            }
            ImageLoader.loadImage(getActivity(), ivCar2, R.drawable.img_no_image, image);
            tvCar2.setText(mDetailCarResponse2.getData().getName());
        }
    }

    private void setContent(List<CarComparisonSpec.Item> items) {
        CarSpecAdapter carSpecAdapter = new CarSpecAdapter(getActivity(), items);
        rvDetail.setAdapter(carSpecAdapter);
    }
}
