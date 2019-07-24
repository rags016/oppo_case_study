package com.bestpractices.learning.oppocasestudy.restapis;

import com.bestpractices.learning.oppocasestudy.models.FundsModel;


import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {
    @GET("funds")
    Call<FundsModel> getAllFunds();
}
