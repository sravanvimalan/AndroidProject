package com.example.notify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

//import com.theartofdev.edmodo.cropper.CropImage;
//import com.theartofdev.edmodo.cropper.CropImageView;


public class UserdetailsActivity extends AppCompatActivity {

    private TextView mName,mphNo,mMail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);

        mName = findViewById(R.id.iddisplayname);
        mMail = findViewById(R.id.iddisplaymail);
        mphNo = findViewById(R.id.iddisplayphno);




    }
}
