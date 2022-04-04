package com.mc2022.template.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mc2022.template.interfaces.TempDAO;
import com.mc2022.template.modelClasses.Temperature;

@Database(entities = {Temperature.class},version = 2)
public abstract class TempDatabase extends RoomDatabase {
    // database object
    public abstract TempDAO tempDAO();

    // instance of database
    // we should have single instance of database and should ensure that our database class should be singleton

    public static TempDatabase tDatainstance;

    public static  TempDatabase getInstance(Context context){

        if(tDatainstance == null){
            tDatainstance = Room.databaseBuilder(context.getApplicationContext(), TempDatabase.class, "temperature_database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }

        return tDatainstance;
    }
}
