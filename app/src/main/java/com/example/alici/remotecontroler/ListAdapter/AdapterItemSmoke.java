package com.example.alici.remotecontroler.ListAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.alici.remotecontroler.R;
import com.example.alici.remotecontroler.models.Smoke;

import java.util.ArrayList;
import java.util.List;

public class AdapterItemSmoke extends  RecyclerView.Adapter<AdapterItemSmoke.DateViewHolder>  {

    public static class DateViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView date;

        DateViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            date = (TextView)itemView.findViewById(R.id.date_list);
        }
    }

    List<Smoke> smokeRecord;

    public AdapterItemSmoke(List<Smoke> smokeRecord){
        this.smokeRecord = smokeRecord;
    }

    @Override
    public int getItemCount() {
        return smokeRecord.size();
    }

    @Override
    public DateViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.kitchen_content, viewGroup, false);
        DateViewHolder pvh = new DateViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(DateViewHolder personViewHolder, int i) {
        personViewHolder.date.setText(smokeRecord.get(i).date);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
