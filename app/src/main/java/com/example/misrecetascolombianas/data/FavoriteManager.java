package com.example.misrecetascolombianas.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FavoriteManager {
    private static final String PREFS_NAME       = "misrecetas_prefs";
    private static final String KEY_FAVORITES    = "favorites";


    public static Set<String> getFavorites(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return new HashSet<>(prefs.getStringSet(KEY_FAVORITES, Collections.emptySet()));
    }


    public static void addFavorite(Context ctx, String recipeName) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> set = new HashSet<>(prefs.getStringSet(KEY_FAVORITES, Collections.emptySet()));
        set.add(recipeName);
        prefs.edit().putStringSet(KEY_FAVORITES, set).apply();
    }


    public static void removeFavorite(Context ctx, String recipeName) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> set = new HashSet<>(prefs.getStringSet(KEY_FAVORITES, Collections.emptySet()));
        set.remove(recipeName);
        prefs.edit().putStringSet(KEY_FAVORITES, set).apply();
    }

}
