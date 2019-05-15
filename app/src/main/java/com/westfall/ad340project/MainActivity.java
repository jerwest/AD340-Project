package com.westfall.ad340project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    Button btn1 = (Button)findViewById(R.id.button1);
    btn1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Zombie Movies", Toast.LENGTH_SHORT).show();
        }
    });

    Button btn2 = (Button)findViewById(R.id.button2);
    btn2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Button #2", Toast.LENGTH_SHORT).show();
        }
    });

    Button btn3 = (Button)findViewById(R.id.button3);
    btn3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Button #3", Toast.LENGTH_SHORT).show();
        }
    });

    Button btn4 = (Button)findViewById(R.id.button4);
    btn4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Button #4", Toast.LENGTH_SHORT).show();
        }
    });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about,menu);

        // Intent E = new Intent(MainActivity.this, traffic_camera.class);

        // startActivity(E);

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent E = new Intent(MainActivity.this, About.class);

                startActivity(E);
                //Toast.makeText(this,"item 1 selected",Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

}
