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


public class third extends Fragment {

    Button add;
    private EditText name,lon,lad;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String mParam1;
    String mParam2;

    public third() {
    }

    public static third newInstance(String param1, String param2) {
        third fragment = new third();
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
// it is made to create new hospitals

        View rootView =  inflater.inflate(R.layout.fragment_third, container, false);

        add=rootView.findViewById(R.id.add);
        name= rootView.findViewById(R.id.hosptal);
        lon= rootView.findViewById(R.id.lon);
        lad= rootView.findViewById(R.id.lat);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference=rootNode.getReference("hosptal");
                String Name= name.getText().toString();
                String Lon= lon.getText().toString();
                String Lad=lad.getText().toString();


                hosptal hosptal=new hosptal(Name,Lon,Lad);
                reference.child(Name).setValue(hosptal);

                if(TextUtils.isEmpty(name.toString().trim())){
                    name.setError("Name is Required!");
                    return;
                }
                if(TextUtils.isEmpty(lon.toString().trim())){
                    lon.setError("longitude is Required!");
                    return;
                }
                if(TextUtils.isEmpty(lad.toString().trim())){
                    lad.setError("latitude is Required!");
                    return;
                }


            }
        });
        return rootView;
    }
}