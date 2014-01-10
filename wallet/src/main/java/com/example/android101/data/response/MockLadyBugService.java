package com.example.android101.data.response;

import android.os.Handler;
import android.os.Looper;

import com.example.android101.data.ApiHeaders;
import com.example.android101.data.MockData;
import com.example.android101.data.NewPostBody;
import com.example.android101.data.YourService;
import com.example.android101.data.model.Post;
import com.example.android101.data.model.User;
import com.example.android101.data.request.LogInBody;

import java.util.List;
import java.util.concurrent.ExecutorService;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by square on 1/10/14.
 */
public class MockLadyBugService implements YourService{

    Handler handler;
    ApiHeaders apiHeaders;
    public MockLadyBugService(ApiHeaders apiHeaders) {
        handler = new Handler(Looper.getMainLooper());
        this.apiHeaders = apiHeaders;
    }
    @Override
    public void login(@Body LogInBody body, final Callback<LogInResponse> cb) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LogInResponse lir = new LogInResponse(true,"Success","Successful login",null,null,MockData.USERS[0],"asdf");
                cb.success(lir,null);
            }
        },1000);

    }

    @Override
    public void listPosts(@Query("limit") int limit, final Callback<List<Post>> callback) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.success(MockData.POSTS, null);
            }
        }, 1000);
    }

    @Override
    public void newPost(@Body final NewPostBody content, final Callback<Post> callback) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Post post = content.post;
                MockData.POSTS.add(0,post);
                callback.success(post, null);
            }
        }, 1000);
    }

    @Override
    public void getUserProfile(@Path("userId") String user, Callback<User> callback) {
        // TODO
    }
}
