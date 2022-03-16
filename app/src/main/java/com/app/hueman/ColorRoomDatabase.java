package com.app.hueman;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Color.class},
        version = 3,
        exportSchema = true)
public abstract class ColorRoomDatabase extends RoomDatabase {

    public abstract ColorDao colorDao();

    private static volatile ColorRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ColorRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ColorRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ColorRoomDatabase.class, "cdb")
                            .fallbackToDestructiveMigration()
                            .createFromAsset("Color.db")
                            .allowMainThreadQueries()
                            .build();

                }
            }
        }
        return INSTANCE;
    }



}
