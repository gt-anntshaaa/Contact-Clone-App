package com.example.simplecontact.ui.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.simplecontact.ui.data.local.entity.ContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM Contact ORDER BY firstname ASC")
    fun getAllContact(): Flow<List<ContactEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: ContactEntity)

    @Update()
    suspend fun update(contact: ContactEntity)

//    @Delete
//    suspend fun delete(contact: ContactEntity)
    @Query("DELETE FROM Contact WHERE id = :contactId")
    suspend fun delete(contactId: Int)

    @Query("SELECT * FROM Contact WHERE firstname LIKE :searchQuery OR lastname LIKE :searchQuery")
    fun search(searchQuery: String): Flow<List<ContactEntity>>
}