package com.example.senlastudy.database.dao.contactdao

import com.example.senlastudy.database.dao.Dao
import com.example.senlastudy.database.entity.Contact

interface ContactsDao : Dao {
    fun getContactList(): List<Contact>?
}