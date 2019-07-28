package com.bestpractices.learning.oppocasestudy.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.bestpractices.learning.oppocasestudy.models.Funds;
import com.bestpractices.learning.oppocasestudy.models.FundsModel;

import java.util.List;

public class FundsRepository {
    private static final String  TAG= FundsRepository.class.getSimpleName();
    private FundsDao fundsDao;
    private static List<Funds> mAllFunds;
    private Funds funds;

    public FundsRepository(Application application) {
        FundsDatabase db = FundsDatabase.getDatabase(application);
        fundsDao =  db.fundsDao();
        setmAllFunds();
    }

    public void setmAllFunds(){
        new getFundsAsynctask(fundsDao).execute();
    }

    public List<Funds> getmAllFunds(){
        return mAllFunds;
    }
    public void insert(Funds fundsModel){
        new insertAsyncTask(fundsDao).execute(fundsModel);

    }

    public static class insertAsyncTask extends AsyncTask<Funds,Void,Void>{

        private FundsDao mAsyncDao;
        public insertAsyncTask(FundsDao fundsDao) {
            mAsyncDao = fundsDao;
        }

        @Override
        protected Void doInBackground(Funds... fundsModels) {
            mAsyncDao.insert(fundsModels[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }

    public static class getFundsAsynctask extends AsyncTask<Void,List<Funds>,List<Funds>>{

        FundsDao fundsDao;
        public getFundsAsynctask(FundsDao fundsDao) {
            this.fundsDao = fundsDao;
        }


        @Override
        protected List<Funds> doInBackground(Void... voids) {
            return fundsDao.getAllFundsFromDb();
        }

        @Override
        protected void onPostExecute(List<Funds> funds) {
            super.onPostExecute(funds);
            mAllFunds = funds;
        }
    }
}
