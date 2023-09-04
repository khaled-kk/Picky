package com.myapplication.Picky;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class sixth extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String mParam1;
    String mParam2;
    RecyclerView recyclerView;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    myadapter2 adapter;
    ArrayList<hosptal> list;
    Button go;
    EditText lon,lad;
    public sixth() {
    }


    public static sixth newInstance(String param1, String param2) {
        sixth fragment = new sixth();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// it is made to create a card recycler view for the hospitals

        View v = inflater.inflate(R.layout.activity_recycler_view, container, false);

        recyclerView = v.findViewById(R.id.list2);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("hosptal");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        go=v.findViewById(R.id.go);
        lad=v.findViewById(R.id.latH);
        lon=v.findViewById(R.id.lonH);

        list = new ArrayList<hosptal>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    hosptal hosptal = dataSnapshot.getValue(hosptal.class);
                    list.add(hosptal);
                    System.out.println(list.size());
                }
                adapter = new myadapter2(getActivity(), list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        go.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), maps.class));
//
//            }
//        });
        return v;
    }
}