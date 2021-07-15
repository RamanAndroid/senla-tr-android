package com.example.senlastudy.database.dao.contactdao

import android.database.sqlite.SQLiteDatabase
import com.example.senlastudy.database.dao.BaseDao
import com.example.senlastudy.database.entity.Contact

class ContactList : BaseDao(), ContactListDao {

    override fun getContactList(): List<Contact?> {
        TODO("Not yet implemented")
    }

    override fun create() {
        TODO("Not yet implemented")
    }

    override fun migrate(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}