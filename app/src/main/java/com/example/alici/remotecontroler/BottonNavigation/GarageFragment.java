package com.example.alici.remotecontroler.BottonNavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.alici.remotecontroler.R;
import com.example.alici.remotecontroler.models.Door;
import com.example.alici.remotecontroler.models.Light;
import com.example.alici.remotecontroler.network.async.GetDoorAsyncTask;
import com.example.alici.remotecontroler.network.async.GetLightAsyncTask;
import com.example.alici.remotecontroler.network.async.SetDoorAsyncTask;
import com.example.alici.remotecontroler.network.async.SetLightAsyncTask;

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
        //TODO presencia y humo
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

        //TODO getters iniciales de presencia y humo
    }
}
