package com.example.simplecontact.ui.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.simplecontact.ui.data.local.dao.ContactDao
import com.example.simplecontact.ui.data.local.entity.ContactEntity
import  kotlinx.coroutines.flow.Flow

class ContactRepo(private val contactDao: ContactDao) {
    val allContact: Flow<List<ContactEntity>> = contactDao.getAllContact()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(contact: ContactEntity){
        contactDao.insert(contact)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(contact: ContactEntity){
        contactDao.update(contact)
    }
//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun delete(contact: ContactEntity){
//        contactDao.delete(contact)
//    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(contactId: Int){
        contactDao.delete(contactId)
    }

    fun search(searchQuery: String): Flow<List<ContactEntity>>{
        return contactDao.search(searchQuery)
    }
}