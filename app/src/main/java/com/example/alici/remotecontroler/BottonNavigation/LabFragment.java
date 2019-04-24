package com.example.alici.remotecontroler.BottonNavigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alici.remotecontroler.ListAdapter.AdapterItemDate;
import com.example.alici.remotecontroler.R;
import com.example.alici.remotecontroler.models.Date;
import com.example.alici.remotecontroler.models.Light;
import com.example.alici.remotecontroler.network.async.GetHumidityAsyncTask;
import com.example.alici.remotecontroler.network.async.GetLightAsyncTask;
import com.example.alici.remotecontroler.network.async.GetSmokeAsyncTask;
import com.example.alici.remotecontroler.network.async.GetTempAsyncTask;

import java.util.ArrayList;

public class LabFragment extends Fragment {

    public LabFragment() {
        // Required empty public constructor
    }

    public static LabFragment newInstance() {
        LabFragment fragment = new LabFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_lab, container, false);




        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       //TODO: Gets y execute correspondientes
    }
}
