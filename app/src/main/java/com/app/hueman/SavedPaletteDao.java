package com.app.hueman;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SavedPaletteDao {

    @Insert
    public void insertSavedPalette(SavedPalette savedPalette);

    @Query("SELECT * FROM SavedPalettes")
    public SavedPalette[] loadAllSavedPalettes();
}
