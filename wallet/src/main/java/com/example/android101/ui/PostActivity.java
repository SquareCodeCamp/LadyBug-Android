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
import com.example.android101.data.MockData;
import com.example.android101.data.model.Post;
import com.example.android101.data.model.User;
import com.example.android101.data.response.SimpleResponse;
import com.google.gson.Gson;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by square on 1/10/14.
 */
public class PostActivity extends WalletActivity{

    ImageView userImage;
    TextView userName;
    TextView postContent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFinishing()) return;

        setContentView(R.layout.post_activity);

        final Post post = gson.fromJson(getIntent().getStringExtra("postExtra"), Post.class);
        final User user = MockData.findUserByToken(post.userId);

        userName = (TextView) findViewById(R.id.user_name);
        userName.setText(user.name);

        userImage = (ImageView) findViewById(R.id.user_image);
        picasso.load(user.image).into(userImage);

        postContent = (TextView) findViewById(R.id.post_content);
        postContent.setText(post.content);

    }

    public static void start(Post post, Gson gson, Context context) {
        String postJson = gson.toJson(post);
        Intent intent = new Intent(context, PostActivity.class);
        intent.putExtra("postExtra", postJson);
        context.startActivity(intent);
    }
}
