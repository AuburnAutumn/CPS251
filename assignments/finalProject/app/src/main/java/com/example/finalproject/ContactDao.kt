package com.example.finalproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface ContactDao {
    @Insert
    fun insertContact(contact: Contact)

    //Changed to search for a substring instead of the whole
    @Query("SELECT * FROM contacts WHERE contactName LIKE '%' || :name || '%'")
    fun findContact(name: String): List<Contact>

    //Deletes from id now, as an Int
    @Query("DELETE FROM contacts WHERE contactID = :id")
    fun deleteContact(id: Int)

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contact>>

}