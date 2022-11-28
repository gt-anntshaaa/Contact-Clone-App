package com.example.simplecontact.ui.data.local

import android.app.Application
import com.example.simplecontact.ui.data.repository.ContactRepo

class ContactApplication : Application() {
    val database by lazy{ContactDatabase.getDatabase(this)}
    val repository by lazy { ContactRepo(database.getContactDao()) }
}