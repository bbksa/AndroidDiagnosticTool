package com.example.diagnostictool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CardView SoundcardView = (CardView) findViewById(R.id.c2);
        SoundcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSound();
            }
        });

        CardView SensorcardView = (CardView) findViewById(R.id.c4);
        SensorcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSensor();
            }
        });

        CardView BatterycardView = (CardView) findViewById(R.id.c5);
        BatterycardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBattery();
            }
        });

        CardView connectcardView = (CardView) findViewById(R.id.c3);
        connectcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openconnect();
            }
        });

        CardView CameracardView = (CardView) findViewById(R.id.c6);
        CameracardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });
    }
    public void openSound(){
        Intent intentsound = new Intent(this, Sound.class);
        startActivity(intentsound);
    }
    public void openconnect(){
        Intent intentconnect = new Intent(this, Connection.class);
        startActivity(intentconnect);
    }
    public void openSensor(){
        Intent intentSesor = new Intent(this, Sensor.class);
        startActivity(intentSesor);
    }
    public void openBattery(){
        Intent intentBattery = new Intent(this, Battery.class);
        startActivity(intentBattery);
    }
    public void openCamera(){
        Intent intentCam = new Intent(this, Camera.class);
        startActivity(intentCam);
    }

}