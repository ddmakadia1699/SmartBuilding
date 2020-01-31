package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton light;
    private ImageButton hvac;
    private ImageButton parking;
    private ImageButton tank;
    private ImageButton soil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        light = (ImageButton) findViewById(R.id.light);
        hvac = (ImageButton) findViewById(R.id.hvac);
        parking = (ImageButton) findViewById(R.id.parking);
        tank = (ImageButton) findViewById(R.id.tank);
        soil = (ImageButton) findViewById(R.id.soil);

        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Light Status",Toast.LENGTH_LONG).show();
                Intent mainIntent = new Intent(MainActivity.this, light.class);
                startActivity(mainIntent);
            }
        });

        hvac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"HVAC Status",Toast.LENGTH_LONG).show();
                Intent mainIntent = new Intent(MainActivity.this, hvac.class);
                startActivity(mainIntent);
            }
        });

        parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Parking Status",Toast.LENGTH_LONG).show();
                Intent mainIntent = new Intent(MainActivity.this, parking.class);
                startActivity(mainIntent);
            }
        });

        tank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Water Tank Level",Toast.LENGTH_LONG).show();
                Intent mainIntent = new Intent(MainActivity.this, tank.class);
                startActivity(mainIntent);
            }
        });

        soil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Soil Moisture",Toast.LENGTH_LONG).show();
                Intent mainIntent = new Intent(MainActivity.this, soil.class);
                startActivity(mainIntent);
            }
        });
    }
}
