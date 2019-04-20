package com.example.alici.remotecontroler;

import android.app.Application;

import com.example.alici.remotecontroler.network.IntelliHomeAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IntelliHomeApplication extends Application {
    public static IntelliHomeAPI service;

    @Override
    public void onCreate() {
        super.onCreate();

//      public final static String HOST_LAB = "belisama.ii.uam.es:8080";
//      public final static String PORT_LAB = "8080";
        // TODO servicio del lab
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.247/IntelliHome/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(IntelliHomeAPI.class);
    }
}
