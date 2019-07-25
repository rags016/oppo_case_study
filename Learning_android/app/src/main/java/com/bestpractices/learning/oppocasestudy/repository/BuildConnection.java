package com.bestpractices.learning.oppocasestudy.repository;

import com.bestpractices.learning.oppocasestudy.models.FundsModel;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildConnection {
    public static final String  BASE_URL = "https://5d3151ab45e2b00014d93c81.mockapi.io/api/v1/";
    private Retrofit retrofit;
    private static BuildConnection instance = null;
    private NetworkCallService service;
    private ArrayList<FundsModel> fundsModels;
    private BuildConnection() {
    }

    public static BuildConnection getInstance(){
        if(instance== null){
            instance = new BuildConnection();
        }
        return instance;
    }

    public Retrofit buildConnection(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
