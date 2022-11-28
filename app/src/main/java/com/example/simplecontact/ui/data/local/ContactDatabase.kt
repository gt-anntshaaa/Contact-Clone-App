package com.example.simplecontact.ui.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simplecontact.ui.data.local.dao.ContactDao
import com.example.simplecontact.ui.data.local.entity.ContactEntity

@Database(entities = arrayOf(ContactEntity::class), version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun getContactDao(): ContactDao


    // build database
    companion object{
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "Contact_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}