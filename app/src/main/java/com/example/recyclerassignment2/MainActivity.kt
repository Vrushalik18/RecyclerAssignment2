package com.example.recyclerassignment2

import Item
import RecyclerAdapter
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val items = MutableList(10) { Item("Item ${it + 1}") }
    private val selectedItems = mutableListOf<String>()

    private var currentSwipedPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RecyclerAdapter(
            items,
            ::onItemLongClick,
            ::onItemClick,
            ::onSwipeLeft,
            ::onSwipeRight
        )
        recyclerView.adapter = adapter


        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (direction == ItemTouchHelper.LEFT) {
                    onSwipeLeft(position)
                } else if (direction == ItemTouchHelper.RIGHT) {
                    onSwipeRight(position)
                }
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun onItemClick(position: Int) {
        val item = items[position]
        item.isSelected = !item.isSelected
        updateSelectedItems()
    }

    private fun onItemLongClick(position: Int) {

        items[position].isSelected = !items[position].isSelected
        updateSelectedItems()
    }

    private fun updateSelectedItems() {
        selectedItems.clear()
        items.filter { it.isSelected }.forEach { selectedItems.add(it.name) }
        findViewById<TextView>(R.id.selectedItemsText).text = selectedItems.joinToString(", ")
    }

    private fun onSwipeLeft(position: Int) {

        if (currentSwipedPosition != position) {

            currentSwipedPosition = position
            Toast.makeText(this, "Left swipe on ${items[position].name}: Show ButA, ButB", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onSwipeRight(position: Int) {

        if (currentSwipedPosition != position) {

            currentSwipedPosition = position
            Toast.makeText(this, "Right swipe on ${items[position].name}: Show ButC, ButD", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showToast(buttonName: String) {
        Toast.makeText(this, "Button named $buttonName is clicked", Toast.LENGTH_SHORT).show()
    }

    fun onLeftButtonClick(view: View) {
        when (view.id) {
            R.id.butA -> showToast("ButA")
            R.id.butB -> showToast("ButB")
        }
    }

    fun onRightButtonClick(view: View) {
        when (view.id) {
            R.id.butC -> showToast("ButC")
            R.id.butD -> showToast("ButD")
        }
    }
}
