package com.example.android101.data.model;

public class User {
  public final String id;
  public final String name;
  public final String email;
  public final String bio;
  public final String homepage;
  public final String pic;

    public User(String id, String name, String email, String bio, String homepage, String pic) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.homepage = homepage;
        this.pic = pic;
    }
    //[{"id":1,"name":"sean","lat":null,"lon":null,"email":"seansorrell@gmail.com","bio":"i am cool","pic":null,"created_at":"2014-01-10T22:54:41.993Z","updated_at":"2014-01-10T22:54:41.993Z","password":null}
}
