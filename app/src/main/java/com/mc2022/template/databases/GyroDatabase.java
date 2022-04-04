package com.mc2022.template.databases;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.mc2022.template.interfaces.GyroDAO;
import com.mc2022.template.modelClasses.Gyroscope;

@Database(entities = {Gyroscope.class},version = 1)
public abstract class GyroDatabase extends RoomDatabase {

    // database object
    public abstract GyroDAO gyroDAO();

    // instance of database
    // we should have single instance of database and should ensure that our database class should be singleton

    public static GyroDatabase gDatainstance;

    public static  GyroDatabase getInstance(Context context){

        if(gDatainstance == null){
            gDatainstance = Room.databaseBuilder(context.getApplicationContext(), GyroDatabase.class, "gyroscope_database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }

        return gDatainstance;
    }

}
