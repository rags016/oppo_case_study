package com.bestpractices.learning.oppocasestudy.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Util {
    private static final String TAG = "atul";
    private static final String MY_PREFERENCE = "my_preference";
    private static final String HEADER = "Fetching Funds";
    private static SharedPreferences pref;
    private static Context mContext;
    private static ProgressDialog loading;

    public static void setPreferences(boolean value){
        pref = mContext.getSharedPreferences(MY_PREFERENCE, mContext.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.putBoolean("FROM_DB",value);
        editor.commit();

        Log.d(TAG, "setPreferences: " + pref);

    }

    public static boolean getPreferences(){
        if(pref==null){
            return false;
        }
        pref = mContext.getSharedPreferences(MY_PREFERENCE,
                Context.MODE_PRIVATE);
        Log.d(TAG, "getPreferences: " + pref);

      boolean fromDb =  pref.getBoolean("FROM_DB",false);
      return fromDb;
    }

    public static void showProgressDialog(Context context){
        Log.d(TAG, "showProgressDialog: ");
        loading = new ProgressDialog(context);
        loading.setCancelable(true);
        loading.setMessage(HEADER);
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();

    }

    public static void hideProgressDialog(){
        Log.d(TAG, "hideProgressDialog: ");
        loading.dismiss();
    }
    public static void setContext(Context context){
        mContext = context;
    }
}
