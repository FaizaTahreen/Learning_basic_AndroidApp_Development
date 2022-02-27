package com.nsit.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
EditText sid,name,age,branch,add;
Button register,showdata,birddata;
FirebaseDatabase fd;
DatabaseReference dbr;
int maxid;
Student sd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sid=findViewById(R.id.sid);
        name=findViewById(R.id.name);
        branch=findViewById(R.id.branch);
        age=findViewById(R.id.age);
        add=findViewById(R.id.add);
        register=findViewById(R.id.register);
        showdata=findViewById(R.id.showdata);
        birddata=findViewById(R.id.birddata);
        sd=new Student();
        showdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShowData.class));
            }
        });
        birddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,BirdData.class));
            }
        });
        fd=FirebaseDatabase.getInstance();
        dbr=fd.getReference().child("student");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maxid=(int)dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

          register.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  sd.setAddress(add.getText().toString());
                  sd.setAge(Integer.parseInt(age.getText().toString()));
                  sd.setSid(Integer.parseInt(sid.getText().toString()));
                  sd.setName(name.getText().toString());
                  sd.setBranch(branch.getText().toString());
                  dbr.child(String.valueOf(maxid+1)).setValue(sd);
                  
              }
          });

    }
}