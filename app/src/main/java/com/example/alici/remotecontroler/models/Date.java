package com.example.alici.remotecontroler.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Date {

    public String date;
    String time;


    public Date(String date)
    {
        this.date = date;
    }
    public Date(JSONObject response)
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

//    public static ArrayList<ItemDate> getSmokeCategory(ArrayList<Date> smoke) {
//
//    }
}
