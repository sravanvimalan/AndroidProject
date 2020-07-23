package com.example.notify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InterestActivity extends AppCompatActivity {

    private Button btnanimal, btnbooks, btnprogramming, btnsports, btntech, btntravel,msubmit;
    Intent nextpage;

    private void setUpViewId() {

        btnanimal = (Button) findViewById(R.id.btnanimal);
        btnbooks = (Button) findViewById(R.id.btnbook);
        btnprogramming = (Button) findViewById(R.id.btnprgming);
        btnsports = (Button) findViewById(R.id.btnsport);
        btntech = (Button) findViewById(R.id.btntech);
        btntravel = (Button) findViewById(R.id.btntravel);
        msubmit = findViewById(R.id.submit);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        setUpViewId();

        btnanimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextpage = new Intent(InterestActivity.this,AnimalActivity.class);
                startActivity(nextpage);
            }
        });

        btnbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextpage = new Intent(InterestActivity.this,booksActivity.class);
                startActivity(nextpage);

            }
        });

        btntravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextpage = new Intent(InterestActivity.this,TravelActivity.class);
                startActivity(nextpage);
            }
        });

        btntech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextpage = new Intent(InterestActivity.this,TechnologyActivity.class);
                startActivity(nextpage);
            }
        });
        btnsports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextpage = new Intent(InterestActivity.this,SportsActivity.class);
                startActivity(nextpage);
            }
        });
        btnprogramming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextpage = new Intent(InterestActivity.this,programmingActivity.class);
                startActivity(nextpage);
            }
        });
        msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextpage = new Intent(InterestActivity.this,profileActivity.class);
                startActivity(nextpage);
            }
        });

    }
}
