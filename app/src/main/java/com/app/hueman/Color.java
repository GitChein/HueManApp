package com.app.hueman;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Color")
public class Color {
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "hex")
    public String hex;

    @ColumnInfo(name = "color_name")
    public String color_name;
}
