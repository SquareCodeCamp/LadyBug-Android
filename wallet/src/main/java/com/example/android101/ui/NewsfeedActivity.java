package com.example.android101.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android101.R;
import com.example.android101.WalletActivity;
import com.example.android101.data.MockData;
import com.example.android101.data.YourService;
import com.example.android101.data.model.Post;
import com.example.android101.data.model.User;

import java.util.Arrays;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Activities are high-level screens in an app. Similar to pages on a website. Content
 * inside an activity can change based on some state or user interaction. Large changes
 * are easier by just moving to a new activity.
 * <p/>
 * This activity is responsible for display the contents of the directory. We'll present it
 * as a large grid of images in the hopes of dazzling and delighting users--er--customers into
 * forking over their cash.
 */
public class NewsfeedActivity extends WalletActivity {

    ListView listView;
    private PostAdapter adapter;
    EditText newPost;

    /**
     * Android has a managed lifecycle. The operating system creates and destroys things as the user
     * moves throughout the app. We get callbacks at various states. "onCreate" is the first.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Call up the base class. This is where we centralize things like dependency injection and
        // checking for a user. Once this is done (and I've done it all for you) it's rarely touched.
        super.onCreate(savedInstanceState);

        // The base class, WalletActivity, might have called finish() if the user was logged out. We
        // need to check that here and bail because they are being shuffled to the login screen.
        if (isFinishing()) return;

        setContentView(R.layout.newsfeed_activity);

        adapter = new PostAdapter(this);

        newPost = (EditText) findViewById(R.id.new_post);

        newPost.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final TextView textView, int i, KeyEvent keyEvent) {
                yourService.newPost(textView.getText().toString(),new Callback<Post>() {
                    @Override
                    public void success(Post post, Response response) {
                        adapter.addPost(post);
                        textView.setText("");
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Toast.makeText(NewsfeedActivity.this, "Error with post", Toast.LENGTH_LONG).show();
                    }
                });

                return false;
            }
        });

        listView = (ListView) findViewById(R.id.grid_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                Post post = adapter.getItem(position);
                PostActivity.start(post, gson, NewsfeedActivity.this);
            }
        });
        yourService.listPosts(20, new Callback<List<Post>>() {
            @Override
            public void success(List<Post> posts, Response response) {
                adapter.updateMerchants(posts);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(NewsfeedActivity.this, "Error with getting posts", Toast.LENGTH_LONG).show();
            }
        });

    }
}
