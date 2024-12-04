package com.example.finalproject

    import android.content.Context
    import androidx.room.Database
    import androidx.room.Room
    import androidx.room.RoomDatabase

//Sets up contact_database
    @Database(entities = [(Contact::class)], version = 1)
    abstract class ContactRoomDatabase: RoomDatabase() {
        abstract fun ContactDao(): ContactDao
        companion object {
            private var INSTANCE: ContactRoomDatabase? = null
            internal fun getDatabase(context: Context): ContactRoomDatabase? {
                if (INSTANCE == null) {
                    synchronized(ContactRoomDatabase::class.java) {
                        if (INSTANCE == null) {
                            INSTANCE =
                                Room.databaseBuilder(
                                    context.applicationContext,
                                    ContactRoomDatabase::class.java,
                                    "contact_database").build()
                        }
                    }
                }
                return INSTANCE
            }
        }

    }