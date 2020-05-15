package com.example.parsetagram;

import android.os.Bundle;

import com.example.parsetagram.adapters.PostAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    public static final String TAG = "PostActivity";

    List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        RecyclerView rvPosts =  findViewById(R.id.rvPosts);
        posts = new ArrayList<>();

        PostAdapter postAdapter = new PostAdapter(this, posts);
        rvPosts.setAdapter(postAdapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(this));

        posts.addAll(getPosts());
        postAdapter.notifyDataSetChanged();

    }

    private List<Post> getPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        List<Post> posts = new ArrayList<>();
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Post post: posts) {
                    Log.i(TAG, "POST: " + post.getDescription() + " username: " + post.getUser().getUsername());
                    posts.add(post);
                }
            }
        });
        return posts;
    }

}
