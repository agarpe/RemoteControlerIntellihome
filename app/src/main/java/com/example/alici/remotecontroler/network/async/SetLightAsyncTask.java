package com.example.alici.remotecontroler.network.async;

import android.os.AsyncTask;

import com.example.alici.remotecontroler.IntelliHomeApplication;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SetLightAsyncTask extends AsyncTask<Void, Void, Boolean> {
    private String place;
    private boolean status;

    public interface CallbackSetLight {
        void setLight(Boolean success);
    }

    private CallbackSetLight callback;

    public SetLightAsyncTask(String place, boolean status, CallbackSetLight callback) {
        this.place = place;
        this.status = status;
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Call<Void> setLightReq = IntelliHomeApplication.service.setLight(place, status ? 1 : 0);
        Response<Void> setLightResp;
        try {
            setLightResp = setLightReq.execute();
            return setLightResp.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        super.onPostExecute(success);
        callback.setLight(success);
    }
}
