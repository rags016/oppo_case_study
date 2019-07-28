package com.bestpractices.learning.oppocasestudy.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Util {
    private static final String TAG = Util.class.getSimpleName();
    private static final String MY_PREFERENCE = "my_preference";
    private static final String HEADER = "Fetching Funds";
    private static SharedPreferences pref;
    private static Context mContext;
    private static ProgressDialog loading;

    public static void setPreferences(boolean value) {
        pref = mContext.getSharedPreferences(MY_PREFERENCE, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.putBoolean("FROM_DB", value);
        editor.commit();
    }

    public static boolean getPreferences() {
        if (pref == null) {
            return false;
        }
        pref = mContext.getSharedPreferences(MY_PREFERENCE,
                Context.MODE_PRIVATE);
        boolean fromDb = pref.getBoolean("FROM_DB", false);
        return fromDb;
    }

    public static void showProgressDialog(Context context) {
        loading = new ProgressDialog(context);
        loading.setCancelable(true);
        loading.setMessage(HEADER);
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();

    }

    public static void hideProgressDialog() {
        if (isShowing())
            loading.dismiss();
    }

    public static void setContext(Context context) {
        mContext = context;
    }

    public static boolean isShowing(){
        return loading.isShowing();
    }
}
