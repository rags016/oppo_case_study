package com.bestpractices.learning.oppocasestudy.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bestpractices.learning.oppocasestudy.models.FundsModel;
import com.bestpractices.learning.oppocasestudy.repository.BuildConnection;
import com.bestpractices.learning.oppocasestudy.repository.NetworkCall;
import com.bestpractices.learning.oppocasestudy.repository.NetworkCallService;
import com.bestpractices.learning.oppocasestudy.ui.MainActivity;

import java.util.List;

public class FundsViewModel extends AndroidViewModel{
    public static final String TAG ="atul";
    private MutableLiveData<List<FundsModel.Funds>> funds;
    private MutableLiveData<Boolean> mIsUpdating  = new MutableLiveData<>();
    private NetworkCall call;
    private Context context;

    public FundsViewModel(@NonNull Application application) {
        super(application);
        context = application;
        call  = NetworkCall.getInstance();
    }


    public void init() {
        Log.d(TAG, "init: ");
        if (funds != null) {
            return;
        }
        mIsUpdating.setValue(true);

        call.getFundsNetworkData();
        if(MainActivity.first_launch){

        }else {
//            funds = call.getDataFromDb();
        }
        funds = call.getAllFunds();

    }

//    public FundsModel getFundsData() {
//        return funds;
//    }

    public LiveData<List<FundsModel.Funds>> getFundsData(){
        if(funds!=null){
            mIsUpdating.setValue(false);
        }
        return funds;
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }


}
