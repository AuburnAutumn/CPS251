package com.example.finalproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contacts")
class Contact {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contactID")
    var id: Int = 0

    @ColumnInfo(name = "contactName")
    var contactName: String? = null

    @ColumnInfo(name = "contactNumber")
    var contactNumber: String? = null


    constructor() {}

    constructor(contactName : String, contactNumber: String) {
        this.contactName = contactName
        this.contactNumber = contactNumber
    }
}
