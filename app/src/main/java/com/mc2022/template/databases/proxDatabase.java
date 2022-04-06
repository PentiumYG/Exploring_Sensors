package com.mc2022.template.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mc2022.template.interfaces.LADAO;
import com.mc2022.template.interfaces.ProxDAO;
import com.mc2022.template.modelClasses.Proximity;

@Database(entities = {Proximity.class},version = 2)
public abstract class proxDatabase extends RoomDatabase {

    public abstract ProxDAO proxDAO();

    // instance of database
    // we should have single instance of database and should ensure that our database class should be singleton

    public static proxDatabase pDatainstance;

    public static  proxDatabase getInstance(Context context){

        if(pDatainstance == null){
            pDatainstance = Room.databaseBuilder(context.getApplicationContext(),
                    proxDatabase.class,
                    "Proximity_database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }

        return pDatainstance;
    }
}
