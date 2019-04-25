package com.example.alici.remotecontroler.network.async;

import android.os.AsyncTask;

import com.example.alici.remotecontroler.IntelliHomeApplication;
import com.example.alici.remotecontroler.models.Date;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class GetPresenceAsyncTask extends AsyncTask<Void, Void, ArrayList<String>> {
    private String place;

    public interface CallbackGetPresence {
        void getPresence(ArrayList<String> presence);
    }
    private CallbackGetPresence callback;

    public GetPresenceAsyncTask(String place, CallbackGetPresence callback) {
        this.place = place;
        this.callback = callback;
    }
    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        Call<ArrayList<String>> getPresenceReq = IntelliHomeApplication.service.getPresence(place);
        Response<ArrayList<String>> getPresenceResp;
        try {
            getPresenceResp = getPresenceReq.execute();
            if (getPresenceResp.isSuccessful()) return getPresenceResp.body();
            else return new ArrayList<String>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<String> presence) {
        super.onPostExecute(presence);
        callback.getPresence(presence);
    }
}
