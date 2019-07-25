package com.bestpractices.learning.oppocasestudy.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "funds_table")

public class FundsModel {

    private String status;
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    public Integer getId() {
        return id;
    }

    private String message;

    private List<Funds> data;

    public FundsModel(String status, Integer id, String message, List<Funds> data) {
        this.status = status;
        this.id = id;
        this.message = message;
        this.data = data;
    }

    public class Funds{
        private String fundCode;

        @SerializedName("name")
        private String fundName;

        @SerializedName("3yrreturns")
        private String threeYearReturns;

        private String nav;

        private String fundCategory;

        private String rating;

        @SerializedName("icon")
        private String url;

        public Funds(String fundCode, String fundName, String threeYearReturns, String nav, String fundCategory, String rating, String url) {
            this.fundCode = fundCode;
            this.fundName = fundName;
            this.threeYearReturns = threeYearReturns;
            this.nav = nav;
            this.fundCategory = fundCategory;
            this.rating = rating;
            this.url = url;
        }

        public String getFundCode() {
            return fundCode;
        }

        public void setFundCode(String fundCode) {
            this.fundCode = fundCode;
        }

        public String getFundName() {
            return fundName;
        }

        public void setFundName(String fundName) {
            this.fundName = fundName;
        }

        public String getThreeYearReturns() {
            return threeYearReturns;
        }

        public void setThreeYearReturns(String threeYearReturns) {
            this.threeYearReturns = threeYearReturns;
        }

        public String getNav() {
            return nav;
        }

        public void setNav(String nav) {
            this.nav = nav;
        }

        public String getFundCategory() {
            return fundCategory;
        }

        public void setFundCategory(String fundCategory) {
            this.fundCategory = fundCategory;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Funds> getData() {
        return data;
    }

    public void setData(ArrayList<Funds> data) {
        this.data = data;
    }
}
