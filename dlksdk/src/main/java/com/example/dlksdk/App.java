package com.example.dlksdk;

import android.app.Application;

import org.xutils.x;


public class App extends Application {

    public static int pass=5000;


    public static int passControl=3;



    @Override
    public void onCreate() {
        super.onCreate();


        x.Ext.init(this);
        x.Ext.setDebug(true);



    }

}
