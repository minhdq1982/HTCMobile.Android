package com.tvo.htc.view.main.support.findlocation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.lib.model.response.AgenciesResponse;
import com.tvo.htc.R;

import java.util.List;

public class FindLocationPagerAdapter extends PagerAdapter {

    private Context context;
    private List<AgenciesResponse.Agency> itemsList;
    private LayoutInflater inflater;
    private int mType;
    private HotLineCallBack callBack;

    public FindLocationPagerAdapter(Context context, List<AgenciesResponse.Agency> itemsList, int type, HotLineCallBack callBack) {
        this.context = context;
        this.itemsList = itemsList;
        this.mType = type;
        this.callBack = callBack;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((View) o);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_detail_location, container, false);
        AgenciesResponse.Agency item = itemsList.get(position);

        TextView txtTitleLocation = view.findViewById(R.id.txtTitleLocation);
        TextView txtAddressLocation = view.findViewById(R.id.txtAddressLocation);
        TextView txtHotlineCare = view.findViewById(R.id.txtHotlineCare);
        TextView txtHotlineShop = view.findViewById(R.id.txtHotlineShop);
        TextView txtHotlineService = view.findViewById(R.id.txtHotlineService);
        TextView txtWebsiteLocation = view.findViewById(R.id.txtWebsiteLocation);

        //Set text
        txtTitleLocation.setText(item.getName());
        txtAddressLocation.setText(item.getAddress());
        txtHotlineCare.setText(item.getHotline());
        txtHotlineShop.setText(item.getHotlineSale());
        txtHotlineService.setText(item.getHotlineService());
        txtWebsiteLocation.setText(item.getWebsite());

        //Hotline callback
        txtHotlineCare.setOnClickListener(v -> callBack.setHotlineCallback(txtHotlineCare.getText().toString()));
        txtHotlineShop.setOnClickListener(v -> callBack.setHotlineCallback(txtHotlineShop.getText().toString()));
        txtHotlineService.setOnClickListener(v -> callBack.setHotlineCallback(txtHotlineService.getText().toString()));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    interface HotLineCallBack {
        void setHotlineCallback(String hotline);
    }
}
