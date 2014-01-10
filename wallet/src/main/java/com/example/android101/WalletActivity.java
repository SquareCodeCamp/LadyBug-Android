package com.example.android101;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android101.data.ApiHeaders;
import com.example.android101.data.YourService;
import com.example.android101.ui.LoginActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

/**
 * A base class which handles the common nitty-gritty of every screen.
 * <p/>
 * - Performs dependency injection.
 * - Checks if the user is logged in. If they are not we kick them off to the login screen.
 */
public abstract class WalletActivity extends Activity {
    protected Gson gson;
    protected Picasso picasso;
    protected YourService yourService;
    protected ApiHeaders apiHeaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // As you read in DirectoryActivity, Android has a callback-based, managed lifecycle. For every
        // callback that we override, we have to make sure to call into the base class so that it can
        // react to these events before our code.
        super.onCreate(savedInstanceState);

        // Inject the things we'll be using.
        WalletApp app = (WalletApp) getApplication();
        gson = app.gson;
        picasso = app.picasso;
        yourService = app.yourService;
        apiHeaders = app.apiHeaders;

        // Check if the user is logged in. We just injected this class in the previous line so it
        // is now usable.
        if (!apiHeaders.hasSession()) {
            // Hey! No session therefore the user is logged out. Punt them to login activity.
            signOut();
        }
    }

    private void signOut() {
        startActivity(new Intent(this, LoginActivity.class));
        // Get rid of this screen. We don't want them to come back to this screen if they hit back.
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Sign out").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                apiHeaders.clearSession();
                signOut();
                return true;
            }
        });
        return true;
    }
}
