package com.example.notify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mauth;
    private EditText mMailid;
    private EditText password;
    private Button btn;
    private TextView forgotpassword;
    // private ProgressBar progressBar;

    @Override
    protected void onStart() {
        super.onStart();
        if(mauth.getCurrentUser() != null)
        {
            //progressBar.setVisibility(View.INVISIBLE);
           // progressBar.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(LoginActivity.this,profileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mauth = FirebaseAuth.getInstance();  // object intialize
        mMailid = findViewById(R.id.idemail);
        password = findViewById(R.id.etpsswrd);
        btn = findViewById(R.id.btnsubmit);
        forgotpassword = findViewById(R.id.idpswrdfrgot);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mailid = mMailid.getText().toString().trim();
                final String passwrd=password.getText().toString().trim();
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
                userlogin(mailid, passwrd);
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,forgotpasswordActivity.class);
                startActivity(intent);
            }
        });



    }

    private void userlogin(String mailid, String passwrd) {
        mauth.signInWithEmailAndPassword(mailid, passwrd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //startProfileAcitvity();
                            Intent intent = new Intent(LoginActivity.this,profileActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            // progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }
}
