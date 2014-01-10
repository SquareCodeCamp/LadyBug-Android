package com.example.android101.data;

import com.example.android101.data.model.Post;

/**
 * Created by square on 1/10/14.
 */
public class NewPostBody {
    String token;
    public Post post;

    public NewPostBody(String token, Post post) {
        this.token = token;
        this.post = post;
    }


}
