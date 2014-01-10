package com.example.android101.data;

import com.example.android101.data.model.Post;
import com.example.android101.data.request.LogInBody;
import com.example.android101.data.response.DirectoryMerchantResponse;
import com.example.android101.data.response.LogInResponse;
import com.example.android101.data.response.SimpleResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Retrofit API endpoint definitions for services used by Wallet.
 *
 * If you want to learn more about the syntax in this file see http://square.github.io/retrofit/
 */
public interface YourService {
  @POST("/login") //
  void login( //
      @Body LogInBody body, //
      Callback<LogInResponse> cb);

    @GET("/post") //
    void listPosts( //
        @Query("limit") int limit, //
        Callback<List<Post>> callback);

     @FormUrlEncoded
     @POST("/post") //
    void newPost( //
        @Field("content") String content, //
        Callback<Post> callback);

}
