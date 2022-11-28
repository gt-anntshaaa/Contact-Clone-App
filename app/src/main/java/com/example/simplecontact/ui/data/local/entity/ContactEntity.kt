package com.example.simplecontact.ui.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Contact")
@Parcelize
data class ContactEntity (
    @ColumnInfo(name = "firstname") val firstname: String,
    @ColumnInfo(name = "lastname") val lastname: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name= "phone_number") val phoneNumber: String,
) : Parcelable{
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}