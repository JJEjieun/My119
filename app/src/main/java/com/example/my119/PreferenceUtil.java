package com.example.my119;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {
    public static void setPreferences(Context context, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences("pref", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPreferences(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences("pref", context.MODE_PRIVATE);
        return pref.getString(key, "");
    }
}
