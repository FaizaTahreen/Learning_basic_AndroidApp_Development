package com.eict.nsit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
ImageButton im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im=findViewById(R.id.ib);
    }
    public void browser(View view)
    {
        Toast.makeText(this, "Welcome to NSIT", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainActivity.this, WebViewActivity.class);
        startActivity(intent);
    }
}