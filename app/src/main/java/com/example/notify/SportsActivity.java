package com.example.notify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SportsActivity extends AppCompatActivity {

    private CheckBox mCricket,mTennis,mFootball,mGolf,mBasketball,mVolleyball,mBaseball;
    private Button submit;
    FirebaseDatabase database;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        final  String[] globalarray = getIntent().getStringArrayExtra("key");
        final int index21=21,index22=22,index23=23,index24=24,index25=25,index26=26,index27=27;
        mCricket = findViewById(R.id.idcbcricket);
        mTennis = findViewById(R.id.idcbtennis);
        mFootball = findViewById(R.id.idcbfootball);
        mGolf = findViewById(R.id.idcbgolf);
        mBasketball = findViewById(R.id.idcbBasketball);
        mVolleyball = findViewById(R.id.idcbVollyball);
        mBaseball = findViewById(R.id.idcbBaseball);
        submit = findViewById(R.id.submit);

        final String content1="cricket";
        final String content2="tennis";
        final String content3="football";
        final String content4="golf";
        final String content5="Basketball";
        final String content6="Vollyball";
        final String content7="Baseball";

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Interest").child("Sports");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mCricket.isChecked())
                {
                    mDatabase.child("sports Category").setValue(content1);
                    globalarray[index21]=content1;
                }
                if(  mTennis .isChecked())
                {
                    mDatabase.child("sports Category").setValue(content2);
                    globalarray[index22]=content2;
                }
                if(mFootball.isChecked())
                {
                    mDatabase.child("sports Category").setValue(content3);
                    globalarray[index23]=content3;
                }
                if(mGolf.isChecked())
                {
                    mDatabase.child("sportsCategory").setValue(content4);
                    globalarray[index24]=content4;
                }
                if( mBasketball.isChecked())
                {
                    mDatabase.child("sports Category").setValue(content5);
                    globalarray[index25]=content5;
                }
                if(mVolleyball .isChecked())
                {
                    mDatabase.child("sports Category").setValue(content6);
                    globalarray[index26]=content6;
                }
                if( mBaseball .isChecked())
                {
                    mDatabase.child("sports Category").setValue(content7);
                    globalarray[index27]=content7;
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

                Intent j=new Intent(SportsActivity.this,TechnologyActivity.class);
                j.putExtra("key",globalarray);
                startActivity(j);
            }
        });

    }
}
