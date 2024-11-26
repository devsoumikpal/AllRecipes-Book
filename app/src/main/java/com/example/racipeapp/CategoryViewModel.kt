package com.example.racipeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: RecipeRepository) : ViewModel() {
    val recipes: LiveData<List<Recipe?>> = repository.getAllRecipes()

    fun filterRecipesByCategory(category: String?): LiveData<List<Recipe>> {
        val filteredRecipes = MutableLiveData<List<Recipe>>()
        category?.let {
            viewModelScope.launch {
                val list = recipes.value?.filter { it?.category?.contains(category) == true }
                filteredRecipes.postValue((list ?: emptyList()) as List<Recipe>?)
            }
        }
        return filteredRecipes
    }
}