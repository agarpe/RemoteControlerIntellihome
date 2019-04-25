package com.example.alici.remotecontroler;

import android.app.Application;

import com.example.alici.remotecontroler.network.IntelliHomeAPI;
import com.example.alici.remotecontroler.network.LabAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IntelliHomeApplication extends Application {
    public static IntelliHomeAPI service;
    public static LabAPI serviceLab;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.247/IntelliHome/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(IntelliHomeAPI.class);

        Retrofit retrofitLab = new Retrofit.Builder()
                .baseUrl("http://belisama.ii.uam.es:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceLab = retrofitLab.create(LabAPI.class);
    }
}
