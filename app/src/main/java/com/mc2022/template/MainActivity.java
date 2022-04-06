package com.mc2022.template;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.mc2022.template.databases.GyroDatabase;
import com.mc2022.template.databases.LADatabase;
import com.mc2022.template.databases.LightDatabase;
import com.mc2022.template.databases.MFDatabase;
import com.mc2022.template.databases.TempDatabase;
import com.mc2022.template.modelClasses.Gyroscope;
import com.mc2022.template.modelClasses.Light;
import com.mc2022.template.modelClasses.LinearAcceleration;
import com.mc2022.template.modelClasses.MagneticField;
import com.mc2022.template.modelClasses.Temperature;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView gyroX, gyroY, gyroZ, tempVal, lightVal, orietZ, orietX, orietY, lVal, loVal, laValX, laValY, laValZ;
    ToggleButton gToggle, laToggle, tToggle, lToggle, pToggle, oToggle, gpsToggle;
    LocationManager locManager;
    LocationListener locLis;

    LineChart laLineChart, pLineChart;

    float totalLA[] = new float[10];




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
        laValX = (TextView) findViewById(R.id.laXvalue);
        laValY = (TextView) findViewById(R.id.laYvalue);
        laValZ = (TextView) findViewById(R.id.laZvalue);


        //ToggleButton initialization
        gToggle = (ToggleButton) findViewById(R.id.Gtoggle);
        laToggle = (ToggleButton) findViewById(R.id.LAtoggle);
        tToggle = (ToggleButton) findViewById(R.id.Ttoggle);
        lToggle = (ToggleButton) findViewById(R.id.Ltoggle);
        pToggle = (ToggleButton) findViewById(R.id.Ptoggle);
        oToggle = (ToggleButton) findViewById(R.id.Otoggle);
        gpsToggle = (ToggleButton) findViewById(R.id.gpsToggle);


        laLineChart = (LineChart) findViewById(R.id.laLineChart);
        pLineChart = (LineChart) findViewById(R.id.pLineChart);

        laLineChart.setTouchEnabled(true);
        laLineChart.setPinchZoom(true);
        pLineChart.setTouchEnabled(true);
        pLineChart.setPinchZoom(true);


        //Line Chart Testing
//        ArrayList<Entry> values = new ArrayList<>();
//        values.add(new Entry(1, 50));
//        values.add(new Entry(2, 100));
//        values.add(new Entry(3, 140));
//        values.add(new Entry(4, 150));
//        values.add(new Entry(5, 270));
//
//
//
//        LineDataSet set1;
//        if (laLineChart.getData() != null &&
//                laLineChart.getData().getDataSetCount() > 0) {
//            set1 = (LineDataSet) laLineChart.getData().getDataSetByIndex(0);
//            set1.setValues(values);
//            laLineChart.getData().notifyDataChanged();
//            laLineChart.notifyDataSetChanged();
//            laLineChart.getDescription().setEnabled(false);
//        } else {
//            set1 = new LineDataSet(values, "Sample Data");
//            set1.setDrawIcons(false);
//            set1.enableDashedLine(10f, 5f, 0f);
//            set1.enableDashedHighlightLine(10f, 5f, 0f);
//            set1.setColor(Color.DKGRAY);
//            set1.setCircleColor(Color.DKGRAY);
//            set1.setLineWidth(1f);
//            set1.setCircleRadius(3f);
//            set1.setDrawCircleHole(false);
//            set1.setValueTextSize(9f);
//            set1.setDrawFilled(true);
//            set1.setFormLineWidth(1f);
//            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
//            set1.setFormSize(15.f);
//            if (Utils.getSDKInt() >= 18) {
//                Drawable drawable = ContextCompat.getDrawable(this, R.color.teal_200);
//                set1.setFillDrawable(drawable);
//            } else {
//                set1.setFillColor(Color.DKGRAY);
//            }
//            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//            dataSets.add(set1);
//            LineData data = new LineData(dataSets);
//            laLineChart.setData(data);
//        }


        



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
                        Toast.makeText(MainActivity.this, "Magnetic Field Sensor Activated..!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.i("Magnetic Field-check","Magnetic Field NOT Supported!!");
                    }
                }
                else{
                    sensorMan.unregisterListener(MainActivity.this, sensorMan.getDefaultSensor(Sensor.TYPE_ORIENTATION));
                    orietX.setText("0");
                    orietY.setText("0");
                    orietZ.setText("0");
                    Toast.makeText(MainActivity.this, "Magnetic Field Sensor De-Activated..!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        laToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(laToggle.isChecked()){
                    Sensor linearAcc = sensorMan.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
                    if(linearAcc !=null){
                        sensorMan.registerListener((SensorEventListener) MainActivity.this, linearAcc, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, "Linear Acceleration Sensor Activated..!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.i("Linear Accel-check","Linear Acceleration NOT Supported!!");
                    }

                }
                else{
                    sensorMan.unregisterListener(MainActivity.this, sensorMan.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION));
                    laValX.setText("0");
                    laValY.setText("0");
                    laValZ.setText("0");

                    //Line Chart
                    ArrayList<Entry> values = new ArrayList<>();
                    values.add(new Entry(1, totalLA[9]));
                    values.add(new Entry(2, totalLA[8]));
                    values.add(new Entry(3, totalLA[7]));
                    values.add(new Entry(4, totalLA[6]));
                    values.add(new Entry(5, totalLA[5]));
                    values.add(new Entry(6, totalLA[4]));
                    values.add(new Entry(7, totalLA[3]));
                    values.add(new Entry(8, totalLA[2]));
                    values.add(new Entry(9, totalLA[1]));
                    values.add(new Entry(10, totalLA[0]));

                    //dummy data
//                    values.add(new Entry(1, 3));
//                    values.add(new Entry(2, 5));
//                    values.add(new Entry(3, 4));
//                    values.add(new Entry(4, 6));
//                    values.add(new Entry(5, 3));
//                    values.add(new Entry(6, 5));
//                    values.add(new Entry(7, 4));
//                    values.add(new Entry(8, 6));
//                    values.add(new Entry(9, 3));
//                    values.add(new Entry(10, 5));



                    LineDataSet set1;
                    if (laLineChart.getData() != null &&
                            laLineChart.getData().getDataSetCount() > 0) {
                        set1 = (LineDataSet) laLineChart.getData().getDataSetByIndex(0);
                        set1.setValues(values);
                        laLineChart.getData().notifyDataChanged();
                        laLineChart.notifyDataSetChanged();
                        laLineChart.getDescription().setEnabled(false);
                    } else {
                        set1 = new LineDataSet(values, "Sample Data");
                        set1.setDrawIcons(false);
                        set1.enableDashedLine(10f, 5f, 0f);
                        set1.enableDashedHighlightLine(10f, 5f, 0f);
                        set1.setColor(Color.DKGRAY);
                        set1.setCircleColor(Color.DKGRAY);
                        set1.setLineWidth(1f);
                        set1.setCircleRadius(3f);
                        set1.setDrawCircleHole(false);
                        set1.setValueTextSize(9f);
                        set1.setDrawFilled(true);
                        set1.setFormLineWidth(1f);
                        set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
                        set1.setFormSize(15.f);
                        if (Utils.getSDKInt() >= 18) {
                            Drawable drawable = ContextCompat.getDrawable(MainActivity.this, R.color.teal_200);
                            set1.setFillDrawable(drawable);
                        } else {
                            set1.setFillColor(Color.DKGRAY);
                        }
                        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                        dataSets.add(set1);
                        LineData data = new LineData(dataSets);
                        laLineChart.setData(data);
                    }

                    Toast.makeText(MainActivity.this, "Linear Acceleration Sensor De-Activated..!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //GPS related
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

            //Database related
            GyroDatabase gdb = GyroDatabase.getInstance(MainActivity.this);
            Gyroscope gyroscope = new Gyroscope(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
            gdb.gyroDAO().insert(gyroscope);

            gyroX.setText(Float.toString(sensorEvent.values[0]));
            gyroY.setText(Float.toString(sensorEvent.values[1]));
            gyroZ.setText(Float.toString(sensorEvent.values[2]));

            // Get List
            List<Gyroscope> gEntries = gdb.gyroDAO().getList();

            String outputGyro = "";
            for(Gyroscope g : gEntries)
            {
                outputGyro += Integer.toString(g.getId()) + " " + Float.toString(g.getGyroX()) + " " + Float.toString(g.getGyroY()) + " " + Float.toString(g.getGyroZ()) + "\n";
//                gyroX.setText(Float.toString(g.getGyroX()));
//                gyroY.setText(Float.toString(g.getGyroY()));
//                gyroZ.setText(Float.toString(g.getGyroZ()));
            }


            Log.i("Gyroscope Output : ",outputGyro);


        }
        else if(sen.getType() == Sensor.TYPE_LIGHT){
            Log.i("Value-check", "Light:" + sensorEvent.values[0]);
            //Database related
            LightDatabase ldb = LightDatabase.getInstance(MainActivity.this);
            Light light = new Light(sensorEvent.values[0]);
            ldb.lightDAO().insert(light);

            // Get List
            List<Light> lEntries = ldb.lightDAO().getList();

            String outputLight = "";
            for(Light l : lEntries)
            {
                outputLight += Integer.toString(l.getId()) + " " + Float.toString(l.getLight()) + "\n";
                lightVal.setText(Float.toString(l.getLight()));
            }

            Log.i("Temperature Output : ",outputLight);
        }
        else if(sen.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
            Log.i("Value-check", "Linear Acceleration X-axis:" + sensorEvent.values[0] + "Y-axis:" + sensorEvent.values[1]
                    + "Z-axis:" + sensorEvent.values[2]);

            //Database related
            LADatabase ladb = LADatabase.getInstance(MainActivity.this);
            LinearAcceleration linearAcceleration = new LinearAcceleration(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
            ladb.laDAO().insert(linearAcceleration);

            // Get List
            //List<LinearAcceleration> laEntries = ladb.laDAO().getList();

            List<LinearAcceleration> last10LA  = ladb.laDAO().getLast10();

            for(int i=0;i<10;i++){
                totalLA[i] = (sensorEvent.values[0]+sensorEvent.values[1]+sensorEvent.values[2])/3;
            }



            laValX.setText(Float.toString(sensorEvent.values[0]));
            laValY.setText(Float.toString(sensorEvent.values[1]));
            laValZ.setText(Float.toString(sensorEvent.values[2]));

            String outputLA = "";
          //  String outTotal = "";
            for(LinearAcceleration la : last10LA)
            {
                outputLA += Integer.toString(la.getId()) + " " + Float.toString(la.getLaccX()) + " " + Float.toString(la.getLaccY()) + " " + Float.toString(la.getLaccZ()) + "\n";
                //outTotal += Integer.toString(la.getId()) + " " + Float.toString(la.getLaccX()) + " " + Float.toString(la.getLaccY()) + " " + Float.toString(la.getLaccZ()) + "\n";
            }


            Log.i("LinearAcc Output : ",outputLA);




        }
        else if(sen.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            Log.i("Value-check", "Temperature:" + sensorEvent.values[0]);

            //Database related
            TempDatabase tdb = TempDatabase.getInstance(MainActivity.this);
            Temperature temperature = new Temperature(sensorEvent.values[0]);
            tdb.tempDAO().insert(temperature);

            // Get List
            List<Temperature> tEntries = tdb.tempDAO().getList();

            String outputTemp = "";
            for(Temperature temp : tEntries)
            {
                outputTemp += Integer.toString(temp.getId()) + " " + Float.toString(temp.getTemp()) + "\n";
                tempVal.setText(Float.toString(temp.getTemp()));
            }

            Log.i("Temperature Output : ",outputTemp);
        }

        else if(sen.getType() == Sensor.TYPE_ORIENTATION){
            Log.i("Value-check", "Magnetic Field X-axis:" + sensorEvent.values[0] + "Y-axis:" + sensorEvent.values[1]
                    + "Z-axis:" + sensorEvent.values[2]);

            //Database related
            MFDatabase mfdb = MFDatabase.getInstance(MainActivity.this);
            MagneticField magneticField = new MagneticField(sensorEvent.values[1], sensorEvent.values[2], sensorEvent.values[0]);
            mfdb.mfDAO().insert(magneticField);

            // Get List
            List<MagneticField> mfEntries = mfdb.mfDAO().getList();

            String outputMf = "";
            for(MagneticField mf : mfEntries)
            {
                outputMf += Integer.toString(mf.getId()) + " " + Float.toString(mf.getMagX()) + " " + Float.toString(mf.getMagY()) + " " + Float.toString(mf.getMagZ()) + "\n";
                orietX.setText(Float.toString(mf.getMagX()));
                orietY.setText(Float.toString(mf.getMagY()));
                orietZ.setText(Float.toString(mf.getMagZ()));
            }


            Log.i("MagneticField Output : ",outputMf);

        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}