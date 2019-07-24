package com.bestpractices.learning.oppocasestudy.models;

import androidx.annotation.NonNull;


import java.util.List;

public class FundsModel {

    private int status;

    private String message;

    private List<Funds> data;

    public FundsModel(int status,String message, List<Funds> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }


    public String getMessage() {
        return message;
    }


    public List<Funds> getData() {
        return data;
    }

}
