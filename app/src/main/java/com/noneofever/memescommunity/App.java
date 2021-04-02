package com.noneofever.memescommunity;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("MemesCom")
                // if defined
                .clientKey("pass")
                .server("http://compute-1.amazonaws.com:1337/parse")
                .build());
    }
}
