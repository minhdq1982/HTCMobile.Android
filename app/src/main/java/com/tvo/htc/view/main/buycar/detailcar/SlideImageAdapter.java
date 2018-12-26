package com.tvo.htc.view.main.buycar.detailcar;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tvo.htc.R;
import com.tvo.htc.util.ImageLoader;

import java.util.List;

public class SlideImageAdapter extends PagerAdapter {
    private List<String> mList;

    public SlideImageAdapter(List<String> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.item_detail_car_slide, container, false);
        ImageView imageView = v.findViewById(R.id.ivIconCar);
        ImageLoader.loadImage(container.getContext(), imageView, 0, mList.get(position));
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
