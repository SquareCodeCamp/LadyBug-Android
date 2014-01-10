package com.example.android101;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android101.data.ApiHeaders;
import com.example.android101.data.YourService;
import com.example.android101.ui.LoginActivity;
import com.example.android101.ui.NewsfeedActivity;
import com.example.android101.ui.PeopleActivity;
import com.example.android101.ui.ProfileActivity;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_newsfeed: {
                Intent intent  = new Intent(this, NewsfeedActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_people: {
                Intent intent = new Intent(this, PeopleActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_profile: {
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_signout: {
                apiHeaders.clearSession();
                signOut();
                break;
            }
            default: {
                throw new RuntimeException("WTF");
            }
        }

        return true;
    }

    protected ApiHeaders apiHeaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // As you read in NewsfeedActivity, Android has a callback-based, managed lifecycle. For every
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
        //if (!apiHeaders.hasSession()) {
            // Hey! No session therefore the user is logged out. Punt them to login activity.
          //  signOut();
        //}
    }

    private void signOut() {
        startActivity(new Intent(this, LoginActivity.class));
        // Get rid of this screen. We don't want them to come back to this screen if they hit back.
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_actions, menu);

        return true;
    }


}
