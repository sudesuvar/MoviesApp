package com.example.moviesapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.Domain.FilmItemModel
import com.example.moviesapp.Repository.MainRepository

class MainViewModel:ViewModel() {

    //MainViewModel, UI (Activity/Fragment) ile Repository arasında bir köprü görevi görür.
    private val repository= MainRepository()

    fun loadUpcoming(): LiveData<MutableList<FilmItemModel>> {
        return repository.loadUpcoming()
    }

    fun loadUItems(): LiveData<MutableList<FilmItemModel>> {
        return repository.loadItems()
    }

}