package com.fort.saos.instantshot.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fort.saos.instantshot.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saldemachki on 26/10/2016.
 */

public class PhoneUserSharedPreferenciesManager {
    private static String KEY = "PHONE_USER_KEY";
    private SharedPreferences.Editor edit;

    public static boolean savePhoneUser(Context context, User user) {
        clearPhoneUser(context);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor =  sp.edit();
        Gson gson = new Gson();
        String jsonText = gson.toJson(user);
        prefsEditor.putString(KEY, jsonText);
        prefsEditor.commit();
        return true;
    }

    public static User getPhoneUser(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String jsonText = sp.getString(KEY, null);
        User phoneUser = gson.fromJson(jsonText, User.class);

        return phoneUser;
    }

    public static boolean clearPhoneUser(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = sp.edit();

        //prefsEditor.clear();
        prefsEditor.commit();
        return true;
    }
}
