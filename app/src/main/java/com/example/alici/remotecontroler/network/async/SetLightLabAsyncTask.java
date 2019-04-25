package com.example.alici.remotecontroler.network.async;

import android.os.AsyncTask;

import com.example.alici.remotecontroler.IntelliHomeApplication;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SetLightLabAsyncTask extends AsyncTask<Void, Void, Boolean> {
    private String labLight;
    private boolean status;

    public interface CallbackSetLight {
        void setLight(Boolean success);
    }

    private CallbackSetLight callback;

    public SetLightLabAsyncTask(String labLight, boolean status, CallbackSetLight callback) {
        this.labLight = labLight;
        this.status = status;
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Call<Void> setLightReq = null;
        if(labLight.equals("regulable")){

        }else {
            if (status) {
                setLightReq = IntelliHomeApplication.serviceLab.switchOnLightLab(labLight);
            } else {
                setLightReq = IntelliHomeApplication.serviceLab.switchOffLightLab(labLight);
            }
        }
        Response<Void> setLightResp;
        try {
            setLightResp = setLightReq.execute();
            return setLightResp.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        super.onPostExecute(success);
        callback.setLight(success);
    }
}
