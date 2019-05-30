package com.westfall.ad340project;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ZombieMovieDetails extends AppCompatActivity {

    private static final String TAG = "ZombieMovieDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_zombie_movie_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ((ActionBar) ab).setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        //pulling elements values from key on Bundle
        String ImageUrlReceived = bundle.getString("zombieImageURL");
        String TitleReceived = bundle.getString("zombieMovieTitle");
        String YearReceived = bundle.getString("zombieMovieYear");
        String DirectReceived = bundle.getString("zombieMovieDirector");
        String DescReceived = bundle .getString("zombieMovieDescription");

        //creating layout elements
        ImageView zombieMovieImage = (ImageView) findViewById(R.id.zombieMovieImage);
        TextView zombieMovieTitle = (TextView)findViewById(R.id.zombieMovieTitle);
        TextView zombieMovieYear = (TextView)findViewById(R.id.zombieMovieYear);
        TextView zombieMovieDirector = (TextView)findViewById(R.id.zombieMovieDirector);
        TextView zombieMovieDescription = (TextView)findViewById(R.id.zombieMovieDescription);

        //setting text variables to the fields
        zombieMovieTitle.setText(TitleReceived);
        zombieMovieYear.setText(YearReceived);
        zombieMovieDirector.setText(DirectReceived);
        zombieMovieDescription.setText(DescReceived);

        //setting image URL
        Picasso p = Picasso.get();
        p.setIndicatorsEnabled(true);
        p.setLoggingEnabled(true);


        p.load(ImageUrlReceived).into(zombieMovieImage);
    }
}
