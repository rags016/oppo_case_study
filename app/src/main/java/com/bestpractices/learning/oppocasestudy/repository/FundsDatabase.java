package com.bestpractices.learning.oppocasestudy.repository;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bestpractices.learning.oppocasestudy.models.Funds;

@Database(entities = {Funds.class}, version = 4)
public abstract class FundsDatabase extends RoomDatabase {
    public static final String TAG = "atul";
    private static FundsDatabase INSTANCE;
    public abstract FundsDao fundsDao();

    public static FundsDatabase getDatabase(final Context context){
        Log.d(TAG, "getDatabase: ");
        if(INSTANCE == null){
            synchronized (FundsDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FundsDatabase.class,"funds_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
