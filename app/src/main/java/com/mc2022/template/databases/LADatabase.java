package com.mc2022.template.databases;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mc2022.template.interfaces.GyroDAO;
import com.mc2022.template.interfaces.LADAO;
import com.mc2022.template.modelClasses.LinearAcceleration;

@Database(entities = {LinearAcceleration.class},version = 2)
public abstract class LADatabase extends RoomDatabase {

    public abstract LADAO laDAO();

    // instance of database
    // we should have single instance of database and should ensure that our database class should be singleton

    public static LADatabase laDatainstance;

    public static  LADatabase getInstance(Context context){

        if(laDatainstance == null){
            laDatainstance = Room.databaseBuilder(context.getApplicationContext(),
                    LADatabase.class,
                    "LinearAcc_database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }

        return laDatainstance;
    }
}
