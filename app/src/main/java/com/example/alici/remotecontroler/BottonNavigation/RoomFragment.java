package com.example.alici.remotecontroler.BottonNavigation;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.alici.remotecontroler.R;
import com.example.alici.remotecontroler.VolleyController.AppController;

import org.json.JSONException;
import org.json.JSONObject;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link RoomFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link RoomFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class RoomFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public RoomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RoomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoomFragment newInstance(String param1, String param2) {
        RoomFragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static RoomFragment newInstance() {
        RoomFragment fragment = new RoomFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room, container, false);


        return view;
    }


    public class RoomRequest extends AsyncTask<Void, Void, Boolean> {

        //Parametros de url o lo que sea para crear el objeto
        String urlAction;
        String mode;

        public RoomRequest(String urlAction, String mode) {
            this.urlAction = urlAction;
            this.mode = mode;
        }


        //Lo que se inicia al hacer roomRequ.execute

        @Override
        protected Boolean doInBackground(Void... params) {
            String url = "";
            if (mode.equals("get")) {
                url = urlAction + "room";
            } else if (mode.equals("set")) {
                url = urlAction + "room?state="; //TODO parametro
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
            if (success) {
//               //Si ha ido bien lo que sea
            } else {
                //CAMBIAR POR LO QUE SEA PARA LA APP O QUITAR
                Log.e("OnPostExecute", "Background failed");
                //Toast sale un dialogo en la app
                Toast.makeText(getContext(), "No se han encontrado resultados en su búsqueda", Toast.LENGTH_LONG).show();
            }
        }
    }
    
    
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
