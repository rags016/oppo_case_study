package com.bestpractices.learning.oppocasestudy.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.bestpractices.learning.oppocasestudy.utils.Converters;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "funds_table")
@TypeConverters(Converters.class)
public class Funds {
    @PrimaryKey
    @NonNull
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


    public String getFundName() {
        return fundName;
    }


    public String getThreeYearReturns() {
        return threeYearReturns;
    }

    public String getNav() {
        return nav;
    }


    public String getFundCategory() {
        return fundCategory;
    }


    public String getRating() {
        return rating;
    }

    public String getUrl() {
        return url;
    }

}
