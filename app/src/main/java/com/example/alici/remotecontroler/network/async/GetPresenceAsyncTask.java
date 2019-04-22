package com.example.alici.remotecontroler.network.async;

import android.os.AsyncTask;

import com.example.alici.remotecontroler.IntelliHomeApplication;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class GetPresenceAsyncTask extends AsyncTask<Void, Void, /*TODO clase del objeto que devuelva*/> {
    private String place;

    public interface CallbackGetPresence {
        void getPresence(Integer presence);
    }
    private CallbackGetPresence callback;

    public GetPresenceAsyncTask(String place, CallbackGetPresence callback) {
        this.place = place;
        this.callback = callback;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        Call<Integer> getPresenceReq = IntelliHomeApplication.service.getPresence(place);
        Response<Integer> getPresenceResp;
        try {
            getPresenceResp = getPresenceReq.execute();
            if (getPresenceResp.isSuccessful()) return // TODO new array de presences con getPresenceResp.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(/*TODO clase del parámetro que devuelva*/ presence) {
        super.onPostExecute(presence);
        callback.getPresence(presence);
    }
}
