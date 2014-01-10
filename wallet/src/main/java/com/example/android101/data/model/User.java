package com.example.android101.data.model;

public class User {
  public final String id;
  public final String name;
  public final String email;
  public final String bio;
  public final String homepage;
  public final String image;
  public final String location;

    public User(String id, String name, String email, String bio, String homepage, String image, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.homepage = homepage;
        this.image = image;
        this.location = location;
    }
}
