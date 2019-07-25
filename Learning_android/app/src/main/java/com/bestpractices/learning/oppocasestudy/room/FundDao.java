package com.bestpractices.learning.oppocasestudy.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bestpractices.learning.oppocasestudy.models.FundsModel;

import java.util.List;
@Dao
public interface FundDao {
    @Insert()
    void insert(FundsModel funds);

    @Update
    void update(FundsModel funds);



    @Query("SELECT * FROM funds_table ")
    LiveData<FundsModel> getAllNotes();
}
