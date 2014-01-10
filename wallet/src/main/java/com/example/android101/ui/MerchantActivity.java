package com.example.android101.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android101.R;
import com.example.android101.WalletActivity;
import com.example.android101.WalletApp;
import com.example.android101.data.model.User;
import com.example.android101.data.response.SimpleResponse;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MerchantActivity extends WalletActivity {
    public static final String EXTRA_USER = "user_json";

    private TextView merchantName;
    private ImageView merchantImage;
    private TextView merchantPhone;
    private Button openTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFinishing()) return;

        setContentView(R.layout.merchant_activity);

        final User user = gson.fromJson(getIntent().getStringExtra(EXTRA_USER), User.class);

        merchantName = (TextView) findViewById(R.id.merchant_name);
        merchantName.setText(user.name);

        merchantPhone = (TextView) findViewById(R.id.merchant_phone);
        merchantPhone.setText(user.email);

        merchantImage = (ImageView) findViewById(R.id.merchant_image);
        Picasso.with(this).load(user.getCuratedUri()).into(merchantImage);

        openTab = (Button) findViewById(R.id.open_tab);
        openTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTab.setEnabled(false);
                yourService.openTab(user.id, new Callback<SimpleResponse>() {
                    @Override
                    public void success(SimpleResponse simpleResponse, Response response) {
                        Toast.makeText(MerchantActivity.this, "You opened a Tab!", Toast.LENGTH_SHORT).show();
                        openTab.setText("Tab Opened!");
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        openTab.setEnabled(true);
                        Toast.makeText(MerchantActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public static void start(Context context, User user) {
        String userString = ((WalletApp) context.getApplicationContext()).gson.toJson(user);

        Intent intent = new Intent(context, MerchantActivity.class);
        intent.putExtra(MerchantActivity.EXTRA_USER, userString);
        context.startActivity(intent);
    }

}
