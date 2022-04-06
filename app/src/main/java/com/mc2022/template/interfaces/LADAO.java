package com.mc2022.template.interfaces;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mc2022.template.modelClasses.Gyroscope;
import com.mc2022.template.modelClasses.LinearAcceleration;

import java.util.List;

@Dao
public interface LADAO {
    // list all
    @Query("Select * from LinearAccelerationTable")
    List<LinearAcceleration> getList();

    // Insert
    @Insert
    void insert(LinearAcceleration linearAcceleration);

    // Delete using id
    @Query("DELETE from LinearAccelerationTable where id = :id")
    void deleteUsingID(int id);


    @Query("SELECT * FROM (SELECT * FROM LinearAccelerationTable ORDER BY id DESC LIMIT 10) ORDER BY id ASC")
    List<LinearAcceleration> getLast10();

    // Delete using object
    @Delete
    void delete(LinearAcceleration linearAcceleration);

}
