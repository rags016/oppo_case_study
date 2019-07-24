package com.bestpractices.learning.oppocasestudy.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.bestpractices.learning.oppocasestudy.models.Funds;

import java.util.List;

public class FundsRepository {
    private static final String  TAG= "atul";
    private FundsDao fundsDao;
    private LiveData<List<Funds>> mAllFunds;

    public FundsRepository(Application application) {
        FundsDatabase db = FundsDatabase.getDatabase(application);
        fundsDao =  db.fundsDao();
        mAllFunds = fundsDao.getAllFundsFromDb();
        Log.d(TAG, "FundsRepository: " + mAllFunds.getValue());

    }

    public LiveData<List<Funds>> getmAllFunds(){
        return mAllFunds;
    }
    public void insert(Funds fundsModel){
        Log.d(TAG, "insert: ");
        new insertAsyncTask(fundsDao).execute(fundsModel);

    }

    public static class insertAsyncTask extends AsyncTask<Funds,Void,Void>{

        private FundsDao mAsyncDao;
        public insertAsyncTask(FundsDao fundsDao) {
            Log.d(TAG, "insertAsyncTask: " + "constructor");
            mAsyncDao = fundsDao;
        }

        @Override
        protected Void doInBackground(Funds... fundsModels) {
            mAsyncDao.insert(fundsModels[0]);
            Log.d(TAG, "doInBackground: "  + "Thread Name: " + Thread.currentThread().getId());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.d(TAG, "FundsRepository: " + "thread: " + Thread.currentThread().getId());
        }
    }
}
