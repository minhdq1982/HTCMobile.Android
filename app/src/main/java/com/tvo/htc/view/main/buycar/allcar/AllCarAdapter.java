package com.tvo.htc.view.main.buycar.allcar;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.Car;
import com.android.lib.model.response.CarFilterResponse.Data.CarItem;
import com.tvo.htc.R;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class AllCarAdapter<T> extends BaseAdapter<T, AllCarAdapter.ViewHolderNews> {


    public AllCarAdapter(Context context, List<T> items) {
        super(context, items);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_buy_car_product;
    }

    @Override
    protected ViewHolderNews createViewHolder(View view) {
        return new ViewHolderNews(view);
    }

    @Override
    protected void bindView(ViewHolderNews h, T item, int position) {
        if (item instanceof Car) {
            Car car = (Car) item;
            h.txtTitleProduct.setText(car.getName());
            String images = "";
            if (car.getImages() != null) {
                images = Utils.getImagePath(car.getImages().isEmpty() ? "" : car.getImages().get(0));
            }
            ImageLoader.loadImage(getContext(), h.imgCarProduct, R.drawable.img_no_image, images);
        } else if (item instanceof CarItem) {
            CarItem carItem = (CarItem) item;
            String name = carItem.getCarName() != null ? carItem.getCarName() : "";
            String versionName = carItem.getVersionName() != null ? carItem.getVersionName() : "";
            String image = Utils.getImagePath(carItem.getImage() != null ? carItem.getImage() : "");
            h.txtTitleProduct.setText(name + " " + versionName);
            ImageLoader.loadImage(getContext(), h.imgCarProduct, R.drawable.ic_error_car, image);
        }
    }


    static class ViewHolderNews extends BaseViewHolder {
        @BindView(R.id.imCarPreview)
        ImageView imgCarProduct;
        @BindView(R.id.tvName)
        TextView txtTitleProduct;


        public ViewHolderNews(View view) {
            super(view);
        }

    }

}
