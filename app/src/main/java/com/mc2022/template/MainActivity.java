package com.mc2022.template;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView gyroX, gyroY, gyroZ, tempVal, lightVal, orietZ, orietX, orietY;
    ToggleButton gToggle, laToggle, tToggle, lToggle, pToggle, oToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView initialization
        gyroX = (TextView) findViewById(R.id.gyroscopeXvalue);
        gyroY = (TextView) findViewById(R.id.gyroscopeYvalue);
        gyroZ = (TextView) findViewById(R.id.gyroscopeZvalue);
        tempVal = (TextView) findViewById(R.id.tempValue);
        lightVal = (TextView) findViewById(R.id.lightValue);
        orietX = (TextView) findViewById(R.id.pitchValue);
        orietY = (TextView) findViewById(R.id.rollValue);
        orietZ = (TextView) findViewById(R.id.azimuthValue);


        //ToggleButton initialization
        gToggle = (ToggleButton) findViewById(R.id.Gtoggle);
        laToggle = (ToggleButton) findViewById(R.id.LAtoggle);
        tToggle = (ToggleButton) findViewById(R.id.Ttoggle);
        lToggle = (ToggleButton) findViewById(R.id.Ltoggle);
        pToggle = (ToggleButton) findViewById(R.id.Ptoggle);
        oToggle = (ToggleButton) findViewById(R.id.Otoggle);


    }
}