package com.example.android101.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android101.R;
import com.example.android101.WalletActivity;
import com.example.android101.WalletApp;
import com.example.android101.data.MockData;
import com.example.android101.data.model.Post;
import com.example.android101.data.model.User;
import com.google.gson.Gson;

/**
 * Created by square on 1/10/14.
 */
public class ProfileActivity extends WalletActivity {

    ImageView userImage;
    TextView userName;
    TextView location;
    TextView email;
    TextView bio;
    TextView post1;
    TextView post2;
    TextView post3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFinishing()) return;

        User currentUser = WalletApp.getCurrentUser(this);

        setContentView(R.layout.profile_activity);

        userName = (TextView) findViewById(R.id.user_name);
        userName.setText(currentUser.name);

        userImage = (ImageView) findViewById(R.id.user_image);
        picasso.load(currentUser.image).into(userImage);

        location = (TextView) findViewById(R.id.user_location);
        location.setText(currentUser.location);

        email = (TextView) findViewById(R.id.user_email);
        email.setText(currentUser.email);

        bio = (TextView) findViewById(R.id.user_bio);
        xbio.setText(currentUser.bio);

        post1 = (TextView) findViewById(R.id.user_post1);
        post1.setText("most recent post");

        post2 = (TextView) findViewById(R.id.user_post2);
        post2.setText("most recent post");

        post3 = (TextView) findViewById(R.id.user_post3);
        post3.setText("most recent post");

    }

}
