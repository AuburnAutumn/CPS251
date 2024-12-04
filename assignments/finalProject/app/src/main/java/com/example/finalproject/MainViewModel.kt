package com.example.finalproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: ContactRepository = ContactRepository(application)
    private val allContacts: LiveData<List<Contact>>? = repository.allContacts
    private var searchResults: MutableLiveData<List<Contact>> = MutableLiveData()
    private var displayedList: MutableLiveData<List<Contact>> = MutableLiveData()

    val currentDisplayedList: LiveData<List<Contact>> get() = displayedList

    init {
        //Grabs all contacts and puts them into displayedList which handles most of the app
        //Sets displayedList to show allContacts by default
        allContacts?.observeForever { contacts ->
            displayedList.postValue(contacts)
        }
    }

    //Adds new contact and lets displayedList know
    fun insertContact(contact: Contact) {
        repository.insertContact(contact)
        refreshDisplayedList()
    }

    //Searches contact datatable for a substring
    suspend fun findContact(name: String): List<Contact>? {
        val results = repository.findContact(name).value
        //If statement to keep it from displaying an empty cardView if there's no results
        if (results.isNullOrEmpty()) {
            return results
        } else {
            searchResults.postValue(results)
            displayedList.postValue(results)
            return results
        }
    }

    //Deletes contact and lets displayedList know
    fun deleteContact(id: Int) {
        repository.deleteContact(id)
        refreshDisplayedList()
    }

    //Updates displayedList on changes to the list of all contacts
    private fun refreshDisplayedList() {
        displayedList.postValue(allContacts?.value)
    }

    //Allows anything to be dropped into the displayedList, such as search results
    private fun updateCurrentList(newList: List<Contact>?) {
        displayedList.postValue(newList)
    }

    //Sorting
    //Allows displayedList to be sorted, regardless of what it is currently displaying
    //This allows search results to be sorted
    fun sortBy(ascDesc : String){
        if (ascDesc == "asc"){
            val sortedArray = displayedList.value?.sortedBy { it.contactName }
            updateCurrentList(sortedArray)
        } else {
            val sortedArray = displayedList.value?.sortedByDescending { it.contactName }
            updateCurrentList(sortedArray)
        }
    }
}
