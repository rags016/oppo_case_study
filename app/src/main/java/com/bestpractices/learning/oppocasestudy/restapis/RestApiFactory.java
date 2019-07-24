package com.bestpractices.learning.oppocasestudy.restapis;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bestpractices.learning.oppocasestudy.models.Funds;
import com.bestpractices.learning.oppocasestudy.models.FundsModel;
import com.bestpractices.learning.oppocasestudy.repository.FundsRepository;
import com.bestpractices.learning.oppocasestudy.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestApiFactory {
    private static final String TAG = "atul";
    private FundsModel fundsData;
    private ArrayList<Funds> list = new ArrayList<>();
    private OnNotifyListener listener;
    private static RestApiFactory instance = null;

    private FundsRepository repository;

    private Context context;

    public void setListener(OnNotifyListener listener) {
        this.listener = listener;
    }

    private RestApiFactory() {
    }

    public static RestApiFactory getInstance(){
        if(instance==null){
            instance = new RestApiFactory();
        }
        return instance;
    }

    public void setApplicationContext(Application application){
        repository = new FundsRepository(application);
        context = application;
        Util.setContext(context);
    }

    public void getFundsNetworkData(){
        RestApi networkCallService = RestApiBuilder.getInstance().buildConnection();

        Call<FundsModel> call = networkCallService.getAllFunds();
        call.enqueue(new Callback<FundsModel>() {
            @Override
            public void onResponse(Call<FundsModel> call, Response<FundsModel> response) {
                Log.d(TAG, "onResponse: ");
                if(response.isSuccessful()){
                    setNetworkData(response.body());
                    listener.onNotify();
                }else{
                    Log.d(TAG, "onResponse: " + " Not success" );
                }


            }

            @Override
            public void onFailure(Call<FundsModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void setNetworkData(FundsModel fundsModel){
        fundsData = fundsModel;
        Funds funds = fundsModel.getData().get(0);
        Log.d(TAG, "setData: " +fundsModel);
        list.clear();
        for(int i=0;i< fundsModel.getData().size();i++){
            list.add(fundsModel.getData().get(i));
        }
        if(Util.getPreferences()== false){
            repository.insert(funds);
            Util.setPreferences(true);
        }


    }


    public MutableLiveData<List<Funds>> getAllFunds(){

        MutableLiveData<List<Funds>> dataSet = new MutableLiveData<>();
        Log.d(TAG, "getAllFunds: " + list.size());
        dataSet.setValue(list);

        return dataSet;
    }

    public MutableLiveData<List<Funds>> getFundFromDb(){
        LiveData<List<Funds>> dbFundsModel = repository.getmAllFunds();
        Log.d(TAG, "getFundFromDb: " + dbFundsModel.getValue());
//        List<Funds> dbFundsList = dbFundsModel.getValue().get(0).getData();
        MutableLiveData<List<Funds>> dataSet = new MutableLiveData<>();
        dataSet.setValue(list);

        return dataSet;
    }


    public interface OnNotifyListener{

        void onNotify();
    }
}
