package com.bestpractices.learning.oppocasestudy.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bestpractices.learning.oppocasestudy.R;
import com.bestpractices.learning.oppocasestudy.adapters.FundsAdapter;
import com.bestpractices.learning.oppocasestudy.databinding.ActivityMainBinding;
import com.bestpractices.learning.oppocasestudy.models.Funds;
import com.bestpractices.learning.oppocasestudy.models.FundsModel;
import com.bestpractices.learning.oppocasestudy.repository.FundsRepository;
import com.bestpractices.learning.oppocasestudy.restapis.RestApiFactory;
import com.bestpractices.learning.oppocasestudy.utils.Util;
import com.bestpractices.learning.oppocasestudy.viewmodels.FundsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RestApiFactory.OnNotifyListener {
    public static final String TAG = "atul";
    private FundsAdapter adapter;
    private FundsViewModel fundsViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        init();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Util.showProgressDialog(this);

    }

    private void init() {

        RestApiFactory.getInstance().setListener(this);
        fundsViewModel = ViewModelProviders.of(this).get(FundsViewModel.class);
        fundsViewModel.init();
        generateDataList();
    }

    private void generateDataList() {
        adapter = new FundsAdapter(fundsViewModel.getFundsData().getValue(),this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);


    }


    @Override
    public void onNotify() {
        Util.hideProgressDialog();
        fundsViewModel.getFundsData().observe(this, new Observer<List<Funds>>() {
            @Override
            public void onChanged(List<Funds> funds) {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
