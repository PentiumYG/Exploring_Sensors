package com.mc2022.template.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mc2022.template.modelClasses.Gyroscope;

import java.util.List;

@Dao
public interface GyroDAO {
    // list all
    @Query("Select * from GyroscopeTable")
    List<Gyroscope> getList();

    // Insert
    @Insert
    void insert(Gyroscope gyroscope);

    // Delete using id
    @Query("DELETE from GyroscopeTable where id = :id")
    void deleteUsingID(int id);

    // Delete using object
    @Delete
    void delete(Gyroscope gyroscope);


}
