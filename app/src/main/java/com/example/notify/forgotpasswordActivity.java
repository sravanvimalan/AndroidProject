package com.example.notify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpasswordActivity extends AppCompatActivity {
    private EditText email;
    private Button reset;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        email = findViewById(R.id.idemail);
        reset = findViewById(R.id.btnsubmit);

        firebaseAuth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(forgotpasswordActivity.this,"password reset link send to your email",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(forgotpasswordActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}
