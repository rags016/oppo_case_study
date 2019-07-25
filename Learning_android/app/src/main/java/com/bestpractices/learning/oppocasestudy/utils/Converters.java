package com.bestpractices.learning.oppocasestudy.utils;


import androidx.room.TypeConverter;

import com.bestpractices.learning.oppocasestudy.models.FundsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Converters {
    static Gson gson = new Gson();
    @TypeConverter
    public static List<FundsModel.Funds> fromString(String data) {


        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<FundsModel.Funds>>() {}.getType();

        return gson.fromJson(data, listType);
    }
    @TypeConverter
    public static String someObjectListToString(List<FundsModel.Funds> someObjects) {
        return gson.toJson(someObjects);
    }
}

