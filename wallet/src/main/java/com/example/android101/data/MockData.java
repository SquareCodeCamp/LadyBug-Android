package com.example.android101.data;

import android.location.Location;

import com.example.android101.data.model.Post;
import com.example.android101.data.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Fake data. Because real data is hard! */
public class MockData {
  public static final Location SQUARE = locationAt(37.782436, -122.406621, 40f);

  private static Location locationAt(double lat, double lon, float accuracy) {
    Location location = new Location("gps");
    location.setLatitude(lat);
    location.setLongitude(lon);
    location.setAccuracy(accuracy);
    return location;
  }

  public static User findUserByToken(String token) {
      for (User user : USERS) {
          if(user.id.equals(token)) {
              return user;
          }
      }

      throw new IllegalArgumentException("No known user w/ token: " + token);
  }

  public static final User[] USERS = new User[] {
      new User("123","Ashley","ashley3@mit.edu","lame","https://www.google.com",null,"Boston, MA"),
      new User("456", "Shana", "shanahu@berkeley.edu", "lol", "", null, "Berkeley"),
      new User("789", "Holmes", "holmes.j@gmail.com", "The guy", null, null, "SF!")
  };

  public static final List<Post> POSTS = new ArrayList<Post>(Arrays.asList(new Post[]{
          new Post("1", "123", "hello world"),
          new Post("2", "456", "code camp"),
          new Post("3", "456", "code camp"),
          new Post("4", "456", "code camp"),
          new Post("5", "456", "code camp"),
          new Post("6", "456", "code campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode campcode camp")

  }));
}
