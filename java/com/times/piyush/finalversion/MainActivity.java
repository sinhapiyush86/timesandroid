package com.times.piyush.finalversion;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {
    String comp = "http://hithaldia.co.in/times/index.php";
    ProgressBar progressBar;
    TextView t;
    WebView web;
    String wurl;

    public class myWebClient extends WebViewClient {
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            MainActivity.this.progressBar.setVisibility(0);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            MainActivity.this.startActivity(new Intent(MainActivity.this, ConnectionFailure.class));
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            MainActivity.this.progressBar.setVisibility(0);
            view.loadUrl(url);
            return true;
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            MainActivity.this.wurl = MainActivity.this.web.getUrl();
            MainActivity.this.progressBar.setVisibility(8);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        this.web = (WebView) findViewById(R.id.webView1);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        this.web.setWebViewClient(new myWebClient());
        this.web.setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                new Builder(view.getContext()).setTitle(BuildConfig.FLAVOR).setMessage(message).setPositiveButton("OK", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create().show();
                result.confirm();
                return true;
            }
        });
        this.web.getSettings().setJavaScriptEnabled(true);
        this.web.getSettings().setBuiltInZoomControls(true);
        this.web.getSettings().setDisplayZoomControls(false);
        this.web.getSettings().setDomStorageEnabled(true);
        this.progressBar.setVisibility(0);
        this.web.loadUrl("http://hithaldia.co.in/times/index.php");
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(8388611)) {
            drawer.closeDrawer(8388611);
        } else if (this.comp.equals(this.wurl)) {
            super.onBackPressed();
        } else {
            this.web.goBack();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.action_settings).setVisible(false);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            this.progressBar.setVisibility(0);
            this.web.loadUrl("http://hithaldia.co.in/times/index.php");
        } else if (id == R.id.nav_gallery) {
            this.progressBar.setVisibility(0);
            this.web.loadUrl("http://hithaldia.co.in/times/events.php");
        } else if (id == R.id.nav_slideshow) {
            this.progressBar.setVisibility(0);
            this.web.loadUrl("http://hithaldia.co.in/times/carpe.html");
        } else if (id == R.id.nav_manage) {
            this.progressBar.setVisibility(0);
            this.web.loadUrl("http://hithaldia.co.in/times/issue.html");
        } else if (id == R.id.NOTIFICATION) {
            this.progressBar.setVisibility(0);
            this.web.loadUrl("http://hithaldia.co.in/times/notification.php");
        } else if (id == R.id.nav_share) {
            this.progressBar.setVisibility(0);
            this.web.loadUrl("http://hithaldia.co.in/times/contact.html");
        } else if (id == R.id.nav_send) {
            this.progressBar.setVisibility(0);
            this.web.loadUrl("http://hithaldia.co.in/times/about.html");
        } else if (id == R.id.nav_department) {
            this.progressBar.setVisibility(0);
            this.web.loadUrl("http://hithaldia.co.in/times/department.php");
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(8388611);
        return true;
    }
}
