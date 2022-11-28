package com.example.simplecontact.ui.main.detail

import android.content.Context
import com.example.simplecontact.ui.data.local.entity.ContactEntity

interface SystemListener {
    fun CALL (phone: String)
    fun MESSAGE (phone: String)
    fun DELETE(contactId: Int)
    fun UPDATE(contact: ContactEntity)
}