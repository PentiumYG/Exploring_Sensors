package com.mc2022.template.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mc2022.template.modelClasses.MagneticField;

import java.util.List;

@Dao
public interface mfDAO {

    @Query("Select * from MagneticFieldTable")
    List<MagneticField> getList();

    // Insert
    @Insert
    void insert(MagneticField magneticField);

    // Delete using id
    @Query("DELETE from MagneticFieldTable where id = :id")
    void deleteUsingID(int id);

    // Delete using object
    @Delete
    void delete(MagneticField magneticField);
}
