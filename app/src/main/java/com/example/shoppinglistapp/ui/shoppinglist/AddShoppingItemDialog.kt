package com.example.shoppinglistapp.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.data.db.entities.ShoppingItem

class AddShoppingItemDialog (context: Context, var addDialogListener: AddDialogListener): AppCompatDialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_shopping_item)

        val tvAdd: TextView? = findViewById(R.id.tvAdd)
        val etName: EditText ?= findViewById(R.id.etName)
        val etAmount: EditText ?= findViewById(R.id.etAmount)
        val tvCancel: TextView ?= findViewById(R.id.tvCancel)

        tvAdd?.setOnClickListener{
            val name = etName?.text.toString()
            val amount = etAmount?.text.toString()
            if(name.isNullOrEmpty()) {
                Toast.makeText(context, "Please enter a name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(amount.isNullOrEmpty()){
                Toast.makeText(context, "Please enter an amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tvCancel?.setOnClickListener {
            cancel()
        }
    }
}