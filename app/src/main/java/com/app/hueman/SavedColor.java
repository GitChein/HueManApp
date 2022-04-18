package com.app.hueman;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SavedColors")
public class SavedColor {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "hex")
    public String hex;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "type")
    public String type;
}
