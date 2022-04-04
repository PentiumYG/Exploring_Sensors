package com.mc2022.template.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mc2022.template.interfaces.LightDAO;
import com.mc2022.template.modelClasses.Light;

@Database(entities = {Light.class},version = 1)
public abstract class LightDatabase extends RoomDatabase {
    public abstract LightDAO lightDAO();

    public static LightDatabase lDatainstance;

    public static  LightDatabase getInstance(Context context){

        if(lDatainstance == null){
            lDatainstance = Room.databaseBuilder(context.getApplicationContext(), LightDatabase.class,
                    "light_database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }

        return lDatainstance;
    }
}
