package com.nsit.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ShowData extends AppCompatActivity {
    FirebaseDatabase fd;
    DatabaseReference dbr;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fd=FirebaseDatabase.getInstance();
        dbr=fd.getReference("student");
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseRecyclerAdapter<Student,DataHolder> fra=new FirebaseRecyclerAdapter<Student, DataHolder>(Student.class,R.layout.data_show,
                DataHolder.class,dbr) {
            @Override
            protected void populateViewHolder(DataHolder dataHolder, Student student, int i) {
                dataHolder.setView(getApplicationContext(),student.getSid(),student.getName(),student.getAddress(),student.getBranch()
                ,student.getAge());

            }
        };
        recyclerView.setAdapter(fra);
    }
}