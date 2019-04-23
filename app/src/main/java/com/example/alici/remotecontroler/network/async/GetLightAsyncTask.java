package com.example.alici.remotecontroler.network.async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.alici.remotecontroler.IntelliHomeApplication;
import com.example.alici.remotecontroler.models.Light;
import com.example.alici.remotecontroler.network.IntelliHomeAPI;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class GetLightAsyncTask extends AsyncTask<Void, Void, Light> {
    private String place;

    public interface CallbackGetLight {
        void getLight(Light light);
    }

    private CallbackGetLight callback;

    public GetLightAsyncTask(String place, CallbackGetLight callback) {
        this.place = place;
        this.callback = callback;
    }

    @Override
    protected Light doInBackground(Void... voids) {
        Call<Integer> getLightReq = IntelliHomeApplication.service.getLight(place);
        Response<Integer> getLightResp;
        try {
            getLightResp = getLightReq.execute();
            if (getLightResp.isSuccessful()) return new Light(getLightResp.body() == 1);
            //Si no funciona por ser entero hacer un custom converter
            //Sale buscando retrofit parse integer
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(Light light) {
        super.onPostExecute(light);
        callback.getLight(light);
    }
}
