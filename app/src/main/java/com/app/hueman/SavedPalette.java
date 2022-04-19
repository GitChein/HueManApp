package com.app.hueman;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SavedPalettes")
public class SavedPalette {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "hex1")
    public String hex1;

    @ColumnInfo(name = "hex2")
    public String hex2;

    @ColumnInfo(name = "hex3")
    public String hex3;

    @ColumnInfo(name = "hex4")
    public String hex4;

    @ColumnInfo(name = "hex5")
    public String hex5;
}
