package com.example.parsetagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class) ;

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("bcid-parsetagram") // should correspond to APP_ID env variable
                .clientKey("LetsHugTotoro")  // set explicitly unless clientKey is explicitly configured on Parse server`
                .server("https://bcid-parsetagram.herokuapp.com/parse").build());
    }
}
