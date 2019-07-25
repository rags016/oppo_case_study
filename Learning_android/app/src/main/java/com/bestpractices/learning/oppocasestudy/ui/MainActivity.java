package com.bestpractices.learning.oppocasestudy.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bestpractices.learning.oppocasestudy.R;
import com.bestpractices.learning.oppocasestudy.adapters.FundsAdapter;
import com.bestpractices.learning.oppocasestudy.databinding.ActivityMainBinding;
import com.bestpractices.learning.oppocasestudy.models.FundsModel;
import com.bestpractices.learning.oppocasestudy.repository.NetworkCall;
import com.bestpractices.learning.oppocasestudy.viewmodels.FundsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkCall.OnNotifyListener {
    public static final String TAG = "atul";
    private FundsAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressDoalog;
    private FundsViewModel fundsViewModel;
    private ActivityMainBinding binding;
    public static boolean first_launch = true;
    private NetworkCall networkCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        init();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    private void init() {
        NetworkCall.getInstance().setListener(this);
        fundsViewModel = ViewModelProviders.of(this).get(FundsViewModel.class);
        fundsViewModel.init();

        fundsViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    showProgressBar();
                }else{
                    hideProgressBar();
                }
            }
        });

        generateDataList();
    }

    private void generateDataList() {
        adapter = new FundsAdapter(fundsViewModel.getFundsData().getValue(),this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);

    }


    public void showProgressBar(){
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar(){
        binding.progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onNotify() {
        fundsViewModel.getFundsData().observe(this, new Observer<List<FundsModel.Funds>>() {
            @Override
            public void onChanged(List<FundsModel.Funds> funds) {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
