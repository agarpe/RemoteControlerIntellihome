package com.example.alici.remotecontroler.network.async;

import android.os.AsyncTask;

import com.example.alici.remotecontroler.IntelliHomeApplication;
import com.example.alici.remotecontroler.models.Light;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class GetLightLabAsyncTask extends AsyncTask<Void, Void, Light> {
    private String labLight;

    public interface CallbackGetLight {
        void getLight(Light light);
    }

    private CallbackGetLight callback;

    public GetLightLabAsyncTask(String labLight, CallbackGetLight callback) {
        this.labLight = labLight;
        this.callback = callback;
    }

    @Override
    protected Light doInBackground(Void... voids) {
        Call<String> getLightReq = IntelliHomeApplication.serviceLab.getLightLab(labLight);
        Response<String> getLightResp;
        try {
            getLightResp = getLightReq.execute();
            if (getLightResp.isSuccessful()){
                //la respuesta es del tipo "binaryValue=1;" o "binaryValue=0"
                String respBody = getLightResp.body();
                String lightStatus = respBody.substring(respBody.length() - 2, respBody.length() - 1);
                return new Light(lightStatus.equals("1"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Light(false);

    }

    @Override
    protected void onPostExecute(Light light) {
        super.onPostExecute(light);
        callback.getLight(light);
    }
}
