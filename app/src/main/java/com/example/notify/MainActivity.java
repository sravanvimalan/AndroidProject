package com.example.notify;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
//import android.app.NotificationChannel;


public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_ID= "simplfy coding";
    private static  final String CHANNEL_NAME= "Simplied code";
    private static  final String CHANNEL_DESC= "Simplfied coding notification";
    private EditText mMailid;
    private EditText password;
    private Button btn;
    private ProgressBar progressBar;
    private  EditText mconfirmpasswrd;
    private EditText mname;
    private EditText mphoneno;

 private FirebaseAuth mauth;

    @Override
    protected void onStart() {
        super.onStart();
        if(mauth.getCurrentUser() != null)
        {
            //progressBar.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(MainActivity.this,profileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mauth = FirebaseAuth.getInstance();  // object intialize
        mMailid = findViewById(R.id.iddisplaymail);
        password = findViewById(R.id.idpwrd);
        btn = findViewById(R.id.button);
        progressBar = findViewById(R.id.idprogress);
        mconfirmpasswrd = findViewById(R.id.idconfirmpasswrd);
        progressBar.setVisibility(View.INVISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createuser();
            }

            private void createuser() {
                final String mailid = mMailid.getText().toString().trim();
                final String passwrd=password.getText().toString().trim();
                final String confirmpasswrd = mconfirmpasswrd.getText().toString().trim();
                if(mailid.isEmpty())
                {
                    mMailid.setError("Email required");
                    mMailid.requestFocus();
                    return;
                }
                if(passwrd.isEmpty())
                {
                    password.setError("Password required");
                    password.requestFocus();
                    return;
                }
                if(passwrd.length() < 8)
                {
                    password.setError("password should greater than 8 long char");
                    password.requestFocus();
                    return;
                }
                    progressBar.setVisibility(View.VISIBLE);

                if(passwrd.equals(confirmpasswrd)) {
                    mauth.createUserWithEmailAndPassword(mailid, passwrd)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startProfileAcitvity();
                                    } else {
                                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                            userlogin(mailid, passwrd);
                                        } else {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }

                                private void userlogin(String mailid, String passwrd) {
                                    mauth.signInWithEmailAndPassword(mailid, passwrd)
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        startProfileAcitvity();
                                                    } else {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                    }

                                                }
                                            });

                                }

                                private void startProfileAcitvity() {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Intent intent = new Intent(MainActivity.this, AnimalActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

                                }
                            });
                }
                else
                {
                    password.setError("password not match");
                    password.requestFocus();
                    return;
                }
            }
        });



       // textView = findViewById(R.id.idtoken);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Token saved", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                           Toast.makeText(MainActivity.this,"Token not saved",Toast.LENGTH_SHORT).show();
                        }
                    }
                });



        }

    }

