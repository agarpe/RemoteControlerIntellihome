package com.example.alici.remotecontroler.BottonNavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alici.remotecontroler.ListAdapter.AdapterItemSmoke;
import com.example.alici.remotecontroler.ListAdapter.ItemSmoke;
import com.example.alici.remotecontroler.R;
import com.example.alici.remotecontroler.models.Light;
import com.example.alici.remotecontroler.models.Smoke;
import com.example.alici.remotecontroler.network.async.GetHumidityAsyncTask;
import com.example.alici.remotecontroler.network.async.GetLightAsyncTask;
import com.example.alici.remotecontroler.network.async.GetSmokeAsyncTask;
import com.example.alici.remotecontroler.network.async.GetTempAsyncTask;
import com.example.alici.remotecontroler.network.async.SetLightAsyncTask;

import java.util.ArrayList;

//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link KitchenFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link KitchenFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class KitchenFragment extends Fragment {
    private Switch lightSw;
    private TextView tempTextView;
    private TextView humTextView;
    private RecyclerView lvSmoke;

    private RecyclerView.LayoutManager layoutManager;

    public static KitchenFragment newInstance() { return new KitchenFragment(); }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_kitchen, container, false);
        lightSw = view.findViewById(R.id.light_kitchen_switch);
        lightSw.setVisibility(View.INVISIBLE);
        lightSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightSw.setEnabled(false);
                SetLightAsyncTask setLightAsyncTask = new SetLightAsyncTask("kitchen", lightSw.isChecked(), new SetLightAsyncTask.CallbackSetLight() {
                    @Override
                    public void setLight(Boolean success) {
                        lightSw.setEnabled(success);
                    }
                });
                setLightAsyncTask.execute();
            }
        });
        Button rechargeButton = view.findViewById(R.id.kitchen_rechargeButton);
        tempTextView = view.findViewById(R.id.temp_kitchen_content);
        humTextView = view.findViewById(R.id.hum_kitchen_content);
        rechargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTempAsyncTask getTempAsyncTask = new GetTempAsyncTask("kitchen", new GetTempAsyncTask.CallbackGetTemp() {
                    @Override
                    public void getTemp(Integer temp) {
                        tempTextView.setText(temp + " ºC");
                    }
                });
                getTempAsyncTask.execute();
                GetHumidityAsyncTask getHumidityAsyncTask = new GetHumidityAsyncTask("kitchen", new GetHumidityAsyncTask.CallbackGetHum() {
                    @Override
                    public void getHum(Integer hum) {
                        humTextView.setText(hum + " %");
                    }
                });
                getHumidityAsyncTask.execute();
            }
        });

        //TODO get de humo

         lvSmoke = (RecyclerView) view.findViewById(R.id.listview_smoke_kitchen);


         ArrayList<Smoke> smokes = new ArrayList<>();
         smokes.add(new Smoke("01/04/1234"));
        smokes.add(new Smoke("01/04/43636"));
        smokes.add(new Smoke("01/04/5555"));

        // use a linear layout manager
//        layoutManager = new LinearLayoutManager(getContext());
//        lvSmoke.setLayoutManager(layoutManager);


        AdapterItemSmoke adapter = new AdapterItemSmoke(smokes);
        lvSmoke.setAdapter(adapter);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GetLightAsyncTask getLightAsyncTask = new GetLightAsyncTask("kitchen", new GetLightAsyncTask.CallbackGetLight() {
            @Override
            public void getLight(Light light) {
                lightSw.setChecked(light.isOn());
                lightSw.setVisibility(View.VISIBLE);
            }
        });
        getLightAsyncTask.execute();

        GetTempAsyncTask getTempAsyncTask = new GetTempAsyncTask("kitchen", new GetTempAsyncTask.CallbackGetTemp() {
            @Override
            public void getTemp(Integer temp) {
                tempTextView.setText(temp + " ºC");
            }
        });
        getTempAsyncTask.execute();

        GetHumidityAsyncTask getHumidityAsyncTask = new GetHumidityAsyncTask("kitchen", new GetHumidityAsyncTask.CallbackGetHum() {
            @Override
            public void getHum(Integer hum) {
                humTextView.setText(hum + " %");
            }
        });
        getHumidityAsyncTask.execute();

        //TODO get inicial de humo
        GetSmokeAsyncTask getSmokeAsyncTask = new GetSmokeAsyncTask("kitchen", new GetSmokeAsyncTask.CallbackGetSmoke() {
            @Override
            public void getSmoke(ArrayList<Smoke> smoke) {
//                ArrayList<ItemSmoke> category = new ArrayList<ItemSmoke>();
                //Obtiene los datos de itemSmoke a partir de los Smokes
//                category = Smoke.getSmokeCategory(smoke);

                AdapterItemSmoke adapter = new AdapterItemSmoke(smoke);
                lvSmoke.setAdapter(adapter);
            }
        });
//        getSmokeAsyncTask.execute();
    }
}
