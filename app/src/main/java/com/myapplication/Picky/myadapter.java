package com.myapplication.Picky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.app.ComponentActivity;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>{

    Context context;
    ArrayList<userHelper> list;

    public myadapter(Context context, ArrayList<userHelper> list) {
        this.context = context;
        this.list = list;
    }
// it is made to get the data of the request for the doctor recycler view
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.fragment_fourth,parent,false);
        return new myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        userHelper userHelper =list.get(position);
        holder.name.setText(userHelper.getUsername());
        holder.age.setText(userHelper.getAge());
        holder.mobile.setText(userHelper.getMobile());
        holder.sympt.setText(userHelper.getSymp());


    }

    @Override
    public int getItemCount() {

        System.out.println(list.size());
        return list.size();
    }

    public static class myviewholder extends RecyclerView.ViewHolder{

        TextView name,age,mobile,sympt;



    public myviewholder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        age=itemView.findViewById(R.id.age1);
        mobile=itemView.findViewById(R.id.phone);
        sympt=itemView.findViewById(R.id.symp1);



    }
}
}
