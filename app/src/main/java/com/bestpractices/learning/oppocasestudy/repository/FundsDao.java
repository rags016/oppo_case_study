package com.bestpractices.learning.oppocasestudy.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.bestpractices.learning.oppocasestudy.models.Funds;
import java.util.List;

@Dao
public interface FundsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Funds funds);

    @Query("SELECT * From funds_table")
    LiveData<List<Funds>> getAllFundsFromDb();
}
