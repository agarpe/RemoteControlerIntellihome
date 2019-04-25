package com.example.alici.remotecontroler.BottonNavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.alici.remotecontroler.R;
import com.example.alici.remotecontroler.models.Light;
import com.example.alici.remotecontroler.network.async.GetLightLabAsyncTask;
import com.example.alici.remotecontroler.network.async.SetLightLabAsyncTask;

//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link LabFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link LabFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class LabFragment extends Fragment {
    private Switch light1Sw;
    private Switch light2Sw;

    public static LabFragment newInstance() {
        return new LabFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_lab, container, false);
        light1Sw = view.findViewById(R.id.switch1);
        light1Sw.setVisibility(View.INVISIBLE);
        light1Sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                light1Sw.setEnabled(false);
                SetLightLabAsyncTask setLightAsyncTask = new SetLightLabAsyncTask("lab", light1Sw.isChecked(), new SetLightLabAsyncTask.CallbackSetLight() {
                    @Override
                    public void setLight(Boolean success) {
                        light1Sw.setEnabled(success);
                    }
                });
                setLightAsyncTask.execute();
            }
        });
        light2Sw = view.findViewById(R.id.switch2);
        light2Sw.setVisibility(View.INVISIBLE);
        light2Sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                light2Sw.setEnabled(false);
                SetLightLabAsyncTask setLightAsyncTask = new SetLightLabAsyncTask("lab", light2Sw.isChecked(), new SetLightLabAsyncTask.CallbackSetLight() {
                    @Override
                    public void setLight(Boolean success) {
                        light2Sw.setEnabled(success);
                    }
                });
                setLightAsyncTask.execute();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GetLightLabAsyncTask getLight1AsyncTask = new GetLightLabAsyncTask("lab", new GetLightLabAsyncTask.CallbackGetLight() {
                    @Override
                    public void getLight(Light light) {
                        light1Sw.setChecked(light.isOn());
                light1Sw.setVisibility(View.VISIBLE);
            }
        });
        getLight1AsyncTask.execute();

        GetLightLabAsyncTask getLight2AsyncTask = new GetLightLabAsyncTask("lab", new GetLightLabAsyncTask.CallbackGetLight() {
                    @Override
                    public void getLight(Light light) {
                        light2Sw.setChecked(light.isOn());
                light2Sw.setVisibility(View.VISIBLE);
            }
        });
        getLight2AsyncTask.execute();

    }
}
