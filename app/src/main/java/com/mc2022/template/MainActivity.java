package com.mc2022.template;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView gyroX, gyroY, gyroZ, tempVal, lightVal, orietZ, orietX, orietY, lVal, loVal;
    ToggleButton gToggle, laToggle, tToggle, lToggle, pToggle, oToggle, gpsToggle;
    LocationManager locManager;
    LocationListener locLis;




    class LocationFunc implements LocationListener{

        @Override
        public void onLocationChanged(@NonNull Location location) {
            lVal.setText(String.valueOf(location.getLatitude()));
            loVal.setText(String.valueOf(location.getLongitude()));
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
            LocationListener.super.onProviderDisabled(provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

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
        lVal = (TextView) findViewById(R.id.latValue);
        loVal = (TextView) findViewById(R.id.longValue);


        //ToggleButton initialization
        gToggle = (ToggleButton) findViewById(R.id.Gtoggle);
        laToggle = (ToggleButton) findViewById(R.id.LAtoggle);
        tToggle = (ToggleButton) findViewById(R.id.Ttoggle);
        lToggle = (ToggleButton) findViewById(R.id.Ltoggle);
        pToggle = (ToggleButton) findViewById(R.id.Ptoggle);
        oToggle = (ToggleButton) findViewById(R.id.Otoggle);
        gpsToggle = (ToggleButton) findViewById(R.id.gpsToggle);

        //Sensor Service
        SensorManager sensorMan = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        gToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(gToggle.isChecked()){
                    Sensor gyro = sensorMan.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                    if(gyro !=null){
                        sensorMan.registerListener((SensorEventListener) MainActivity.this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, "Gyroscope Sensor Activated..!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.i("Gyro-check","Gyroscope NOT Supported!!");
                    }
                }
                else{
                    sensorMan.unregisterListener(MainActivity.this, sensorMan.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
                    gyroX.setText("0");
                    gyroY.setText("0");
                    gyroZ.setText("0");
                    Toast.makeText(MainActivity.this, "Gyroscope Sensor De-Activated..!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(lToggle.isChecked()){
                    Sensor light = sensorMan.getDefaultSensor(Sensor.TYPE_LIGHT);
                    if(light !=null){
                        sensorMan.registerListener((SensorEventListener) MainActivity.this, light, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, "Light Sensor Activated..!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.i("Light-check","Light NOT Supported!!");
                    }
                }
                else{
                    sensorMan.unregisterListener(MainActivity.this, sensorMan.getDefaultSensor(Sensor.TYPE_LIGHT));
                    lightVal.setText("0");
                    Toast.makeText(MainActivity.this, "Light Sensor De-Activated..!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(tToggle.isChecked()){
                    Sensor temperature = sensorMan.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
                    if(temperature !=null){
                        sensorMan.registerListener((SensorEventListener) MainActivity.this, temperature, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, "Temperature Sensor Activated..!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.i("Temp-check","Temperature NOT Supported!!");
                    }
                }
                else{
                    sensorMan.unregisterListener(MainActivity.this, sensorMan.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE));
                    tempVal.setText("0");
                    Toast.makeText(MainActivity.this, "Temperature Sensor De-Activated..!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        oToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(oToggle.isChecked()){
                    Sensor orientation = sensorMan.getDefaultSensor(Sensor.TYPE_ORIENTATION);
                    if(orientation !=null){
                        sensorMan.registerListener((SensorEventListener) MainActivity.this, orientation, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, "Orientation Sensor Activated..!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.i("Orientation-check","Orientation NOT Supported!!");
                    }
                }
                else{
                    sensorMan.unregisterListener(MainActivity.this, sensorMan.getDefaultSensor(Sensor.TYPE_ORIENTATION));
                    orietX.setText("0");
                    orietY.setText("0");
                    orietZ.setText("0");
                    Toast.makeText(MainActivity.this, "Orientation Sensor De-Activated..!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gpsToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(gpsToggle.isChecked()){
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},1);
                    }
                    locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    locLis = new LocationFunc();
                    locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, locLis);
                    Toast.makeText(MainActivity.this, "GPS Activated..!", Toast.LENGTH_SHORT).show();
                }
                else{
                    locManager.removeUpdates(locLis);
                    Toast.makeText(MainActivity.this, "GPS De-Activated..!", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sen = sensorEvent.sensor;
        if(sen.getType() == Sensor.TYPE_GYROSCOPE){
            Log.i("Value-check", "Gyroscope X-axis:" + sensorEvent.values[0] + "Y-axis:" + sensorEvent.values[1]
                    + "Z-axis:" + sensorEvent.values[2]);
            gyroX.setText(Float.toString(sensorEvent.values[0]));
            gyroY.setText(Float.toString(sensorEvent.values[1]));
            gyroZ.setText(Float.toString(sensorEvent.values[2]));
        }
        else if(sen.getType() == Sensor.TYPE_LIGHT){
            Log.i("Value-check", "Light:" + sensorEvent.values[0]);
            lightVal.setText(Float.toString(sensorEvent.values[0]));
        }
        else if(sen.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            Log.i("Value-check", "Temperature:" + sensorEvent.values[0]);
            tempVal.setText(Float.toString(sensorEvent.values[0]));
        }

        else if(sen.getType() == Sensor.TYPE_ORIENTATION){
            Log.i("Value-check", "Orientation X-axis:" + sensorEvent.values[1] + "Y-axis:" + sensorEvent.values[2]
                    + "Z-axis:" + sensorEvent.values[0]);
            orietZ.setText(Float.toString(sensorEvent.values[0]));
            orietX.setText(Float.toString(sensorEvent.values[1]));
            orietY.setText(Float.toString(sensorEvent.values[2]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}