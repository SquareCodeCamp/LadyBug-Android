package com.example.android101.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android101.R;
import com.example.android101.data.model.User;
import com.example.android101.util.BindableAdapter;
import com.example.android101.util.SquaredImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MerchantAdapter extends BindableAdapter<User> {
    private List<User> merchants = new ArrayList<User>();

    public MerchantAdapter(Context context) {
        super(context);
    }

    public void updateMerchants(List<User> newMerchants) {
        this.merchants = newMerchants;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return merchants.size();
    }

    @Override
    public User getItem(int position) {
        return merchants.get(position);
    }

    @Override
    public View newView(LayoutInflater inflater, int position, ViewGroup container) {
        return inflater.inflate(R.layout.merchant_item, container, false);
    }

    @Override
    public void bindView(View view, int position, User user) {
        SquaredImageView imageView = (SquaredImageView) view.findViewById(R.id.merchant_image);
        Picasso.with(getContext())
                .load(user.getCuratedUri())
                .resize(300, 300)
                .centerCrop()
                .placeholder(R.drawable.app_icon)
                .into(imageView);

        TextView textView = (TextView) view.findViewById(R.id.merchant_name);
        textView.setText(user.name);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
