package com.example.recyclerviewwithintent


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.example.recyclerviewwithintent.databinding.CardViewBinding

class RecyclerAdapter(private val data: Data, private val viewModel: MainViewModel) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            // Set the click listener on the itemView
            itemView.setOnClickListener { v ->
                val position = adapterPosition

                if (position != RecyclerView.NO_POSITION) { // Check if position is valid
                    //Used toast quite a bit for testing so it's commented out and not deleted
                   //Snackbar.make(v, "Click detected on item $position", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null)
                        //.show()

                    val intent = Intent(itemView.context, SecondActivity::class.java)

                    //Passing in values
                    intent.putExtra("title", viewModel.getTitle(position))
                    intent.putExtra("detail", viewModel.getDetails(position))
                    intent.putExtra("imageResId", viewModel.getImage(position))

                    startActivity(itemView.context, intent, null)
                }
            }
        }

        fun bind(title: String, detail: String, imageResId: Int) {
            binding.itemImage.setImageResource(imageResId)
            binding.itemTitle.text = title
            binding.itemDetail.text = detail
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel.getTitle(position), viewModel.getDetails(position), viewModel.getImage(position))
    }

    override fun getItemCount(): Int = data.titles.size
}