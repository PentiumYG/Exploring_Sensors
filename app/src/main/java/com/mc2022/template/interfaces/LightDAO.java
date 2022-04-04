package com.mc2022.template.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mc2022.template.modelClasses.Light;
import com.mc2022.template.modelClasses.Temperature;

import java.util.List;

@Dao
public interface LightDAO {
    @Query("Select * from LightTable")
    List<Light> getList();

    // Insert
    @Insert
    void insert(Light light);

    // Delete using id
    @Query("DELETE from LightTable where id = :id")
    void deleteUsingID(int id);

    // Delete using object
    @Delete
    void delete(Light light);
}
