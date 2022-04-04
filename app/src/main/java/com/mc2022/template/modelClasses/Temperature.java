package com.mc2022.template.modelClasses;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "TemperatureTable")
public class Temperature {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;


    @ColumnInfo(name = "Temperature Value")
    @NonNull
    private Float temp;

    public Temperature(Float temp) {
        this.id = id;
        this.temp = temp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }
}
