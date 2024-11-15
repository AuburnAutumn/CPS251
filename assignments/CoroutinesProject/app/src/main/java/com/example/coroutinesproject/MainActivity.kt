package com.example.coroutinesproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinesproject.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter(viewModel)
        binding.recyclerView.adapter = adapter

        binding.button.setOnClickListener {
            if (binding.nameInput.text.isBlank()) {
                //I didn't want blanks popping up

            } else {

                //Grabs the name
                val name = binding.nameInput.text.toString()

                //Launches coroutine
                CoroutineScope(Dispatchers.Main).launch {
                    var cardString = ""
                    //Random number of seconds generated as an int to keep the seconds rounded
                    val delaySeconds = (1..10).random()
                    //Delay goes
                    delay(delaySeconds.toLong() * 1000)
                    //String built
                    cardString = "The name is $name and the delay was ${delaySeconds}000 milliseconds"
                    //Sends String to array in ViewModel
                    viewModel.updateNameList(cardString)
                    //Updates UI
                    adapter?.notifyDataSetChanged()
                }
            }
            //For convenience
            binding.nameInput.text.clear()
        }
    }

}