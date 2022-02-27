package com.nsit.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb=findViewById(R.id.progressBar);
        new Thread()
        {
            public void run()
            {
                try {
                    for (int i=0; i<=20; i++)
                    {
                        pb.setProgress(5*i);
                        Thread.sleep(100);
                    }
                    Intent intent=new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {

                }
            }
        }.start();
    }
}

