package com.example.alici.remotecontroler.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LabAPI {

    @GET("/bbhttp/get/Light:lamp{labLight}@amilab!isSwitchable/state.binaryValue")
    Call<Integer> getLightLab(@Path("labLight") String labLight);

    @POST("/bbhttp/get/Light:lamp{labLight}@amilab!isSwitchable/state.binaryValue=1")
    Call<Void> switchOnLightLab(@Path("labLight") String labLight);

    @POST("/bbhttp/get/Light:lamp{labLight}@amilab!isSwitchable/state.binaryValue=0")
    Call<Void> switchOffLightLab(@Path("labLight") String labLight);

}
