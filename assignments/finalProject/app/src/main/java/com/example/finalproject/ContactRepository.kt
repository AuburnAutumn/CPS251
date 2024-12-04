package com.example.finalproject

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ContactRepository (application: Application) {
    private val searchResults = MutableLiveData<List<Contact>>()
    private var contactDao: ContactDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allContacts: LiveData<List<Contact>>?

    //Mostly taken from the ProductRepository example

    init {
        val db: ContactRoomDatabase? = ContactRoomDatabase.getDatabase(application)
        contactDao = db?.ContactDao()
        allContacts = contactDao?.getAllContacts()
    }

    fun insertContact(newContact: Contact) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncInsert(newContact)
        }
    }

    private fun asyncInsert(contact: Contact) {
        contactDao?.insertContact(contact)
    }

    //Changed from deleting from name : String to id : Int
    fun deleteContact(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncDelete(id)
        }
    }
    private fun asyncDelete(id: Int) {
        contactDao?.deleteContact(id)
    }

    suspend fun findContact(name: String): MutableLiveData<List<Contact>> {
        val results = withContext(Dispatchers.IO) {
            asyncFind(name).await()
        }
        searchResults.value = results
        return searchResults
    }

    //And if I wanted to sort with SQL strings I would insert a function here that takes a String
    //as a parameter with an if statement. The if statement would look at the string, and if it was
    //"Asc" it would contactDao?.sortByAscending() and if it wasn't it would contactDao?.sortByDescending()
    //I would use the provided SQL for this.
    //It would return that as a list and send it to the MainViewModel to be displayed.

    //USES THE DEFERRED TO RETURN THE VALUES TO THE AWAIT OF THE FIND CONTACT
    private fun asyncFind(name: String): Deferred<List<Contact>?> =
        coroutineScope.async(Dispatchers.IO) {
            //NOTICE THIS IS A RETURN HERE BECAUSE IT IS A SELECT QUERY IT RETURNS A VALUE
            return@async contactDao?.findContact(name)
        }
}