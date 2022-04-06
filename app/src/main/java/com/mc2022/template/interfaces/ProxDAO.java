package com.mc2022.template.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.mc2022.template.modelClasses.Proximity;

import java.util.List;

@Dao
public interface ProxDAO {
    // list all
    @Query("Select * from ProximityTable")
    List<Proximity> getList();

    // Insert
    @Insert
    void insert(Proximity proximity);

    // Delete using id
    @Query("DELETE from ProximityTable where id = :id")
    void deleteUsingID(int id);


    @Query("SELECT * FROM (SELECT * FROM ProximityTable ORDER BY id DESC LIMIT 10) ORDER BY id ASC")
    List<Proximity> getLast10();

    // Delete using object
    @Delete
    void delete(Proximity proximity);
}
