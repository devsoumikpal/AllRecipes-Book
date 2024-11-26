package com.example.racipeapp

import android.content.Context

object FavoriteManager {
    private const val PREF_NAME="app_preferences"
    private const val KEY_FAVORITES="favorite_ids"

    fun  addFavorite(context: Context, id:String){
        val prefs=context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val favorites= prefs.getStringSet(KEY_FAVORITES, HashSet())?.toMutableSet()
        favorites?.add(id)
        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply()
    }

    fun removeFavorite(context: Context, id: String){
        val prefs=context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val favorites= prefs.getStringSet(KEY_FAVORITES, HashSet())?.toMutableSet()
        favorites?.remove(id)
        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply()
    }

    fun isFavorite(context: Context, id: String): Boolean {
        val prefs=context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val favorites= prefs.getStringSet(KEY_FAVORITES, HashSet())
        return favorites?.contains(id)==true
    }

    fun getFavorites(context: Context): Set<String>{
        val prefs=context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getStringSet(KEY_FAVORITES, HashSet())?: emptySet()
    }
}