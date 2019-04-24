package com.example.alici.remotecontroler.network.async;

import android.os.AsyncTask;

import com.example.alici.remotecontroler.IntelliHomeApplication;
import com.example.alici.remotecontroler.models.Date;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetSmokeAsyncTask extends AsyncTask<Void, Void, ArrayList<Date> > {
    private String place;

    public interface CallbackGetSmoke {
        void getSmoke(ArrayList<Date> smoke);
    }
    private CallbackGetSmoke callback;

    public GetSmokeAsyncTask(String place, CallbackGetSmoke callback) {
        this.place = place;
        this.callback = callback;
    }

    @Override
    protected ArrayList<Date> doInBackground(Void... voids) {
        Call<Integer > getSmokeReq = IntelliHomeApplication.service.getSmoke(place);
        Response<Integer > getSmokeResp;
        try {
            getSmokeResp = getSmokeReq.execute();
            if (getSmokeResp.isSuccessful())
            {
//                smokeRecordString = getSmokeResp.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Date> smokeRecord) {
        super.onPostExecute(smokeRecord);
        callback.getSmoke(smokeRecord);
    }
}
