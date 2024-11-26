package com.example.racipeapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], exportSchema = false, version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getDao():RecipeDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "recipe.db" // Ensure this matches your database name
                ).build()
                instance = newInstance
                newInstance
            }
        }
    }
}