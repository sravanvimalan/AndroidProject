package com.example.notify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class booksActivity extends AppCompatActivity {
    private CheckBox action,comic,crime,drama,horror,humor,mystry;
    private Button submit;
    FirebaseDatabase database;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        final  String[] globalarray = getIntent().getStringArrayExtra("key");
        final int index7=7,index8=8,index9=9,index10=10,index11=11,index12=12,index13=13;
            action = findViewById(R.id.idactionnadventre);
            comic = findViewById(R.id.idcomic);
            crime = findViewById(R.id.idcrime);
            drama = findViewById(R.id.iddrama);
            horror = findViewById(R.id.idhorror);
            humor = findViewById(R.id.idhumor);
            mystry = findViewById(R.id.idmystrery);
            submit = findViewById(R.id.submit);

            final String content1="Action and Adventure";
            final String content2="Comic and Graphic Novel";
            final String content3="Crime and Detective";
            final String content4="Drama";
            final String content5="Horror";
            final String content6="Humor";
            final String content7="Mystery";

            mDatabase = FirebaseDatabase.getInstance().getReference().child("Interest").child("Books");


            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(action.isChecked())
                    {
                        mDatabase.child("Book category").setValue(content1);
                        globalarray[index7]=content1;
                    }
                    if(comic.isChecked())
                    {
                        mDatabase.child("Book category").setValue(content2);
                        globalarray[index8]=content2;
                    }
                    if(crime.isChecked())
                    {
                        mDatabase.child("Book category").setValue(content3);
                        globalarray[index9]=content3;
                    }
                    if(drama.isChecked())
                    {
                        mDatabase.child("Book category").setValue(content4);
                        globalarray[index10]=content4;
                    }
                    if(horror.isChecked())
                    {
                        mDatabase.child("Book category").setValue(content5);
                        globalarray[index11]=content5;
                    }
                    if(humor.isChecked())
                    {
                        mDatabase.child("Book category").setValue(content6);
                        globalarray[index12]=content6;
                    }
                    if(mystry.isChecked())
                    {
                        mDatabase.child("Book category").setValue(content7);
                        globalarray[index13]=content7;
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
                    Intent j=new Intent(booksActivity.this,programmingActivity.class);
                    j.putExtra("key",globalarray);
                    startActivity(j);

                }
            });

        }
}
