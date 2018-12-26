package com.tvo.htc.view.main.buycar.allcar;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class AllCarOptionAdapter extends BaseAdapter<String, AllCarOptionAdapter.ViewHolderNews> {

    private int mPosition;

    public AllCarOptionAdapter(Context context, List<String> items) {
        super(context, items);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_option_car;
    }

    @Override
    protected ViewHolderNews createViewHolder(View view) {
        return new ViewHolderNews(view);
    }

    @Override
    protected void bindView(ViewHolderNews h, String item, int position) {
        h.txtText.setText(item);
    }

    class ViewHolderNews extends BaseViewHolder {
        @BindView(R.id.txtText)
        TextView txtText;

        public ViewHolderNews(View view) {
            super(view);
        }

    }

}
