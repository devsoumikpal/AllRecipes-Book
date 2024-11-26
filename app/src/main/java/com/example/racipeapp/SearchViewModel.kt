package com.example.racipeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class SearchViewModel(private val repository: RecipeRepository) : ViewModel() {
    val allRecipes: LiveData<List<Recipe?>> = repository.getAllRecipes()

    fun filterRecipesByTitle(query: String): List<Recipe?> {
        return allRecipes.value?.filter { it?.tittle?.contains(query, ignoreCase = true) == true } ?: emptyList()
    }
}