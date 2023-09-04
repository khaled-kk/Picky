package com.myapplication.Picky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter2 extends RecyclerView.Adapter<myadapter2.mayflies>{

    Context context;
    ArrayList<hosptal> list;

    public myadapter2(Context context, ArrayList<hosptal> list) {
        this.context = context;
        this.list = list;
    }
// it is made to get the data of the hospital recycler view

    @NonNull
    @Override
    public mayflies onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.fragment_sixth,parent,false);
        return new mayflies(v);
    }

    @Override
    public void onBindViewHolder(@NonNull mayflies holder, int position) {

        hosptal hosptal =list.get(position);
        holder.name.setText(hosptal.getUsername());
        holder.lon.setText(hosptal.getLon());
        holder.lad.setText(hosptal.getLad());



    }

    @Override
    public int getItemCount() {

        System.out.println(list.size());
        return list.size();
    }

    public static class mayflies extends RecyclerView.ViewHolder{

        TextView name,lon,lad;



    public mayflies(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.nameH);
        lon=itemView.findViewById(R.id.lonH);
        lad=itemView.findViewById(R.id.latH);



    }
}
}
