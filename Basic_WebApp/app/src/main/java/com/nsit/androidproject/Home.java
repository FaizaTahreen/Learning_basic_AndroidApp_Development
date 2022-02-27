package com.nsit.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Home extends AppCompatActivity implements View.OnClickListener {
private Button btnwebview;
ImageButton tab, crome, facebook, youtube, hotstar, zomato, twitter, filpkart, instagram, amazon;
EditText edtUrl;
private ProgressDialog loadingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnwebview = findViewById(R.id.btnwebview);
        edtUrl = findViewById(R.id.edtUrl);
        tab = findViewById(R.id.tab);
        loadingbar=new ProgressDialog(this);
        crome = findViewById(R.id.crome);
        facebook = findViewById(R.id.facebook);
        filpkart = findViewById(R.id.flipkart);
        zomato = findViewById(R.id.zomato);
        amazon = findViewById(R.id.amazon);
        hotstar = findViewById(R.id.hotstar);
        youtube = findViewById(R.id.youtube);
        twitter = findViewById(R.id.twitter);
        instagram = findViewById(R.id.instagram);
        loadingbar.setTitle("Welcome");
        loadingbar.setMessage("Welcome to the browser app");
        loadingbar.show();
        btnwebview.setOnClickListener(this);
        crome.setOnClickListener(this);
        facebook.setOnClickListener(this);
        filpkart.setOnClickListener(this);
        zomato.setOnClickListener(this);
        amazon.setOnClickListener(this);
        hotstar.setOnClickListener(this);
        youtube.setOnClickListener(this);
        twitter.setOnClickListener(this);
        instagram.setOnClickListener(this);
tab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(getApplicationContext(),Home.class);
        startActivity(i);
    }
});
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }


    @Override
    public void onClick(View view) {
        if(view == btnwebview)
        {
            OpenWebsite();

        }
        if (view==crome)
        {
            Intent crome=new Intent(Home.this, UrlSearch.class);
            crome.putExtra("url_address", "https://www.google.com");
            startActivity(crome);
        }
        if (view==facebook)
        {
            Intent facebook=new Intent(Home.this, UrlSearch.class);
            facebook.putExtra("url_address", "https://www.facebook.com");
            startActivity(facebook);
        }
        if (view==zomato)
        {
            Intent zomato=new Intent(Home.this, UrlSearch.class);
            zomato.putExtra("url_address", "https://www.zomato.com");
            startActivity(zomato);
        }
        if (view==amazon)
        {
            Intent amazon=new Intent(Home.this, UrlSearch.class);
            amazon.putExtra("url_address", "https://www.amazon.com");
            startActivity(amazon);
        }
        if (view==filpkart)
        {
            Intent flipkart=new Intent(Home.this, UrlSearch.class);
            flipkart.putExtra("url_address", "https://www.flipkart.com");
            startActivity(flipkart);
        }
        if (view==twitter)
        {
            Intent twitter=new Intent(Home.this, UrlSearch.class);
            twitter.putExtra("url_address", "https://www.twitter.com");
            startActivity(twitter);
        }
        if (view==youtube)
        {
            Intent youtube=new Intent(Home.this, UrlSearch.class);
            youtube.putExtra("url_address", "https://www.youtube.com");
            startActivity(youtube);
        }
        if (view==hotstar)
        {
            Intent hotstar=new Intent(Home.this, UrlSearch.class);
            hotstar.putExtra("url_address", "https://www.hotstar.com");
            startActivity(hotstar);
        }
        if (view==instagram)
        {
            Intent instagram=new Intent(Home.this, UrlSearch.class);
            instagram.putExtra("url_address", "https://www.instagram.com");
            startActivity(instagram);
        }
    }



    private void OpenWebsite() {
        loadingbar.setTitle("Loading.....");
        loadingbar.setMessage("please wait while we are opening your web address");
        loadingbar.show();
        String Url_Address=edtUrl.getText().toString();
        if (TextUtils.isEmpty(Url_Address))
        {
            Toast empty= Toast.makeText(Home.this,"Please Enter Url Address", Toast.LENGTH_LONG);
            empty.show();
        }
        else {
            String url_without_https=Url_Address.replaceAll("https://www.", "");
            String https= "https://";
            String www="www.";
            Intent search=new Intent(Home.this, UrlSearch.class);
            search.putExtra("url_address",https+www+url_without_https);
            startActivity(search);
            edtUrl.setText("");
            edtUrl.requestFocus();
        }
    }

}
