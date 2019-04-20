package com.example.alici.remotecontroler.network.async;

import android.os.AsyncTask;

import com.example.alici.remotecontroler.IntelliHomeApplication;
import com.example.alici.remotecontroler.models.Door;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class GetDoorAsyncTask extends AsyncTask<Void, Void, Door> {
    private String place;
    public interface CallbackGetDoor{
        void getDoor(Door light);
    }
    private CallbackGetDoor callback;
    public GetDoorAsyncTask(String place, CallbackGetDoor callback){
        this.place = place;
        this.callback = callback;
    }
    @Override
    protected Door doInBackground(Void... voids) {
        Call<Integer> getDoorReq = IntelliHomeApplication.service.getDoor(place);
        Response<Integer> getDoorResp;
        try {
           getDoorResp = getDoorReq.execute();
           if (getDoorResp.isSuccessful()) return new Door(getDoorResp.body() == 1);
           //Si no funciona por ser entero hacer un custom converter
           //Sale buscando retrofit parse integer
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Door door) {
        super.onPostExecute(door);
        callback.getDoor(door);
    }
}
