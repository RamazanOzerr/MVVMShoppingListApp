package com.example.shoppinglistapp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")  // set the table name
data class ShoppingItem(

    @ColumnInfo(name = "item_name")
    var name: String,

    @ColumnInfo(name = "item_amount")  // set the name of the column
    var amount: Int  // otherwise it will get the same name as the attribute
){
    @PrimaryKey(autoGenerate = true)  // room will handle the generation of unique id for each item
    var id: Int? = null  // set this as the PK of the table

}
