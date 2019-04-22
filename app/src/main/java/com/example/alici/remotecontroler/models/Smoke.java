package com.example.alici.remotecontroler.models;

import android.util.Log;

import com.example.alici.remotecontroler.ListAdapter.ItemSmoke;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Smoke {

    public String date;
    String time;


    public Smoke(String date)
    {
        this.date = date;
    }
    public Smoke(JSONObject response)
    {
        try{
            //TODO: comprobar si son 2 parámetros o un solo string
            this.date = response.getString("");
            this.time = response.getString("");
        }catch (JSONException e) {
            Log.e("" +
                    "SmokeConstructor","Error parsing from JSON "+e.getMessage());
            e.printStackTrace();
        } catch (Exception e){
            Log.e("SmokeConstructor","Error "+e.getMessage());
            e.printStackTrace();
        }


    }

//    public static ArrayList<ItemSmoke> getSmokeCategory(ArrayList<Smoke> smoke) {
//
//    }
}
