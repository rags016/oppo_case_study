package com.bestpractices.learning.oppocasestudy.repository;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bestpractices.learning.oppocasestudy.models.FundsModel;
import com.bestpractices.learning.oppocasestudy.room.FundRepository;
import com.bestpractices.learning.oppocasestudy.room.FundsDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bestpractices.learning.oppocasestudy.ui.MainActivity.first_launch;


public class NetworkCall {
    public static final String TAG = "atul";
    FundsModel fundsData;
    ArrayList<FundsModel.Funds> list = new ArrayList<>();
    private OnNotifyListener listener;
    private static NetworkCall instance = null;

    private FundRepository repository;
    private LiveData<FundsModel> allFunds;

    public void setListener(OnNotifyListener listener) {
        this.listener = listener;
    }

    private NetworkCall() {
    }

    public static NetworkCall getInstance(){
        if(instance==null){
            instance = new NetworkCall();
        }
        return instance;
    }

    public void getFundsNetworkData(){
        NetworkCallService networkCallService = BuildConnection.getInstance().buildConnection().create(NetworkCallService.class);

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

        Log.d(TAG, "setData: " +fundsModel);
        for(int i=0;i< fundsModel.getData().size();i++){
            list.add(fundsModel.getData().get(i));
        }

        if (first_launch) {
            insert(fundsData);
        }
        first_launch =false;
        fundsData = fundsModel;

    }


    public MutableLiveData<List<FundsModel.Funds>> getAllFunds(){

        MutableLiveData<List<FundsModel.Funds>> dataSet = new MutableLiveData<>();
        Log.d(TAG, "getAllFunds: " + list.size());
        dataSet.setValue(list);

        return dataSet;
    }


    public interface OnNotifyListener{

        public void onNotify();
    }

    public void insert(FundsModel funds) {
//        repository.insert(funds);
    }

    public MutableLiveData<List<FundsModel.Funds>> getDataFromDb(){
        LiveData<FundsModel> fundsModelLiveData = repository.getAllNotes();
        List<FundsModel.Funds> dbList = new ArrayList<>();
        if(fundsModelLiveData!= null){
            for(int i=0;i< fundsModelLiveData.getValue().getData().size();i++){
                dbList.add(fundsModelLiveData.getValue().getData().get(i));
            }
        }
        MutableLiveData<List<FundsModel.Funds>> dbData = new MutableLiveData<>();
        dbData.setValue(dbList);
        return dbData;
    }
}
