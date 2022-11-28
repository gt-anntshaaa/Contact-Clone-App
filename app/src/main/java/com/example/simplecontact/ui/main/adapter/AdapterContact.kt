package com.example.simplecontact.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecontact.R
import com.example.simplecontact.databinding.ItemContactBinding
import com.example.simplecontact.ui.data.local.entity.ContactEntity
import com.example.simplecontact.ui.main.home.HomeFragmentDirections
import com.example.simplecontact.ui.main.viewmodel.ContactViewModel

class AdapterContact(private val contactViewModel: ContactViewModel) : ListAdapter<ContactEntity, AdapterContact.ViewHolderContact>(Diffcalback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderContact {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderContact(ItemContactBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderContact, position: Int) {
        holder.bindView(getItem(position))
    }


    companion object{
        private val Diffcalback = object  : DiffUtil.ItemCallback<ContactEntity>(){
            override fun areItemsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(
                oldItem: ContactEntity,
                newItem: ContactEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


    inner class ViewHolderContact(private val bind: ItemContactBinding) : RecyclerView.ViewHolder(bind.root){
        fun bindView(contact: ContactEntity){
            bind.tvItemName.text = contact.firstname.plus(" ${contact.lastname}")

            bind.root.setOnClickListener {
                val firstname = contact.firstname
                val lastname = contact.lastname
                val phone = contact.phoneNumber
                val email = contact.email
                val id = contact.id
                val contactArgs = ContactEntity(firstname,lastname,email,phone)
                contactArgs.id = id


                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(contactArgs)
                it.findNavController().navigate(action)
            }
        }

    }


}