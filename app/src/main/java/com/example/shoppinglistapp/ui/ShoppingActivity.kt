package com.example.shoppinglistapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.data.db.ShoppingDatabase
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.data.db.repository.ShoppingRepository
import com.example.shoppinglistapp.other.ShoppingItemAdapter
import com.example.shoppinglistapp.ui.shoppinglist.AddDialogListener
import com.example.shoppinglistapp.ui.shoppinglist.AddShoppingItemDialog
import com.example.shoppinglistapp.ui.shoppinglist.ShoppingViewModel
import com.example.shoppinglistapp.ui.shoppinglist.ShoppingViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        val rvShoppingItems: RecyclerView = findViewById(R.id.rvShoppingItems)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener{
            AddShoppingItemDialog(
                this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }
}