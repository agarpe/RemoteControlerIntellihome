package com.example.alici.remotecontroler.network.async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.alici.remotecontroler.IntelliHomeApplication;
import com.example.alici.remotecontroler.models.Date;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetSmokeAsyncTask extends AsyncTask<Void, Void, ArrayList<String> > {
    private String place;

    public interface CallbackGetSmoke {
        void getSmoke(ArrayList<String> smoke);
    }
    private CallbackGetSmoke callback;

    public GetSmokeAsyncTask(String place, CallbackGetSmoke callback) {
        this.place = place;
        this.callback = callback;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        Call<ArrayList<String>> getSmokeReq = IntelliHomeApplication.service.getSmoke(place);
        Response<ArrayList<String> > getSmokeResp;
        try {
            getSmokeResp = getSmokeReq.execute();
            if (getSmokeResp.isSuccessful())
            {
                Log.d("GETAS",getSmokeResp.body().toString());
                return getSmokeResp.body();
            }
            else
                Log.e("GETAS",getSmokeResp.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<String> smokeRecord) {
        super.onPostExecute(smokeRecord);
        callback.getSmoke(smokeRecord);
    }
}
