package com.mc2022.template.modelClasses;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "LinearAccelerationTable")
public class LinearAcceleration {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "X - Axis")
    private Float laccX;

    @ColumnInfo(name = "Y - Axis")
    private Float laccY;

    @ColumnInfo(name = "Z - Axis")
    private Float laccZ;

    public LinearAcceleration(Float laccX, Float laccY, Float laccZ) {
        this.laccX = laccX;
        this.laccY = laccY;
        this.laccZ = laccZ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getLaccX() {
        return laccX;
    }

    public void setLaccX(Float laccX) {
        this.laccX = laccX;
    }

    public Float getLaccY() {
        return laccY;
    }

    public void setLaccY(Float laccY) {
        this.laccY = laccY;
    }

    public Float getLaccZ() {
        return laccZ;
    }

    public void setLaccZ(Float laccZ) {
        this.laccZ = laccZ;
    }
}
