package com.bestpractices.learning.oppocasestudy.repository;

import com.bestpractices.learning.oppocasestudy.models.FundsModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkCallService {
    @GET("funds")
    Call<FundsModel> getAllFunds();
}
