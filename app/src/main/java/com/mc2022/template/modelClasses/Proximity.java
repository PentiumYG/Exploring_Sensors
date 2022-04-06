package com.mc2022.template.modelClasses;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ProximityTable")
public class Proximity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "Proximity")
    private Float prox;

    public Proximity(Float prox) {
        this.prox = prox;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getProx() {
        return prox;
    }

    public void setProx(Float prox) {
        this.prox = prox;
    }
}
