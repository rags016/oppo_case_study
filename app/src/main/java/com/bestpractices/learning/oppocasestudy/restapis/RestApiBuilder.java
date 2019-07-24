package com.bestpractices.learning.oppocasestudy.restapis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApiBuilder {
    public static final String  BASE_URL = "https://5d3151ab45e2b00014d93c81.mockapi.io/api/v1/";
    private static RestApiBuilder instance = null;
    private RestApiBuilder() {
    }

    public static RestApiBuilder getInstance(){
        if(instance== null){
            instance = new RestApiBuilder();
        }
        return instance;
    }

    public RestApi buildConnection(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RestApi.class);
    }
}
