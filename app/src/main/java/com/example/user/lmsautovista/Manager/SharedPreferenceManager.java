package com.example.user.lmsautovista.Manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.user.lmsautovista.Utils.Constants;

/*
  Created by Shalu Dhochak on 2/17/2018.
 */

public class SharedPreferenceManager {

    private static SharedPreferenceManager mSharedPreferenceManager ;
    private SharedPreferences mSharedPreferences;

    private SharedPreferenceManager(Context context)
    {
        if(mSharedPreferences == null)
            mSharedPreferences  = context.getSharedPreferences(Constants.USERPREFERENCE, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferenceManager getInstance(Context context)
    {
        if(mSharedPreferenceManager == null)
            mSharedPreferenceManager = new SharedPreferenceManager(context);
        return mSharedPreferenceManager;
    }

    public void savePreference(String key,String value)
    {
        SharedPreferences.Editor preferencesEditor = mSharedPreferences.edit();
        preferencesEditor.putString(key,value);
        preferencesEditor.apply();
    }

    public void saveIntegerPreference(String key, Integer value)
    {
        SharedPreferences.Editor preferencesEditor = mSharedPreferences.edit();
        preferencesEditor.putInt(key,value);
        preferencesEditor.apply();
    }

    public void saveBooleanPreference(String key,Boolean value)
    {
        SharedPreferences.Editor preferencesEditor = mSharedPreferences.edit();
        preferencesEditor.putBoolean(key,value);
        preferencesEditor.apply();
    }

    public String getPreference(String key,String defaultValue)
    {
        return mSharedPreferences.getString(key,defaultValue);
    }

    public Boolean getBooleanPreference(String key,boolean defaultValue)
    {
        return mSharedPreferences.getBoolean(key,defaultValue);
    }

    public Integer getIntegerPreference(String key,int defaultValue)
    {
        return mSharedPreferences.getInt(key,defaultValue);
    }
}
