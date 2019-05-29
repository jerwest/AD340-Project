package com.westfall.ad340project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ZombieMovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zombie_movie_details);

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        //pulling elements values from key on Bundle
        String ImageUrlReceived = bundle.getString("ImageURL");
        String TitleReceived = bundle.getString("Title");
        String YearReceived = bundle.getString("Year");
        String DirectReceived = bundle.getString("Director");
        String DescReceived = bundle .getString("Description");

        //creating layout elements
        ImageView image = (ImageView) findViewById(R.id.zombieMovieImage);
        TextView title = (TextView)findViewById(R.id.zombieMovieTitle);
        TextView year = (TextView)findViewById(R.id.zombieMovieYear);
        TextView director = (TextView)findViewById(R.id.zombieMovieDirector);
        TextView description = (TextView)findViewById(R.id.zombieMovieDescription);

        //setting text variables to the fields
        title.setText(TitleReceived);
        year.setText(YearReceived);
        director.setText(DirectReceived);
        description.setText(DescReceived);
        //setting image URL

        Picasso p = Picasso.get();
        p.setIndicatorsEnabled(true);
        p.setLoggingEnabled(true);


        p.load(ImageUrlReceived).into(image);
    }
}
