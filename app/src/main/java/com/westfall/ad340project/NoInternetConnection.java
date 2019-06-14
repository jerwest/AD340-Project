package com.westfall.ad340project;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class NoInternetConnection extends AppCompatActivity {

    private static final String TAG = "No Internet Connection";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate(Bundle) called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_connection);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ((ActionBar) ab).setDisplayHomeAsUpEnabled(true);
    }
}
