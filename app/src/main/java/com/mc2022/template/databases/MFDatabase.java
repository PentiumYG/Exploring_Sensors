package com.mc2022.template.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.mc2022.template.interfaces.mfDAO;

import com.mc2022.template.modelClasses.MagneticField;

@Database(entities = {MagneticField.class},version = 1)
public abstract class MFDatabase extends RoomDatabase {
    public abstract mfDAO mfDAO();
    public static MFDatabase mfDatainstance;

    public static  MFDatabase getInstance(Context context){

        if(mfDatainstance == null){
            mfDatainstance = Room.databaseBuilder(context.getApplicationContext(), MFDatabase.class, "magnetic_field_database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }

        return mfDatainstance;
    }
}
