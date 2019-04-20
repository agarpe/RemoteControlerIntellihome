package com.example.alici.remotecontroler.network.async;

import android.os.AsyncTask;

import com.example.alici.remotecontroler.IntelliHomeApplication;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SetDoorAsyncTask extends AsyncTask<Void, Void, Boolean> {
    private String place;
    private boolean status;

    public interface CallbackSetDoor{
        void setDoor(Boolean success);
    }
    private CallbackSetDoor callback;

    public SetDoorAsyncTask(String place, boolean status, CallbackSetDoor callback){
        this.place = place;
        this.status = status;
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Call<Void> setDoorReq = IntelliHomeApplication.service.setDoor(place, status ? 1 : 0);
        Response<Void> setDoorResp;
        try {
           setDoorResp = setDoorReq.execute();
           return setDoorResp.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        super.onPostExecute(success);
        callback.setDoor(success);
    }
}
