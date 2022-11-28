package com.example.simplecontact.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplecontact.ui.data.repository.ContactRepo

class ContactViewModelFactory(private val contactRepo: ContactRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(ContactViewModel::class.java)){
           @Suppress("UNCHECKED_CAST")
           return ContactViewModel(contactRepo) as T
       }
        throw java.lang.IllegalArgumentException("Unknown ContactViewModel Class")
    }
}