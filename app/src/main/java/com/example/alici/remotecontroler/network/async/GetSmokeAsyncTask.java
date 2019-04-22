package com.example.alici.remotecontroler.network.async;

import android.os.AsyncTask;

import com.example.alici.remotecontroler.IntelliHomeApplication;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class GetSmokeAsyncTask extends AsyncTask<Void, Void, /*TODO clase del objeto que devuelva*/> {
    private String place;

    public interface CallbackGetSmoke {
        void getSmoke(Integer smoke);
    }
    private CallbackGetSmoke callback;

    public GetSmokeAsyncTask(String place, CallbackGetSmoke callback) {
        this.place = place;
        this.callback = callback;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        Call<Integer> getSmokeReq = IntelliHomeApplication.service.getSmoke(place);
        Response<Integer> getSmokeResp;
        try {
            getSmokeResp = getSmokeReq.execute();
            if (getSmokeResp.isSuccessful()) return // TODO new array de smokes con getSmokeResp.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(/*TODO clase del parámetro que devuelva*/ smoke) {
        super.onPostExecute(smoke);
        callback.getSmoke(smoke);
    }
}
