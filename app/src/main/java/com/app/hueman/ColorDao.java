package com.app.hueman;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ColorDao {

    @Query("SELECT color_name FROM color WHERE hex LIKE :in_hex")
    String getName(String in_hex);

}
