package com.example.alici.remotecontroler.network.async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.alici.remotecontroler.IntelliHomeApplication;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class GetHumidityAsyncTask extends AsyncTask<Void, Void, Integer> {
    private String place;

    public interface CallbackGetHum {
        void getHum(Integer hum);
    }
    private CallbackGetHum callback;

    public GetHumidityAsyncTask(String place, CallbackGetHum callback) {
        this.place = place;
        this.callback = callback;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        Call<Integer> getHumReq = IntelliHomeApplication.service.getHumidity(place);
        Response<Integer> getHumResp;
        try {
            getHumResp = getHumReq.execute();
            if (getHumResp.isSuccessful())
            {
                Log.d("GETASHUM",getHumResp.body().toString());
//                Log.d("GETASHUM", getHumResp.raw().message());
                return getHumResp.body();
            }
            else
                Log.e("GETASHUM",getHumResp.errorBody().toString());
//            Log.e("GETASHUM", getHumResp.raw().message());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer hum) {
        super.onPostExecute(hum);
        callback.getHum(hum);
    }
}
