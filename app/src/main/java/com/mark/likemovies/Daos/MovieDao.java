package com.mark.likemovies.Daos;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mark.likemovies.Models.preidecteitem;

import java.util.List;

public interface MovieDao {
    @Query("SELECT * FROM preidecteitem")
    List<preidecteitem> getAll();
    @Query("SELECT * FROM preidecteitem WHERE liked == 1 ")
    preidecteitem findByName(String id);
    @Insert
    void insertAll(preidecteitem  preidecteitems);


    @Delete
    void delete(preidecteitem preidecteitems);
}
