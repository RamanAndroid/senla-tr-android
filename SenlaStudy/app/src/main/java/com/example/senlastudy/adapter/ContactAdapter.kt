package com.example.senlastudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.senlastudy.database.entity.Contact
import com.example.senlastudy.databinding.RowContactBinding

class ContactAdapter(private val listener: OnContactClickListener) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private var contact = emptyList<Contact>()

    inner class ContactViewHolder(private val binding: RowContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.rowContact.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = contact[adapterPosition]
                    listener.onContactClick(item)
                }
            }
        }

        fun bind(contact: Contact) {
            binding.apply {
                nameContact.text = contact.nameContact
                numberPhone.text = contact.numberContact
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = RowContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentItem = contact[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = contact.size

    fun setData(contact: List<Contact>) {
        this.contact += contact
    }

    interface OnContactClickListener {
        fun onContactClick(contact: Contact)
    }

}