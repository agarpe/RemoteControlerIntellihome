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
import android.widget.Button;
import android.widget.Switch;

import com.example.alici.remotecontroler.ListAdapter.AdapterItemDate;
import com.example.alici.remotecontroler.R;
import com.example.alici.remotecontroler.models.Light;
import com.example.alici.remotecontroler.network.async.GetLightAsyncTask;
import com.example.alici.remotecontroler.network.async.GetPresenceAsyncTask;
import com.example.alici.remotecontroler.network.async.GetSmokeAsyncTask;
import com.example.alici.remotecontroler.network.async.SetLightAsyncTask;

import java.util.ArrayList;
import java.util.Collections;

//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link RoomFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link RoomFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class RoomFragment extends Fragment {
    private Switch lightSw;

    private RecyclerView lvPresence;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public static RoomFragment newInstance() {
        return new RoomFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_room, container, false);
        lightSw = view.findViewById(R.id.light_room_switch);
        lightSw.setVisibility(View.INVISIBLE);
        lightSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightSw.setEnabled(false);
                SetLightAsyncTask setLightAsyncTask = new SetLightAsyncTask("room", lightSw.isChecked(), new SetLightAsyncTask.CallbackSetLight() {
                    @Override
                    public void setLight(Boolean success) {
                        lightSw.setEnabled(success);
                    }
                });
                setLightAsyncTask.execute();
            }
        });
        FloatingActionButton rechargeButton = view.findViewById(R.id.room_rechargeButton);
        rechargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetPresenceAsyncTask getPresenceAsyncTask = new GetPresenceAsyncTask("room", new GetPresenceAsyncTask.CallbackGetPresence() {
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

        lvPresence = view.findViewById(R.id.room_presence_list);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GetLightAsyncTask getLightAsyncTask = new GetLightAsyncTask("room", new GetLightAsyncTask.CallbackGetLight() {
                    @Override
                    public void getLight(Light light) {
                        lightSw.setChecked(light.isOn());
                lightSw.setVisibility(View.VISIBLE);
            }
        });
        getLightAsyncTask.execute();

        GetPresenceAsyncTask getPresenceAsyncTask = new GetPresenceAsyncTask("room", new GetPresenceAsyncTask.CallbackGetPresence() {
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
