package com.example.racipeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: RecipeRepository) : ViewModel() {
    val popularRecipes: LiveData<List<Recipe>> = MutableLiveData()

    init {
        viewModelScope.launch {
            val popularList = repository.getAllRecipes().value?.filter { it?.category?.contains("Popular") == true }
            (popularRecipes as MutableLiveData).postValue(popularList as List<Recipe>?)
        }
    }
}