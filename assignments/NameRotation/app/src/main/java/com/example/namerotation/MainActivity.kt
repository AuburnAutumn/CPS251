package com.example.namerotation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.namerotation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //To update text after rotation
        binding.listTV.text=viewModel.getOutput()

        binding.button.setOnClickListener {
            if(binding.editTextText.text.isBlank()){
                /*Checks for no name and sends the String to to the MainViewModel to update
                the current output String. This allows the textView to remember it last had an
                error after rotation as opposed to resetting to the list of names.*/
                viewModel.noNameError(getString(R.string.errorMessage))
                binding.listTV.text = viewModel.getOutput()
            } else {
                //Adds name to list if not blank
                viewModel.addName(binding.editTextText.text.toString())
                //Gets the result
                binding.listTV.text = viewModel.getOutput()
            }
            //For convenience
            binding.editTextText.text.clear()

        }
    }

}