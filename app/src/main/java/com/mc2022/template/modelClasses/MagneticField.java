package com.mc2022.template.modelClasses;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "MagneticFieldTable")
public class MagneticField {
    public MagneticField(Float magX, Float magY, Float magZ) {
        this.id = id;
        this.magX = magX;
        this.magY = magY;
        this.magZ = magZ;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "X - Axis")
    private Float magX;

    @ColumnInfo(name = "Y - Axis")
    private Float magY;

    @ColumnInfo(name = "Z - Axis")
    private Float magZ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getMagX() {
        return magX;
    }

    public void setMagX(Float magX) {
        this.magX = magX;
    }

    public Float getMagY() {
        return magY;
    }

    public void setMagY(Float magY) {
        this.magY = magY;
    }

    public Float getMagZ() {
        return magZ;
    }

    public void setMagZ(Float magZ) {
        this.magZ = magZ;
    }
}
