package com.example.simplecontact.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.simplecontact.ui.data.local.entity.ContactEntity
import com.example.simplecontact.ui.data.repository.ContactRepo
import kotlinx.coroutines.launch

class ContactViewModel (private val  contactRepo: ContactRepo) : ViewModel(){
    val allContact: LiveData<List<ContactEntity>> = contactRepo.allContact.asLiveData()

    fun insert(contact: ContactEntity) = viewModelScope.launch {
        contactRepo.insert(contact)
    }

    fun update(contact: ContactEntity) = viewModelScope.launch {
        contactRepo.update(contact)
    }

//    fun delete(contact: ContactEntity) = viewModelScope.launch {
//        contactRepo.delete(contact)
//    }

    fun delete(contactId: Int) = viewModelScope.launch {
        contactRepo.delete(contactId)
    }

    fun search(searchQuery: String) : LiveData<List<ContactEntity>>{
        return contactRepo.search(searchQuery).asLiveData()
    }
}