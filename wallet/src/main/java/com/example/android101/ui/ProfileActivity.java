package com.example.android101.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android101.R;
import com.example.android101.WalletActivity;
import com.example.android101.data.model.User;
import com.squareup.picasso.Picasso;

/**
 * Created by square on 1/10/14.
 */
public class ProfileActivity extends WalletActivity {

    ImageView userImage;
    TextView userName;
    TextView email;
    TextView bio;
    TextView post1;
    TextView post2;
    TextView post3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFinishing()) return;

        User currentUser = apiHeaders.getUser();

        setContentView(R.layout.profile_activity);

        userName = (TextView) findViewById(R.id.user_name);
        userName.setText(currentUser.name);

        userImage = (ImageView) findViewById(R.id.user_image);
        picasso.load(currentUser.pic)
                .placeholder(R.drawable.app_icon)
                .into(userImage);


        email = (TextView) findViewById(R.id.user_email);
        email.setText(currentUser.email);

        bio = (TextView) findViewById(R.id.user_bio);
        //bio.setText(currentUser.bio);
        bio.setText("This is my bio!!" +
                "I'm a code camper! <3 Square");
        post1 = (TextView) findViewById(R.id.user_post1);
      //  post1.setText("most recent post");

        post2 = (TextView) findViewById(R.id.user_post2);
        //post2.setText("most recent post");

        post3 = (TextView) findViewById(R.id.user_post3);
        //post3.setText("most recent post");

    }

}
