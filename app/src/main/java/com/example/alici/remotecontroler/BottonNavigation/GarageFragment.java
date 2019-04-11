package com.example.alici.remotecontroler.BottonNavigation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.alici.remotecontroler.R;
import com.example.alici.remotecontroler.VolleyController.AppController;

import org.json.JSONException;
import org.json.JSONObject;

//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link GarageFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link GarageFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class GarageFragment extends Fragment {

    private static final int OPENTIME = 10000;
    private long timeIni = 0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean doorState = false; //false closed
    private boolean lightState = false; //false OFF
    private GarageRequest lightRequest;


    public GarageFragment() {
        // Required empty public constructor
    }

    public static GarageFragment newInstance(String param1, String param2) {
        GarageFragment fragment = new GarageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_garage, container, false);


        final Switch lightSw = (Switch) view.findViewById(R.id.light_garage_switch);
        lightSw.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Encender luz
                Toast.makeText(v.getContext(),"Luz del garage",Toast.LENGTH_LONG).show();
                String mode = "";
                if(lightState)
                {
                    mode = "0";
                    lightState = false;
                }
                else
                {
                    mode = "1";
                    lightState = true;
                }


                lightRequest = new GarageRequest(AppController.SETLIGHT1,mode);
                lightRequest.execute();

            }
        });

        final Switch doorSw = (Switch) view.findViewById(R.id.door_garage_switch);

        doorSw.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                long difTime = System.currentTimeMillis();
                long max = timeIni+OPENTIME;
                System.out.println("DIF TIME "+difTime+" "+max);
                //If door opening is on progress
                if(difTime >= timeIni && difTime < timeIni+OPENTIME)
                {
                    String action = "";
                    String actionTitle = "";
                    if(doorSw.isChecked()) {
                        action = "cierra";
                        actionTitle = "Cerrando";
                    }
                    else
                    {
                        action = "abre";
                        actionTitle = "Abriendo";
                    }

                    doorSw.setChecked(doorState);

                    new AlertDialog.Builder(v.getContext())
                            .setTitle(actionTitle+" puerta")
                            .setMessage("Espere mientras la puerta se  "+action+ "...").show();

                }
                //If door open/closed do opposite
                else
                {
                    timeIni = System.currentTimeMillis();
                    doorState = doorSw.isChecked();

                    String action = "";
                    if(!doorSw.isChecked())
                        action = "Cerrar";
                    else
                        action = "Abrir";

                    //Send request
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Mandar petición")
                            .setMessage("Petición para "+action).show();
                }

            }

        });


        return view;
    }



    public class GarageRequest extends AsyncTask<Void, Void, Boolean> {

        String urlAction;
        String mode;

        public GarageRequest(String urlAction, String mode) {
            this.urlAction = urlAction;
            this.mode = mode;
        }


        @Override
        protected Boolean doInBackground(Void... params) {
            String url = "";
            if(mode.equals("get")){
                url = urlAction + "kitchen";
            }else if(mode.equals("set")){
                url = urlAction + "kitchen?state="; //TODO parametro
            }
            //URL PARA CREAR EL CONSTRUCTOR
            String urlJsonObj = AppController.PROTOCOL + AppController.INTELLIHOME + url;

            Log.d(AppController.TAG, "URL: " + urlJsonObj);

            //Objeto JSON para request
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,
                    urlJsonObj, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    Log.d(AppController.TAG, response.toString());
                    try {
                        // Parsing json object response
                        // response will be a json object

                        Log.d(AppController.TAG, response.toString());

                        //Cambiar dentro de getstring por el parámetro de json que se quiera
                        String code = response.getString("state");



                    } catch (JSONException e) {
                        Log.e("ResponseParser", "Error: JSON error en parseUser" + response);
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(AppController.TAG, "Error: " + error.getMessage());
                }
            });

            AppController.getInstance().addToRequestQueue(req);

            //Estos logs salen en el run
            Log.d(AppController.TAG, "Request added to queue");


            //Este es el "success" que le llega al postExecute
            return true;
        }


        @Override
        protected void onPostExecute(final Boolean success) {

            if(urlAction == AppController.GETLIGHT1)
            {
//                if
            }
        }

        @Override
        protected void onCancelled() {
        }


    }



}
