package com.example.alici.remotecontroler.BottonNavigation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.alici.remotecontroler.R;
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

    private static final int OPENTIME = 30;
    private int counter = 0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


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
        View view = inflater.inflate(R.layout.fragment_room, container, false);

        Switch door_bt = (Switch) view.findViewById(R.id.door_garage_switch);

        final Context context = view.getContext();

        door_bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //counter tiempo apertura de puerta
                if(counter >= 0 && counter < OPENTIME)
                {
                    new AlertDialog.Builder(context)
                            .setTitle("Abriendo puerta")
                            .setMessage("Espere mientras la puerta se abre...");

                }
                else
                {
                    //Send request
                    new AlertDialog.Builder(context)
                            .setTitle("Mandar petición")
                            .setMessage("Petición para abrir");
                }

            }

        });


        return view;
    }

}
