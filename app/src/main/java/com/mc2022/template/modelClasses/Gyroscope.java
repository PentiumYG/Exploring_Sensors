package com.mc2022.template.modelClasses;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "GyroscopeTable")
public class Gyroscope {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "X - Axis")
    private Float gyroX;

    @ColumnInfo(name = "Y - Axis")
    private Float gyroY;

    @ColumnInfo(name = "Z - Axis")
    private Float gyroZ;

    public Gyroscope(Float gyroX, Float gyroY, Float gyroZ) {
        //this.id = id;
        this.gyroX = gyroX;
        this.gyroY = gyroY;
        this.gyroZ = gyroZ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getGyroX() {
        return gyroX;
    }

    public void setGyroX(Float gyroX) {
        this.gyroX = gyroX;
    }

    public Float getGyroY() {
        return gyroY;
    }

    public void setGyroY(Float gyroY) {
        this.gyroY = gyroY;
    }

    public Float getGyroZ() {
        return gyroZ;
    }

    public void setGyroZ(Float gyroZ) {
        this.gyroZ = gyroZ;
    }
}
