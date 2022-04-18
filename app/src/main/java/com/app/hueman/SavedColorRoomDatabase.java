package com.app.hueman;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SavedColor.class},
        version = 1,
        exportSchema = true)
public abstract class SavedColorRoomDatabase extends RoomDatabase {

    public abstract SavedColorDao savedColorDao();

    private static volatile SavedColorRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SavedColorRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SavedColorRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SavedColorRoomDatabase.class, "sc_db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();

                }
            }
        }
        return INSTANCE;
    }



}
