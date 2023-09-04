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


public class second extends Fragment {

     Button update;
     EditText name,age,mobile,symp;
     FirebaseDatabase rootNode;
     DatabaseReference reference;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public second() {
    }

    public static second newInstance(String param1, String param2) {
        second fragment = new second();
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
// it is made to create new request (name, mobile ,age ,symptoms)

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);


        update=rootView.findViewById(R.id.submit);
        name= rootView.findViewById(R.id.Name2);
        mobile= rootView.findViewById(R.id.mobile20);
        age= rootView.findViewById(R.id.Age20);
        symp= rootView.findViewById(R.id.symp);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference=rootNode.getReference("request");
                String Name= name.getText().toString();
                String Mobile= mobile.getText().toString();
                String Age=age.getText().toString();
                String Symp=symp.getText().toString();


                userHelper userHelper=new userHelper(Name,Mobile,Age,Symp);
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
                if(TextUtils.isEmpty(symp.toString().trim())){
                    symp.setError("Symptoms is Required!");
                    return;
                }
            }
        });
        return rootView;
    }
}