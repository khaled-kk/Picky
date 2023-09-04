package com.myapplication.Picky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    EditText password,email,mobile;
    TextView textView,textView1,textView2,forgot;
    Button signin,signup;
    FirebaseAuth fAuth;


// it is made to create the login page (email, mobile, password)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email=findViewById(R.id.editTextemail);
        mobile=findViewById(R.id.editTextmobile);
        password=findViewById(R.id.editTextTextPassword);
        textView=findViewById(R.id.textView3);
        textView1=findViewById(R.id.textView4);
        textView2=findViewById(R.id.textView5);
        signin=findViewById(R.id.button);
        signup=findViewById(R.id.btnsignUp);
        fAuth=FirebaseAuth.getInstance();
        forgot=findViewById(R.id.forgot);



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email= email.getText().toString().trim();
                String Passowrd=password.getText().toString().trim();
                String Mobile= mobile.getText().toString().trim();


                if(TextUtils.isEmpty(Email)){
                    email.setError("Email is Required!");
                    return;
                }
                if(TextUtils.isEmpty(Mobile)){
                    mobile.setError("Mobile is Required!");
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
                fAuth.signInWithEmailAndPassword(Email,Passowrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
                            Query query=reference.orderByChild("mobile").equalTo(Mobile);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        String type=snapshot.child(Mobile).child("type").getValue(String.class);
                                        if (type.equals("doctor")){
                                            Toast.makeText(login.this, "Logged in successFully", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), taps2.class));
                                        }
                                        else {
                                            if(type.equals("patient")) {
                                                Toast.makeText(login.this, "Logged in successFully", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), taps.class));
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        else{
                            Toast.makeText(login.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),regester.class));
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail =new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog =new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter your Email to Receive Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail=resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(login.this,"Reset Link Sent To Your Email.",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(login.this,"Reset Link Is Not Send"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });
                passwordResetDialog.create().show();
            }
        });
    }

}