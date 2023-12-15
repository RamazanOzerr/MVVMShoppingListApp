package com.example.shoppinglistapp.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.data.db.repository.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel (
    private val repository: ShoppingRepository
) : ViewModel() {

    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    // we do not need coroutines since this is just a read operation
    fun getAllShoppingItems() = repository.getAllShoppingItem()
}