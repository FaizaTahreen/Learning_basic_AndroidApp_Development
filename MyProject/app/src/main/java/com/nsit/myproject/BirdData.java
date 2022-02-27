package com.nsit.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BirdData extends AppCompatActivity {
  FirebaseDatabase fd;
  DatabaseReference dbr;
  RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_data);
        rv=findViewById(R.id.birddata);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        fd=FirebaseDatabase.getInstance();
        dbr=fd.getReference("Birds");

    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseRecyclerAdapter<ImageData,BirdDataHandler> fra=new FirebaseRecyclerAdapter<ImageData, BirdDataHandler>
                (ImageData.class,R.layout.bird_custom_data,BirdDataHandler.class,dbr) {
            @Override
            protected void populateViewHolder(BirdDataHandler birdDataHandler, ImageData imageData, int i)
            {

             birdDataHandler.setView(getApplicationContext(),imageData.getTitle(),imageData.getImage());
            }
        };
        rv.setAdapter(fra);

    }
}