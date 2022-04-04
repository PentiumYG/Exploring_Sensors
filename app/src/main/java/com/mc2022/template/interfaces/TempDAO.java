package com.mc2022.template.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mc2022.template.modelClasses.Gyroscope;
import com.mc2022.template.modelClasses.Temperature;

import java.util.List;

@Dao
public interface TempDAO {
    @Query("Select * from TemperatureTable")
    List<Temperature> getList();

    // Insert
    @Insert
    void insert(Temperature temperature);

    // Delete using id
    @Query("DELETE from TemperatureTable where id = :id")
    void deleteUsingID(int id);

    // Delete using object
    @Delete
    void delete(Temperature temperature);

}
