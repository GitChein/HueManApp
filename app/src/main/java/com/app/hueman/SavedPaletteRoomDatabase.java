package com.app.hueman;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SavedPalette.class},
        version = 3,
        exportSchema = true)
public abstract class SavedPaletteRoomDatabase extends RoomDatabase {

    public abstract SavedPaletteDao savedPaletteDao();

    private static volatile SavedPaletteRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SavedPaletteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SavedPaletteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SavedPaletteRoomDatabase.class, "sc_db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();

                }
            }
        }
        return INSTANCE;
    }



}
