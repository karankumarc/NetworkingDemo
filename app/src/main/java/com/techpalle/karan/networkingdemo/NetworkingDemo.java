package com.techpalle.karan.networkingdemo;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.Firebase;

/**
 * Created by ADMIN on 10/1/2016.
 */
public class NetworkingDemo extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
