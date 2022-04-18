package com.app.hueman;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SavedColorDao {

    @Query("SELECT * FROM SavedColors WHERE hex == :in_hex LIMIT 1")
    public SavedColor getColor(String in_hex);

    @Insert
    public void insertSavedColor(SavedColor savedColor);



}
