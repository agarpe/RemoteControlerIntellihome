package com.example.alici.remotecontroler.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IntelliHomeAPI {

    //     public static final String GETLIGHT1 = "/bbhttp/get/Light:lamp2@amilab!isSwitchable/state.binaryValue" ;
    //     public static final String SETLIGHT1 = "/bbhttp/set/Light:lamp1@amilab!isSwitchable/state.binaryValue=";

    @GET("operations/getLightStatus.php")
    Call<Integer> getLight(@Query("place") String place);

    @POST("operations/changeLightMovil.php")
    Call<Void> setLight(@Query("place") String place, @Query("state") int state);

    @GET("operations/getDoorStatus.php")
    Call<Integer> getDoor(@Query("place") String place);

    @POST("operations/changeDoorMovil.php")
    Call<Void> setDoor(@Query("place") String place, @Query("state") int state);

    @GET("operations/getHumidityStatus.php")
    Call<Integer> getHumidity(@Query("place") String place);

    @POST("operations/changeHumidityMovil.php")
    Call<Void> setHumidity(@Query("place") String place, @Query("hum") int hum);

    @GET("operations/getPresenceInfo.php")
    Call<Integer> getPresence(@Query("place") String place);

    @POST("operations/changePresenceMovil.php")
    Call<Void> setPresence(@Query("place") String place, @Query("state") int state);

    @GET("operations/getSmokeInfo.php")
    Call<Integer> getSmoke(@Query("place") String place);

    @POST("operations/changeSmokeMovil.php?place={place}&state={state}")
    Call<Void> setSmoke(@Query("place") String place, @Query("state") int state);

    @GET("operations/getTemperature.php")
    Call<Integer> getTemperature(@Query("place") String place);

    @POST("operations/changeTemperatureMovil.php")
    Call<Void> setTemperature(@Query("place") String place, @Query("temp") int temp);
}
