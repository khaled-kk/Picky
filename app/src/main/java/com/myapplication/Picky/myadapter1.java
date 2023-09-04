package com.myapplication.Picky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter1 extends RecyclerView.Adapter<myadapter1.reviewer>{

    Context context;
    ArrayList<userHelper> list;

    public myadapter1(Context context, ArrayList<userHelper> list) {
        this.context = context;
        this.list = list;
    }
// it is made to get the data of the request for the Patient recycler view

    @NonNull
    @Override
    public reviewer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.fragment_fifth,parent,false);
        return new reviewer(v);
    }

    @Override
    public void onBindViewHolder(@NonNull reviewer holder, int position) {

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

    public static class reviewer extends RecyclerView.ViewHolder{

        TextView name,age,mobile,sympt;



    public reviewer(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.nameR);
        age=itemView.findViewById(R.id.ageR);
        mobile=itemView.findViewById(R.id.phoneR);
        sympt=itemView.findViewById(R.id.sympR);



    }
}
}
