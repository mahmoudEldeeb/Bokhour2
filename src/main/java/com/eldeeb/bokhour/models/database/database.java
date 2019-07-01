package com.eldeeb.bokhour.models.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {CartItem.class},version = 2)

public abstract class database extends RoomDatabase {
    public abstract CartDao dao();
    private static volatile database INSTANCE;

    public static database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            database.class, "cartItems")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
