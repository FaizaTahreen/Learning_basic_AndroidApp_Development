package com.nsit.androidproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class UrlSearch extends AppCompatActivity implements View.OnClickListener {
private Button btnwebview, home;
ImageButton tab;
EditText edtUrl;
WebView webView;
    String url;
    private ProgressDialog loadingbar;
    ProgressDialog pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_search);
        btnwebview=findViewById(R.id.btnwebview);
        loadingbar=new ProgressDialog(this);
        home=findViewById(R.id.home);
        edtUrl=findViewById(R.id.edtUrl);
        pb= new ProgressDialog(this);
        webView=findViewById(R.id.webView);
        url= getIntent().getExtras().get("url_address").toString();
        edtUrl.setText(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
        loadingbar.setTitle("Loading.....");
        loadingbar.setMessage("please wait while we are opening your web address");
        loadingbar.show();
        tab=findViewById(R.id.tab);
        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });
        btnwebview.setOnClickListener(this);
        home.setOnClickListener(this);
        webView.setWebViewClient(new Browser());
        webView.setWebChromeClient(new WebChromeClient()
                                   {
                                       @Override
                                       public void onProgressChanged(WebView view, int newProgress) {
                                           if (newProgress<100)
                                           {
                                               pb.show();
                                           }
                                           else
                                           {
                                               pb.dismiss();
                                           }
                                           super.onProgressChanged(view, newProgress);
                                       }
                                   }

        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view==home){
            finish();
            Intent homepage= new Intent(UrlSearch.this, Home.class);
            startActivity(homepage);
        }
        if (view==btnwebview)
        {
            SearchWebAddress();
        }

    }

    private void SearchWebAddress() {
        loadingbar.setTitle("Loading.....");
        loadingbar.setMessage("please wait while we are opening your web address");
        loadingbar.show();

        String Url_Address=edtUrl.getText().toString();
        if (TextUtils.isEmpty(Url_Address))
        {
            Toast empty= Toast.makeText(UrlSearch.this,"Please Enter Url Address", Toast.LENGTH_LONG);
            empty.show();
        }
        else {
            String url_without_https=Url_Address.replaceAll("https://www.", "");
            String https= "https://";
            String www="www.";
            Intent search=new Intent(UrlSearch.this, Home.class);
            search.putExtra("url_address",https+www+url_without_https);
            startActivity(search);
            edtUrl.setText("");
            edtUrl.requestFocus();
        }
    }

    private class Browser extends WebViewClient
    {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            webView.loadUrl(String.valueOf(request.getUrl()));
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if(webView!=null && webView.canGoBack())
        {
            webView.goBack();
        }
        else
            super.onBackPressed();

    }
}