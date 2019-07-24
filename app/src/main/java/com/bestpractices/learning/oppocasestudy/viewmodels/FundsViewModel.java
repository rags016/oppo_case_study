package com.bestpractices.learning.oppocasestudy.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bestpractices.learning.oppocasestudy.models.Funds;
import com.bestpractices.learning.oppocasestudy.repository.FundsRepository;
import com.bestpractices.learning.oppocasestudy.restapis.RestApiFactory;
import com.bestpractices.learning.oppocasestudy.utils.Util;

import java.util.List;

public class FundsViewModel extends AndroidViewModel{
    public static final String TAG ="atul";
    private MutableLiveData<List<Funds>> funds;
    private RestApiFactory call;
    private FundsRepository fundsRepository;
    private Context context;

    public FundsViewModel(@NonNull Application application) {
        super(application);
        context = application;
        call  = RestApiFactory.getInstance();
        call.setApplicationContext(application);
    }


    public void init() {
        Log.d(TAG, "init: ");
        if (funds != null) {
            return;
        }

        call.getFundsNetworkData();
        
        if(Util.getPreferences()){
            Log.d(TAG, "init: fromDb");
            funds = call.getFundFromDb();

        }else {
            Log.d(TAG, "init: fromNetwork");
            funds = call.getAllFunds();
        }


    }

    public LiveData<List<Funds>> getFundsData(){
        return funds;
    }




}
