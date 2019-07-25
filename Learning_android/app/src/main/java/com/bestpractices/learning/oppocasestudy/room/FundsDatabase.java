package com.bestpractices.learning.oppocasestudy.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bestpractices.learning.oppocasestudy.models.FundsModel;
import com.bestpractices.learning.oppocasestudy.utils.Converters;

@Database(entities = FundsModel.class, version = 1)
@TypeConverters({Converters.class})
public abstract class FundsDatabase extends RoomDatabase {
    private static FundsDatabase instance;

    public abstract FundDao noteDao();

    public static synchronized FundsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FundsDatabase.class, "funds_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //Testing
              new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private FundDao noteDao;

        private PopulateDbAsyncTask(FundsDatabase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            noteDao.insert(new FundsModel("Title 1"));
//            noteDao.insert(new FundsModel("Title 2"));
//            noteDao.insert(new FundsModel("Title 3"));
            return null;
        }
    }
}
