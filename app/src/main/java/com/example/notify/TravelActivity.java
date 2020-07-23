package com.example.notify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TravelActivity extends AppCompatActivity {

    private CheckBox mBusinesstravel,msolotravel,mtravelwithfriends,mfamilytravel,mtravelwithgrp,mluxurytravel,madventuretravel;
    private Button submit;
    FirebaseDatabase database;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        final  String[] globalarray = getIntent().getStringArrayExtra("key");
        final int index35=35,index36=36,index37=37,index38=38,index39=39,index40=40,index41=41;


        mBusinesstravel = findViewById(R.id.idcbBusiness);
        msolotravel = findViewById(R.id.idcbSolo);
        mtravelwithfriends = findViewById(R.id.idcbFriends);
        mfamilytravel = findViewById(R.id.idcbFamily);
        mtravelwithgrp = findViewById(R.id.idcbGroup);
        mluxurytravel = findViewById(R.id.idcbLuxury);
        madventuretravel = findViewById(R.id.idcbAdventure);
        submit = findViewById(R.id.submit);

        final String content1="Business Travel";
        final String content2="Solo Travel";
        final String content3="Travel with Friends";
        final String content4="Family Travel";
        final String content5="Travel with group";
        final String content6="Luxury travel";
        final String content7="Adventure Travel";

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Interest").child("Travel");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mBusinesstravel.isChecked())
                {
                    mDatabase.child("Travel Category").setValue(content1);
                    globalarray[index35]=content1;
                }
                if(msolotravel .isChecked())
                {
                    mDatabase.child("Travel Category").setValue(content2);
                    globalarray[index36]=content2;
                }
                if( mtravelwithfriends.isChecked())
                {
                    mDatabase.child("Travel Category").setValue(content3);
                    globalarray[index37]=content3;
                }
                if(mfamilytravel.isChecked())
                {
                    mDatabase.child("Travel Category").setValue(content4);
                    globalarray[index38]=content4;
                }
                if(mtravelwithgrp.isChecked())
                {
                    mDatabase.child("Travel Category").setValue(content5);
                    globalarray[index39]=content5;
                }
                if(mluxurytravel .isChecked())
                {
                    mDatabase.child("Travel Category").setValue(content6);
                    globalarray[index40]=content6;
                }
                if( madventuretravel .isChecked())
                {
                    mDatabase.child("Travel Category").setValue(content7);
                    globalarray[index41]=content7;
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

                Intent j=new Intent(TravelActivity.this,profileActivity.class);
                j.putExtra("key",globalarray);
                startActivity(j);
            }
        });
    }
}
