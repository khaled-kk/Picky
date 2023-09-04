package com.myapplication.Picky;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class first extends Fragment {

    Button update;
    private EditText name,age,mobile;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String mParam1;
    String mParam2;

    public first() {
    }

    public static first newInstance(String param1, String param2) {
        first fragment = new first();
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

// it is made to update the user profile (name, mobile ,age)


        View rootView =  inflater.inflate(R.layout.fragment_first, container, false);

        update=rootView.findViewById(R.id.Update10);
        name= rootView.findViewById(R.id.user10);
        mobile= rootView.findViewById(R.id.mobile10);
        age= rootView.findViewById(R.id.Age10);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users");
                String Name= name.getText().toString();
                String Mobile= mobile.getText().toString();
                String Age=age.getText().toString();


                userHelper userHelper=new userHelper(Name,Mobile,Age);
                reference.child(Mobile).setValue(userHelper);

                if(TextUtils.isEmpty(name.toString().trim())){
                    name.setError("Name is Required!");
                    return;
                }
                if(TextUtils.isEmpty(mobile.toString().trim())){
                    mobile.setError("Mobile is Required!");
                    return;
                }
                if(TextUtils.isEmpty(age.toString().trim())){
                    age.setError("Age is Required!");
                    return;
                }


            }
        });
        return rootView;
    }
}