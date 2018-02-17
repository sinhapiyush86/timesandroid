package com.times.piyush.finalversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ConnectionFailure extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_failure);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ((Button) findViewById(R.id.buttonref)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ConnectionFailure.this.startActivity(new Intent(ConnectionFailure.this, MainActivity.class));
            }
        });
    }

    public void onBackPressed() {
    }
}
