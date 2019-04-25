package com.example.alici.remotecontroler.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LabAPI {

    @GET("bbhttp/get/Light:lamp{labLight}@amilab!isSwitchable/status.binaryValue")
    Call<String> getLightLab(@Path("labLight") String labLight);

    @POST("bbhttp/set/Light:lamp{labLight}@amilab!isSwitchable/status.binaryValue=1")
    Call<Void> switchOnLightLab(@Path("labLight") String labLight);

    @POST("bbhttp/set/Light:lamp{labLight}@amilab!isSwitchable/status.binaryValue=0")
    Call<Void> switchOffLightLab(@Path("labLight") String labLight);

    @POST("bbhttp/set/DimmableLight:dimlamp1@amilab!isDimmable/level.numericValue={level}")
    Call<Void> changeLevelDimLab(@Path("level") String labLight);


}
