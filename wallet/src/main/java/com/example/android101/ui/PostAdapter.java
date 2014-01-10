package com.example.android101.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android101.R;
import com.example.android101.data.MockData;
import com.example.android101.data.model.Post;
import com.example.android101.data.model.User;
import com.example.android101.util.BindableAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends BindableAdapter<Post> {
    private List<Post> posts = new ArrayList<Post>();

    public PostAdapter(Context context) {
        super(context);
    }

    public void updateMerchants(List<Post> newPosts) {
        this.posts = newPosts;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Post getItem(int position) {
        return posts.get(position);
    }

    @Override
    public View newView(LayoutInflater inflater, int position, ViewGroup container) {
        return inflater.inflate(R.layout.post_item, container, false);
    }

    @Override
    public void bindView(View view, int position, Post post) {
        ImageView imageView = (ImageView) view.findViewById(R.id.user_image);
        User user = MockData.findUserByToken(post.userId);
        int picSize = getContext().getResources().getDimensionPixelSize(R.dimen.post_image_height);
        Picasso.with(getContext())
                .load(user.image)
                .resize(picSize, picSize)
                .centerCrop()
                .placeholder(R.drawable.app_icon)
                .into(imageView);

        TextView textView = (TextView) view.findViewById(R.id.post_content);
        textView.setText(post.content);

        TextView userTextView = (TextView) view.findViewById(R.id.user_name);
        userTextView.setText(user.name);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
