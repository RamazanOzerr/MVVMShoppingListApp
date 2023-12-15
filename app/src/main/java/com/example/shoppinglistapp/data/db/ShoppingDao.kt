package com.example.shoppinglistapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppinglistapp.data.db.entities.ShoppingItem

@Dao
interface ShoppingDao {

    // it basically replaces the item if already exists in the table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)  // doesnt allow to do db operations on the main thread
                                            // so we will use coroutines

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllItems() : LiveData<List<ShoppingItem>>

}