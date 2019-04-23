package com.example.alici.remotecontroler.BottonNavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alici.remotecontroler.R;
import com.example.alici.remotecontroler.models.Light;
import com.example.alici.remotecontroler.network.async.GetHumidityAsyncTask;
import com.example.alici.remotecontroler.network.async.GetLightAsyncTask;
import com.example.alici.remotecontroler.network.async.GetTempAsyncTask;
import com.example.alici.remotecontroler.network.async.SetLightAsyncTask;

//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link BathroomFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link BathroomFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class BathroomFragment extends Fragment {
    private Switch lightSw;
    private TextView tempTextView;
    private TextView humTextView;

    public static BathroomFragment newInstance() {
        return new BathroomFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_bathroom, container, false);
        lightSw = view.findViewById(R.id.light_bathroom_switch);
        lightSw.setVisibility(View.INVISIBLE);
        lightSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightSw.setEnabled(false);
                SetLightAsyncTask setLightAsyncTask = new SetLightAsyncTask("bathroom", lightSw.isChecked(), new SetLightAsyncTask.CallbackSetLight() {
                    @Override
                    public void setLight(Boolean success) {
                        lightSw.setEnabled(success);
                    }
                });
                setLightAsyncTask.execute();
            }
        });
        Button rechargeButton = view.findViewById(R.id.bathroom_rechargeButton);
        tempTextView = view.findViewById(R.id.temp_bathroom_content);
        humTextView = view.findViewById(R.id.hum_bathroom_content);
        rechargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTempAsyncTask getTempAsyncTask = new GetTempAsyncTask("bathroom", new GetTempAsyncTask.CallbackGetTemp() {
                    @Override
                    public void getTemp(Integer temp) {
                        tempTextView.setText(temp + " ºC");
                    }
                });
                getTempAsyncTask.execute();

                GetHumidityAsyncTask getHumidityAsyncTask = new GetHumidityAsyncTask("bathroom", new GetHumidityAsyncTask.CallbackGetHum() {
                    @Override
                    public void getHum(Integer hum) {
                        humTextView.setText(hum + " %");
                    }
                });
                getHumidityAsyncTask.execute();
            }
        });

        //TODO get de humo
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GetLightAsyncTask getLightAsyncTask = new GetLightAsyncTask("bathroom", new GetLightAsyncTask.CallbackGetLight() {
            @Override
            public void getLight(Light light) {
                lightSw.setChecked(light.isOn());
                lightSw.setVisibility(View.VISIBLE);
            }
        });
        getLightAsyncTask.execute();

        GetTempAsyncTask getTempAsyncTask = new GetTempAsyncTask("bathroom", new GetTempAsyncTask.CallbackGetTemp() {
            @Override
            public void getTemp(Integer temp) {
                tempTextView.setText(temp + " ºC");
            }
        });
        getTempAsyncTask.execute();

        GetHumidityAsyncTask getHumidityAsyncTask = new GetHumidityAsyncTask("bathroom", new GetHumidityAsyncTask.CallbackGetHum() {
            @Override
            public void getHum(Integer hum) {
                humTextView.setText(hum + " %");
            }
        });
        getHumidityAsyncTask.execute();

        //TODO get inicial de humo
    }
}
