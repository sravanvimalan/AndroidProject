package com.example.notify;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class profileActivity extends AppCompatActivity {

    public static final String NODE_USERS = "user";
    private Button  add_room;
    private EditText room_name;
    private FirebaseAuth mAuth;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_rooms = new ArrayList<>();
    private String name;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();

    public String[] arr ;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_resource,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.signout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(profileActivity.this,LoginActivity.class));
                break;

        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(task.isSuccessful())
                        {
                            String token = task.getResult().getToken();
                            saveToken(token);
                        }else
                        {

                        }
                    }
                });
       // add_room = (Button) findViewById(R.id.btn_add_room);
        //room_name = (EditText) findViewById(R.id.room_name_edittext);
        String[] globalarray = getIntent().getStringArrayExtra("key");

        listView = (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_of_rooms);

          //  if(arrayAdapter.getItem(0).equals("fish"))
                 listView.setAdapter(arrayAdapter);
                 try {
                     String smsMessageStr = (String) arrayAdapter.getItem(6);

                     Toast.makeText(this, smsMessageStr, Toast.LENGTH_SHORT).show();
                 }catch (Exception e)
                 {}



        request_user_name();

//        add_room.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Map<String,Object> map = new HashMap<String, Object>();
//                map.put(room_name.getText().toString(),"");
//                root.updateChildren(map);

//
//            }
//        });

        try {
            for(int i=0;i<globalarray.length;i++)
            {
                if(globalarray[i] != null)
                {
                    Map<String,Object> map = new HashMap<String, Object>();
                    map.put(globalarray[i],"");
                    root.updateChildren(map);
                }

            }
        }catch (Exception e)
        {}
        try {
            for(int i=0;i<globalarray.length;i++)
            {
                if(globalarray[i] != null)
                {
                   // Toast.makeText(profileActivity.this, globalarray[i], Toast.LENGTH_SHORT).show();
                }

            }
        }catch (Exception e)
        {}




        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }

                list_of_rooms.clear();
                list_of_rooms.addAll(set);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(),Chat_Room.class);
                intent.putExtra("room_name",((TextView)view).getText().toString() );
                intent.putExtra("user_name",name);
                startActivity(intent);
            }
        });

    }

    private void request_user_name() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter name:");

        final EditText input_field = new EditText(this);

        builder.setView(input_field);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                name = input_field.getText().toString();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                request_user_name();
            }
        });

        builder.show();
    }

    private void saveToken(String token)
    {
        String emaiil = mAuth.getCurrentUser().getEmail();
        User user = new User(emaiil,token);
        DatabaseReference dbUsers = FirebaseDatabase.getInstance().getReference(NODE_USERS);

        dbUsers.child(mAuth.getCurrentUser().getUid())
                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(profileActivity.this,"Token saved",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}