package com.example.notify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TechnologyActivity extends AppCompatActivity {

    private CheckBox mIOT,mrobotics,mAI,mSI,mAGT,mConsttech,mBlockchain;
    private Button submit;
    FirebaseDatabase database;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);

        final  String[] globalarray = getIntent().getStringArrayExtra("key");
        final int index28=28,index29=29,index30=30,index31=31,index32=32,index33=33,index34=34;

        mIOT = findViewById(R.id.idcbIOT);
        mrobotics = findViewById(R.id.idcbRobotics);
        mAI = findViewById(R.id.idcbAI);
        mSI = findViewById(R.id.idcbSI);
        mAGT = findViewById(R.id.idcbABT);
        mConsttech = findViewById(R.id.idcbCT);
        mBlockchain = findViewById(R.id.idcbbockchain);
        submit = findViewById(R.id.submit);

        final String content1="Internet of Things";
        final String content2="Robotics";
        final String content3="Artificial Intelligence";
        final String content4="Super Intelligence";
        final String content5="Agriculture and bio Technology";
        final String content6="Construction Technology";
        final String content7="Blockchain technology";

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Interest").child("Common Technology");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(  mIOT.isChecked())
                {
                    mDatabase.child("Technology Category").setValue(content1);
                    globalarray[index28]=content1;
                }
                if(mrobotics .isChecked())
                {
                    mDatabase.child("Technology Category").setValue(content2);
                    globalarray[index29]=content2;
                }
                if(mAI .isChecked())
                {
                    mDatabase.child("Technology Category").setValue(content3);
                    globalarray[index30]=content3;
                }
                if(mSI.isChecked())
                {
                    mDatabase.child("Technology Category").setValue(content4);
                    globalarray[index31]=content4;
                }
                if(mAGT.isChecked())
                {
                    mDatabase.child("Technology Category").setValue(content5);
                    globalarray[index32]=content5;
                }
                if(  mConsttech .isChecked())
                {
                    mDatabase.child("Technology Category").setValue(content6);
                    globalarray[index33]=content6;
                }
                if( mBlockchain .isChecked())
                {
                    mDatabase.child("Technology Category").setValue(content7);
                    globalarray[index34]=content7;
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

                Intent j=new Intent(TechnologyActivity.this,TravelActivity.class);
                j.putExtra("key",globalarray);
                startActivity(j);
            }
        });

    }
}
