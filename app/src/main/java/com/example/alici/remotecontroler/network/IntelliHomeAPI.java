package com.example.alici.remotecontroler.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IntelliHomeAPI {

    //     public static final String GETLIGHT1 = "/bbhttp/get/Light:lamp2@amilab!isSwitchable/state.binaryValue" ;
    //     public static final String SETLIGHT1 = "/bbhttp/set/Light:lamp1@amilab!isSwitchable/state.binaryValue=";

    @GET("operations/getLightStatus.php?place={place}")
    Call<Integer> getLight(@Path("place") String place);

    @POST("operations/changeLightMovil.php?place={place}&state={state}")
    Call<Void> setLight(@Path("place") String place, @Path("state") int state);

    @GET("operations/getDoorStatus.php?place={place}")
    Call<Integer> getDoor(@Path("place") String place);

    @POST("operations/changeDoorMovil.php?place={place}&state={state}")
    Call<Void> setDoor(@Path("place") String place, @Path("state") int state);

    @GET("operations/getHumidityStatus.php?place={place}")
    Call<Integer> getHumidity(@Path("place") String place);

    @POST("operations/changeHumidityMovil.php?place={place}&hum={hum}")
    Call<Void> setHumidity(@Path("place") String place, @Path("hum") int hum);

    @GET("operations/getPresenceInfo.php?place={place}")
    Call<Integer> getPresence(@Path("place") String place);

    @POST("operations/changePresenceMovil.php?place={place}&state={state}")
    Call<Void> setPresence(@Path("place") String place, @Path("state") int state);

    @GET("operations/getSmokeInfo.php?place={place}")
    Call<Integer> getSmoke(@Path("place") String place);

    @POST("operations/changeSmokeMovil.php?place={place}&state={state}")
    Call<Void> setSmoke(@Path("place") String place, @Path("state") int state);

    @GET("operations/getTemperature.php?place={place}")
    Call<Integer> getTemperature(@Path("place") String place);

    @POST("operations/changeTemperatureMovil.php?place={place}&temp={temp}")
    Call<Void> setTemperature(@Path("place") String place, @Path("temp") int temp);
}
