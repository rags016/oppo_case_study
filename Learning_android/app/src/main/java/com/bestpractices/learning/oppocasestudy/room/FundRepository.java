package com.bestpractices.learning.oppocasestudy.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.bestpractices.learning.oppocasestudy.models.FundsModel;

public class FundRepository {
    private FundDao fundsDao;
    private LiveData<FundsModel> allNotes;

    public FundRepository(Application application) {
        FundsDatabase database = FundsDatabase.getInstance(application);
        fundsDao = database.noteDao();
        allNotes = fundsDao.getAllNotes();
    }

    public void insert(FundsModel news) {
        new InsertNoteAsyncTask(fundsDao).execute(news);
    }

//    public void update(FundsModel news) {
//        new UpdateNoteAsyncTask(fundsDao).execute(news);
//    }
//
//    public void delete(News news) {
//        new DeleteNoteAsyncTask(noteDao).execute(news);
//    }

    public LiveData<FundsModel> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<FundsModel, Void, Void> {
        private FundDao fundDao;

        private InsertNoteAsyncTask(FundDao fundDao) {
            this.fundDao = fundDao;
        }

        @Override
        protected Void doInBackground(FundsModel... fundsModels) {
            fundDao.insert(fundsModels[0]);
            return null;
        }
    }
}
