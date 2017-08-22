package com.headonelab.hacknbreaksnapchat.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    private SharedPreferences mSharedPreferences;

    public SharedPreferencesHelper(Context context){
        mSharedPreferences = context.getSharedPreferences("HacknbreakSnapchat", Context.MODE_PRIVATE);
    }

    public void setPreferences(String key, String value){
        mSharedPreferences.edit()
                .putString(key, value)
                .commit();
    }

    public String getPreferences(String key, String defValue){
        return mSharedPreferences.getString(key, defValue);
    }

}