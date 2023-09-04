package com.myapplication.Picky;

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

public class fourth extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String mParam1;
    String mParam2;
    RecyclerView recyclerView;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    myadapter myadapter;
    ArrayList<userHelper> list;
    Button button;
    EditText comment;


    public fourth() {
    }

    public static fourth newInstance(String param1, String param2) {
        fourth fragment = new fourth();
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
// it is made to create a card recycler view for the Doctor
// the defiance is the comment Button

        View v = inflater.inflate(R.layout.activity_recycler_view, container, false);
        recyclerView = v.findViewById(R.id.list2);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("request");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        button=v.findViewById(R.id.Update0);
        comment=v.findViewById(R.id.comment);


        list = new ArrayList<userHelper>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    userHelper userHelper = dataSnapshot.getValue(userHelper.class);
                    list.add(userHelper);
                    System.out.println(list.size());
                }
                myadapter = new myadapter(getActivity(), list);
                recyclerView.setAdapter(myadapter);
                myadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        String Comment= comment.getText().toString();
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rootNode =FirebaseDatabase.getInstance();
//                reference=rootNode.getReference("request");
//
//                userHelper userHelper=new userHelper(Comment);
//                reference.child().setValue(userHelper);
//            }
//        });
        return v;
    }
}