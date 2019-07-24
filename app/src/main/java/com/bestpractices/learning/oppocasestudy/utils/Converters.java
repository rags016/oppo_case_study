package com.bestpractices.learning.oppocasestudy.utils;

import androidx.room.TypeConverter;

import com.bestpractices.learning.oppocasestudy.models.Funds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class Converters {
    @TypeConverter
    public static List<Funds> fromString(String value){
        Type listType = new TypeToken<List<Funds>>()
        {}.getType();
        return new Gson().fromJson(value,listType);
    }

    @TypeConverter
    public static String fromList(List<Funds> list){
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
