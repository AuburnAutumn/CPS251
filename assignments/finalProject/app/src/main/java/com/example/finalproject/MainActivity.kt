package com.example.finalproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter: ContactListAdapter? = null
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listenerSetup()
        observerSetup()
        recyclerSetup()
    }

    private fun clearFields() {
        binding.contactName.setText("")
        binding.contactNumber.setText("")
        //Returns focus to nameField
        binding.contactName.requestFocus()
    }

    private fun listenerSetup() {

        //Add button
        binding.addButton.setOnClickListener {
            val name = binding.contactName.text.toString()
            val number = binding.contactNumber.text.toString()

            if (name != "" && number != "") {
                val contact = Contact(name, number)
                viewModel.insertContact(contact)
                clearFields()
            } else {
                clearFields()
                showToast("You must enter a name and phone number")
            }
        }

        binding.findButton.setOnClickListener {
            val name = binding.contactName.text.toString()
            CoroutineScope(Dispatchers.Main).launch {
                if (name != "") {
                val results = viewModel.findContact(binding.contactName.text.toString())
                if (results.isNullOrEmpty()) {
                    showToast("There are no contacts that match your search")

                }
                    clearFields()
                    } else {
                    showToast("You must enter a search criteria in the name field")
                    clearFields()
                }
            }
        }

        //Buttons call the same method but pass in different Strings to tell it
        //what to sort by
        binding.ascendingButton.setOnClickListener {
            viewModel.sortBy("asc")
        }

        binding.descendingButton.setOnClickListener {
            viewModel.sortBy("desc")
        }

    }

    private fun observerSetup() {
        viewModel.currentDisplayedList.observe(this) { contacts ->
            contacts?.let {
                adapter?.setContactList(it)
            }
        }
    }

    //CardView setup
    private fun recyclerSetup() {
        adapter = ContactListAdapter(viewModel)
        binding.contactRecycler.layoutManager = LinearLayoutManager(this)
        binding.contactRecycler.adapter = adapter
    }

    //Provided toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}