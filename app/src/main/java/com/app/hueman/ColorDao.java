package com.app.hueman;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ColorDao {

    @Query("SELECT name FROM Colors WHERE hex == :in_hex LIMIT 1")
    String getName(String in_hex);


}
