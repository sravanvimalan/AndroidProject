package com.example.notify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AnimalActivity extends AppCompatActivity {

    private  CheckBox minvertebrate,mMammals,mBirds,mAmphibian,mReptiles,mFish;
    private Button submit;
    FirebaseDatabase database;
    private DatabaseReference mDatabase;
    final String[] globalarray = new String[100];
    final int index1=1,index2=2,index3=3,index4=4,index5=5,index6=6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        final  String[] globalarray = new String[100];
        minvertebrate = findViewById(R.id.idinvertebrates);
        mMammals = findViewById(R.id.idmammals);
        mBirds = findViewById(R.id.idbirds);
        mAmphibian = findViewById(R.id.idamphibians);
        mReptiles = findViewById(R.id.idreptiles);
        mFish = findViewById(R.id.idfish);
        submit = findViewById(R.id.submit);

        final String content1="Invertebrates";
        final String content2="mammals";
        final String content3="birds";
        final String content4="amphibians";
        final String content5="reptiles";
        final String content6="fish";


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Interest").child("Animals");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( minvertebrate.isChecked())
                {
                    mDatabase.child("Animal Category").setValue(content1);
                    globalarray[index1]=content1;

                }
                if( mMammals.isChecked())
                {
                    mDatabase.child("Animal Category").setValue(content2);
                    globalarray[index2]=content2;

                }
                if( mBirds.isChecked())
                {
                    mDatabase.child("Animal Category").setValue(content3);
                    globalarray[index3]=content3;


                }
                if( mAmphibian.isChecked())
                {
                    mDatabase.child("Animal Category").setValue(content4);
                    globalarray[index4]=content4;

                }
                if( mReptiles.isChecked())
                {
                    mDatabase.child("Animal Category").setValue(content5);
                    globalarray[index5]=content5;

                }
                if( mFish.isChecked())
                {
                    mDatabase.child("Animal Category").setValue(content6);
                    globalarray[index6]=content6;

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

                Intent j=new Intent(AnimalActivity.this,booksActivity.class);
                j.putExtra("key",globalarray);
                startActivity(j);
            }
        });
    }
}
