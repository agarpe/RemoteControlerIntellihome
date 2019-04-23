package com.example.alici.remotecontroler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.alici.remotecontroler.BottonNavigation.BathroomFragment;
import com.example.alici.remotecontroler.BottonNavigation.GarageFragment;
import com.example.alici.remotecontroler.BottonNavigation.KitchenFragment;
import com.example.alici.remotecontroler.BottonNavigation.RoomFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = RoomFragment.newInstance();
                switch (item.getItemId()) {
                    case R.id.navigation_room:
                        selectedFragment = RoomFragment.newInstance();
                        break;
                    case R.id.navigation_kitchen:
                        selectedFragment = KitchenFragment.newInstance();
                        break;
                    case R.id.navigation_bathroom:
                        selectedFragment = BathroomFragment.newInstance();
                        break;
                    case R.id.navigation_garage:
                        selectedFragment = GarageFragment.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, RoomFragment.newInstance());
//        transaction.replace(R.id.frame_layout, KitchenFragment.newInstance());
        transaction.commit();

    }



}
