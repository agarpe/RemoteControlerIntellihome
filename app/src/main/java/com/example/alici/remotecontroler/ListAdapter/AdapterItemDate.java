package com.example.alici.remotecontroler.ListAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.alici.remotecontroler.R;
import com.example.alici.remotecontroler.models.Date;

import java.util.List;

public class AdapterItemDate extends  RecyclerView.Adapter<AdapterItemDate.DateViewHolder>  {

    public static class DateViewHolder extends RecyclerView.ViewHolder{
//        CardView cv;
        TextView date;

        public DateViewHolder(View itemView) {
            super(itemView);
//            cv = (CardView)itemView.findViewById(R.id.cv);
            date = (TextView)itemView.findViewById(R.id.text_item);
        }
    }

    List<Date> record;

    public AdapterItemDate(List<Date> smokeRecord){
        this.record = smokeRecord;
    }

    @Override
    public int getItemCount() {
        return record.size();
    }

    @Override
    public DateViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.date_item, viewGroup, false);
        DateViewHolder pvh = new DateViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(DateViewHolder personViewHolder, int i) {
        personViewHolder.date.setText(record.get(i).date);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
