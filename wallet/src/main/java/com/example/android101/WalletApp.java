package com.example.android101;

import android.app.Application;
import android.content.Context;

import com.example.android101.data.ApiHeaders;
import com.example.android101.data.YourService;
import com.example.android101.data.model.User;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import retrofit.RestAdapter;

/**
 * Our custom application class for Wallet. There will only every be one instance of this class
 * in each process (and unless you explicitly ask otherwise, there's only one process per-app).
 * We use this class to initialize and manage global things like dependency injection.
 */
public class WalletApp extends Application {

    public Gson gson;
    public Picasso picasso;
    public ApiHeaders apiHeaders;
    public YourService yourService;
    public User currentUser;


    @Override
    public void onCreate() {
        super.onCreate();
        currentUser = new User("1234","User Test","asdf@asdf.com","fdklafdkafd;k","asdf",null,"texas, paris");
        WalletModule walletModule = new WalletModule(this);

        gson = new Gson();
        picasso = Picasso.with(this);
        apiHeaders = walletModule.provideApiHeaders();

        OkHttpClient client = walletModule.provideOkHttpClient();
        RestAdapter restAdapter = walletModule.provideRestAdapter(client, apiHeaders);
        yourService = walletModule.provideWalletService(restAdapter);
    }

    public static User getCurrentUser(Context context) {
        return ((WalletApp)context.getApplicationContext()).currentUser;
    }
}
