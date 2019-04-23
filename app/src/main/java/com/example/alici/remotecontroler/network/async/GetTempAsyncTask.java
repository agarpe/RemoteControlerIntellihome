package com.example.alici.remotecontroler.network.async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.alici.remotecontroler.IntelliHomeApplication;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class GetTempAsyncTask extends AsyncTask<Void, Void, Integer> {
    private String place;

    public interface CallbackGetTemp {
        void getTemp(Integer temp);
    }
    private CallbackGetTemp callback;

    public GetTempAsyncTask(String place, CallbackGetTemp callback) {
        this.place = place;
        this.callback = callback;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        Call<Integer> getTempReq = IntelliHomeApplication.service.getTemperature(place);
        Response<Integer> getTempResp;
        try {
            getTempResp = getTempReq.execute();
            if (getTempResp.isSuccessful())
            {
                Log.d("GETASTEMP",getTempResp.body().toString());
                return getTempResp.body();
            }
            else
                Log.e("GETASTEMP",getTempResp.errorBody().toString());

            //Si no funciona por ser entero hacer un custom converter
            //Sale buscando retrofit parse integer
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer temp) {
        super.onPostExecute(temp);
        callback.getTemp(temp);
    }
}
