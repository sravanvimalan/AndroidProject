package com.example.notify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class programmingActivity extends AppCompatActivity {

    private CheckBox mC,mCplusplus,mJava,mPython,mKotlin,mR,mCsharp;
    private Button submit;
    FirebaseDatabase database;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programming);

        final  String[] globalarray = getIntent().getStringArrayExtra("key");
        final int index14=14,index15=15,index16=16,index17=17,index18=18,index19=19,index20=20;
        mC = findViewById(R.id.idC);
        mCplusplus = findViewById(R.id.idCplusplus);
        mJava = findViewById(R.id.idJava);
        mPython = findViewById(R.id.idPython);
        mKotlin = findViewById(R.id.idKotlin);
        mR = findViewById(R.id.idR);
        mCsharp = findViewById(R.id.idCsharp);
        submit = findViewById(R.id.submit);
        final String content1="C";
        final String content2="C++";
        final String content3="Java";
        final String content4="Python";
        final String content5="Kotlin";
        final String content6="R";
        final String content7="C#";

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Interest").child("Programming language");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(  mC.isChecked())
                {
                    mDatabase.child("Programming language Category").setValue(content1);
                    globalarray[index14]=content1;
                }
                if( mCplusplus .isChecked())
                {
                    mDatabase.child("Programming language Category").setValue(content2);
                    globalarray[index15]=content2;
                }
                if(mJava.isChecked())
                {
                    mDatabase.child("Programming language Category").setValue(content3);
                    globalarray[index16]=content3;
                }
                if( mPython.isChecked())
                {
                    mDatabase.child("Programming language Category").setValue(content4);
                    globalarray[index17]=content4;
                }
                if(  mKotlin.isChecked())
                {
                    mDatabase.child("Programming language Category").setValue(content5);
                    globalarray[index18]=content5;
                }
                if(mR .isChecked())
                {
                    mDatabase.child("Programming language Category").setValue(content6);
                    globalarray[index19]=content6;
                }
                if(mCsharp .isChecked())
                {
                    mDatabase.child("Programming language Category").setValue(content7);
                    globalarray[index20]=content7;
                }

                try {
                    for(int i=0;i<globalarray.length;i++)
                    {
                        if(globalarray[i] !=null)
                            mDatabase.child("Animal Category").setValue(globalarray[i]);
                    }
                }catch (Exception e)
                {

                }

                Intent j=new Intent(programmingActivity.this,SportsActivity.class);
                j.putExtra("key",globalarray);
                startActivity(j);
            }
        });
    }
}
