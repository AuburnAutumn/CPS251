package com.example.coroutinesproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.example.coroutinesproject.databinding.CardViewBinding

class RecyclerAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            // Set the click listener on the itemView
            /*Used for testing that the array is working correctly and the coroutine was adding the
            name at the right time (Like it didn't add it and then wait)*/
            itemView.setOnClickListener { v ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) { // Check if position is valid
                    Snackbar.make(v, "Click detected on item $position", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
            }
        }

        fun bind(displayedText: String) {
            binding.textDisplay.text = displayedText
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel.list[position])
    }


    override fun getItemCount(): Int = viewModel.getNameCount()
}