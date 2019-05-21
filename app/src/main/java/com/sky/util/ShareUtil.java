package com.sky.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author 施 凯 沅
 * @version 0.0.1
 */
public class ShareUtil {
    public static String shareData(Context context,String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String value = preferences.getString("key", "nonono");
        return value;
    }
    public static void shareSaveData(Context context,String account,String passowrd,String isChecked){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("account",account);
        editor.putString("password",passowrd);
        editor.putString("isChecked",isChecked);
        editor.apply();
    }


}
