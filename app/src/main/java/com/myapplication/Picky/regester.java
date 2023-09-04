package com.myapplication.Picky;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class regester extends AppCompatActivity {
    EditText user,password,email,mobile;
    Button button;
    TextView textView2;
    ImageView ivback;
    FirebaseAuth fAuth;
    CheckBox DCheckBox,pCheckBox;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String type="";
    String Age="";

    // it is made to create the sign up page (name, email, mobile, password, doctor or patient)

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regester);

        user =findViewById(R.id.user);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobile);
        button=findViewById(R.id.button);
        textView2=findViewById(R.id.textView2);
        ivback=findViewById(R.id.ivback);
        fAuth=FirebaseAuth.getInstance();
        DCheckBox=findViewById(R.id.checkBox4);
        pCheckBox=findViewById(R.id.checkBox3);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode =FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users");
                String name= user.getText().toString();
                String Password= password.getText().toString();
                String E_mail= email.getText().toString();
                String Mobile= mobile.getText().toString();

                if (DCheckBox.isChecked()&&!pCheckBox.isChecked()){
                    type="doctor";
                }
                else {
                    if (pCheckBox.isChecked()&&!DCheckBox.isChecked()){
                        type="patient";
                    }
                    else{
                        DCheckBox.setError("You Should Choose Only One Option!");
                        pCheckBox.setError("You Should Choose Only One Option!");
                        return;
                    }
                }
                userHelper userHelper=new userHelper(name,Password,E_mail,Mobile,type,Age);
                reference.child(Mobile).setValue(userHelper);

                String Email= email.getText().toString().trim();
                String Passowrd=password.getText().toString().trim();

                if(TextUtils.isEmpty(Email)){
                    email.setError("Email is Required!");
                    return;
                }
                if(TextUtils.isEmpty(Passowrd)){
                    password.setError("Password is Required!");
                    return;
                }
                if(Passowrd.length()<6){
                    password.setError("Password Must be more than 6 letters");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(Email,Passowrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            if (type.equals("doctor")){
                                Toast.makeText(regester.this, "User Created", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), taps2.class));
                            }
                            else {
                                if(type.equals("patient")) {
                                    Toast.makeText(regester.this, "User Created", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), taps.class));
                                }
                            }
                        }else{
                            Toast.makeText(regester.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });
    }
}
