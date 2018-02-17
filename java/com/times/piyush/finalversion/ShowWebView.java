package com.times.piyush.finalversion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ShowWebView extends Activity {
    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.webView = (WebView) findViewById(R.id.webView1);
        startWebView("http://www.androidexample.com/media/webview/login.html");
    }

    private void startWebView(String url) {
        this.webView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onLoadResource(WebView view, String url) {
                if (this.progressDialog == null) {
                    this.progressDialog = new ProgressDialog(ShowWebView.this);
                    this.progressDialog.setMessage("Loading...");
                    this.progressDialog.show();
                }
            }

            public void onPageFinished(WebView view, String url) {
                try {
                    if (this.progressDialog.isShowing()) {
                        this.progressDialog.dismiss();
                        this.progressDialog = null;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl("http://hithaldia.co.in/times/");
    }

    public void onBackPressed() {
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
