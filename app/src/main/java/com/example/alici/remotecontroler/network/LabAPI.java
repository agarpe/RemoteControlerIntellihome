package com.example.alici.remotecontroler.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LabAPI {

    @GET("/bbhttp/get/Light:lamp2@amilab!isSwitchable/state.binaryValue")
    Call<Integer> getLightLab();

    @GET("/bbhttp/get/Light:lamp2@amilab!isSwitchable/state.binaryValue=1")
    Call<Integer> switchOntLightLab();

    @GET("/bbhttp/get/Light:lamp2@amilab!isSwitchable/state.binaryValue=0")
    Call<Integer> switchOfftLightLab();
}
