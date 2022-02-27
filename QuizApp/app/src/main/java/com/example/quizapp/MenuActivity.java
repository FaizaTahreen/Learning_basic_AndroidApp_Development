package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lv;
    String my_activity[] = {"C_Programming", "ComputerNetwork", "CoreJava", "DataStructure", "Database"};
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        lv = findViewById(R.id.mymenulist);
        adapter = new ArrayAdapter<String>(MenuActivity.this, R.layout.listview_customdesign, R.id.textView, my_activity);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            String s = "com.example.quizapp." + my_activity[position];
            Class c = Class.forName(s);
            Intent intent = new Intent(MenuActivity.this, c);
            startActivity(intent);
        } catch (Exception e) {
            Log.e("Error", "Error is" + e.getMessage());
        }
    }
}