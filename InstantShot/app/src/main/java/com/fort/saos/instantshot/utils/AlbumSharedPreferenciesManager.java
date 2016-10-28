package com.fort.saos.instantshot.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fort.saos.instantshot.model.Album;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saldemachki on 24/10/2016.
 */

public class AlbumSharedPreferenciesManager {
    private static String KEY = "ALBUM_KEY";
    private SharedPreferences.Editor edit;

    public static boolean saveAlbum(Context context, Album album) {
        List<Album> albums = getAlbums(context);
        clearAlbums(context);
        albums.add(album);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor =  sp.edit();
        Gson gson = new Gson();
        String jsonText = gson.toJson(albums);
        prefsEditor.putString(KEY, jsonText);
        prefsEditor.commit();
        return true;
    }

    public static List<Album> getAlbums(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        List<Album> abonnements = new ArrayList<>();
        Gson gson = new Gson();
        String jsonText = sp.getString(KEY, null);
        Album[] abo = gson.fromJson(jsonText, Album[].class);

        if (abo != null) {
            for (int i = 0; i < abo.length; i++) {
                abonnements.add(abo[i]);
            }
        }

        return abonnements;
    }

    public static boolean clearAlbums(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = sp.edit();

        prefsEditor.clear();
        prefsEditor.commit();
        return true;
    }
}
