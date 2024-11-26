package com.example.racipeapp

import androidx.lifecycle.MutableLiveData

class RecipeRepository (private val recipeDao: RecipeDao){
    fun getAllRecipes(): MutableLiveData<List<Recipe?>> {
        return MutableLiveData(recipeDao.getAll())
    }

    suspend fun insertRecipe(recipe: Recipe){
        recipeDao.insert(recipe)
    }

    suspend fun updateRecipe(recipe: Recipe){
        recipeDao.update(recipe)
    }

    suspend fun deleteRecipe(recipe: Recipe){
        recipeDao.delete(recipe)
    }
}