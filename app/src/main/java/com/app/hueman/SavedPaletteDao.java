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

    @Query("SELECT * FROM SavedPalettes WHERE hex1 == :in_hex1 AND hex2 == :in_hex2 AND hex3 == :in_hex3 AND hex4 == :in_hex4 AND hex5 == :in_hex5")
    public SavedPalette[] loadSavedPalette(String in_hex1, String in_hex2, String in_hex3, String in_hex4, String in_hex5);
}
