package com.example.alici.remotecontroler.BottonNavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.alici.remotecontroler.ListAdapter.AdapterItemDate;
import com.example.alici.remotecontroler.R;
import com.example.alici.remotecontroler.models.Door;
import com.example.alici.remotecontroler.models.Light;
import com.example.alici.remotecontroler.network.async.GetDoorAsyncTask;
import com.example.alici.remotecontroler.network.async.GetLightAsyncTask;
import com.example.alici.remotecontroler.network.async.GetPresenceAsyncTask;
import com.example.alici.remotecontroler.network.async.GetSmokeAsyncTask;
import com.example.alici.remotecontroler.network.async.SetDoorAsyncTask;
import com.example.alici.remotecontroler.network.async.SetLightAsyncTask;

import java.util.ArrayList;
import java.util.Collections;

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
    private Switch lightSw;
    private Switch doorSw;

    private RecyclerView lvSmoke;
    private RecyclerView lvPresence;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public static GarageFragment newInstance() {
        return new GarageFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_garage, container, false);
        lightSw = view.findViewById(R.id.light_garage_switch);
        lightSw.setVisibility(View.INVISIBLE);
        lightSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightSw.setEnabled(false);
                SetLightAsyncTask setLightAsyncTask = new SetLightAsyncTask("garage", lightSw.isChecked(), new SetLightAsyncTask.CallbackSetLight() {
                    @Override
                    public void setLight(Boolean success) {
                        lightSw.setEnabled(success);
                    }
                });
                setLightAsyncTask.execute();
            }
        });

        doorSw = view.findViewById(R.id.door_garage_switch);
        doorSw.setVisibility(View.INVISIBLE);
        doorSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doorSw.setEnabled(false);
                SetDoorAsyncTask setDoorAsyncTask = new SetDoorAsyncTask("garage", doorSw.isChecked(), new SetDoorAsyncTask.CallbackSetDoor() {
                    @Override
                    public void setDoor(Boolean success) {
                        //TODO esperar tiempo de apertura de la puerta
                        doorSw.setEnabled(success);
                    }
                });
                setDoorAsyncTask.execute();

            }
        });

        FloatingActionButton updateBt = view.findViewById(R.id.update_button_garage);
        updateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetSmokeAsyncTask getSmokeAsyncTask = new GetSmokeAsyncTask("garage", new GetSmokeAsyncTask.CallbackGetSmoke() {
                    @Override
                    public void getSmoke(ArrayList<String> smoke) {

                        layoutManager = new LinearLayoutManager(getContext());
                        lvSmoke.setLayoutManager(layoutManager);

                        Collections.reverse(smoke);
                        smoke.addAll(smoke);
                        adapter = new AdapterItemDate(smoke);
                        lvSmoke.setAdapter(adapter);

                    }
                });
                getSmokeAsyncTask.execute();

                GetPresenceAsyncTask getPresenceAsyncTask = new GetPresenceAsyncTask("garage", new GetPresenceAsyncTask.CallbackGetPresence() {
                    @Override
                    public void getPresence(ArrayList<String> presence) {

                        layoutManager = new LinearLayoutManager(getContext());
                        lvPresence.setLayoutManager(layoutManager);

                        Collections.reverse(presence);
                        adapter = new AdapterItemDate(presence);
                        lvPresence.setAdapter(adapter);

                    }
                });
                getPresenceAsyncTask.execute();
            }
        });

        lvPresence = view.findViewById(R.id.presence_list_garage);
        lvSmoke = view.findViewById(R.id.smoke_list_garage);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GetLightAsyncTask getLightAsyncTask = new GetLightAsyncTask("garage", new GetLightAsyncTask.CallbackGetLight() {
            @Override
            public void getLight(Light light) {
                lightSw.setChecked(light.isOn());
                lightSw.setVisibility(View.VISIBLE);
            }
        });
        getLightAsyncTask.execute();

        GetDoorAsyncTask getDoorAsyncTask = new GetDoorAsyncTask("garage", new GetDoorAsyncTask.CallbackGetDoor() {
            @Override
            public void getDoor(Door door) {
                doorSw.setChecked(door.isOpen());
                doorSw.setVisibility(View.VISIBLE);
            }
        });
        getDoorAsyncTask.execute();
        GetSmokeAsyncTask getSmokeAsyncTask = new GetSmokeAsyncTask("garage", new GetSmokeAsyncTask.CallbackGetSmoke() {
            @Override
            public void getSmoke(ArrayList<String> smoke) {

                layoutManager = new LinearLayoutManager(getContext());
                lvSmoke.setLayoutManager(layoutManager);

                Collections.reverse(smoke);
                smoke.addAll(smoke);
                adapter = new AdapterItemDate(smoke);
                lvSmoke.setAdapter(adapter);

            }
        });
        getSmokeAsyncTask.execute();

        GetPresenceAsyncTask getPresenceAsyncTask = new GetPresenceAsyncTask("garage", new GetPresenceAsyncTask.CallbackGetPresence() {
            @Override
            public void getPresence(ArrayList<String> presence) {

                layoutManager = new LinearLayoutManager(getContext());
                lvPresence.setLayoutManager(layoutManager);

                Collections.reverse(presence);
                adapter = new AdapterItemDate(presence);
                lvPresence.setAdapter(adapter);

            }
        });
        getPresenceAsyncTask.execute();
    }
}
