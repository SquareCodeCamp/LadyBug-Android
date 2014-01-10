package com.example.android101.data.model;

/**
 * Created by square on 1/10/14.
 */
public class Post {
    public final String userId;
    public final String content;
    public final String id;

    public Post(String postId, String userId, String content) {
        this.userId = userId;
        this.content = content;
        this.id = postId;
    }
}
