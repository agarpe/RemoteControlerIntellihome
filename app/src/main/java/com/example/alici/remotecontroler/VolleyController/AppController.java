package com.example.alici.remotecontroler.VolleyController;


import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AppController extends Application {

    public final static String PROTOCOL = "http://";
    //    public final static String host = "192.168.43.33";
    public final static String HOST_LAB = "belisama.ii.uam.es:8080";
    public final static String PORT_LAB = "8080";
    public final static String INTELLIHOME = "192.168.1.44";


    public static final String TAG = AppController.class.getSimpleName();
    public static final int MAXWAIT = 4000;
    public static final String GETLIGHT1 = "/bbhttp/get/Light:lamp2@amilab!isSwitchable/status.binaryValue" ;
    public static final String SETLIGHT1 = "/bbhttp/set/Light:lamp1@amilab!isSwitchable/status.binaryValue=";
    public static final String GETLIGHT = "/operations/getLightStatus.php?room=";
    public static final String SETLIGHT = "/operations/changeLight.php?";
    public static final String GETDOOR = "/operations/getDoorStatus.php?room=";
    public static final String SETDOOR = "/operations/changeDoor.php?";
    public static final String GETHUMIDITY = "/operations/getHumidity.php?room=";
    public static final String SETHUMIDITY = "/operations/changeHumidity.php?";
    public static final String GETPRESENCE = "/operations/getPresenceInfo.php?room=";
    public static final String SETPRESENCE = "/operations/changePresence.php?";
    public static final String GETSMOKE = "/operations/getSmokeInfo.php?room=";
    public static final String SETSMOKE = "/operations/changeSmoke.php?";
    public static final String GETTEMPERATURE = "/operations/getTemperature.php?room=";
    public static final String SETTEMPERATURE = "/operations/changeTemperature.php?";



    private String jsonResponse;

    private JSONObject requestResponse = new JSONObject();

    private RequestQueue mRequestQueue;

    private static AppController mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }



}