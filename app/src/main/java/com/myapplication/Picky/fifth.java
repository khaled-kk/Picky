package com.myapplication.Picky;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class fifth extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String mParam1;
    String mParam2;
    RecyclerView recyclerView;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    myadapter1 adaptor;
    ArrayList<userHelper> list;

    public fifth() {
    }

    public static fifth newInstance(String param1, String param2) {
        fifth fragment = new fifth();
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

// it is made to create a card recycler view for the Patient

        View v = inflater.inflate(R.layout.activity_recycler_view, container, false);
        recyclerView = v.findViewById(R.id.list2);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("request");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<userHelper>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    userHelper userHelper = dataSnapshot.getValue(userHelper.class);
                    list.add(userHelper);
                    System.out.println(list.size());
                }
                adaptor = new myadapter1(getActivity(), list);
                recyclerView.setAdapter(adaptor);
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return v;
    }
}