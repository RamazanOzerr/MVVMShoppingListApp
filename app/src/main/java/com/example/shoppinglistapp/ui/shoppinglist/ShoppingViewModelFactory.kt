package com.example.shoppinglistapp.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglistapp.data.db.repository.ShoppingRepository

@Suppress("UNCHECKED_CAST")
class ShoppingViewModelFactory(
    private val repository: ShoppingRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T
    }
}