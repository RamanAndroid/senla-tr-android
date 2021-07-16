package com.example.senlastudy.database.dao.contactdao

import android.content.ContentResolver
import android.database.sqlite.SQLiteDatabase
import android.provider.ContactsContract
import com.example.senlastudy.database.dao.BaseDao
import com.example.senlastudy.database.entity.Contact

class ContactsDaoImpl(private val contentResolver: ContentResolver) : BaseDao(), ContactsDao {

    override fun getContactList(): List<Contact> {
        val listContact = arrayListOf<Contact>()
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val cursor = contentResolver.query(
            uri,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ),
            null,
            null,
            null)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val contactName =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val contactNumber =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                listContact.add(
                    Contact(
                        nameContact = contactName,
                        numberContact = contactNumber
                    )
                )
            } while (cursor.moveToNext())
            cursor.close()
        }
        return listContact
    }

    override fun create() {
        //Здесь можно создать таблицу в которой будет храниться наша таблица с контактами
    }

    override fun migrate(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //Здесь можно перенести таблицу на новую версию
    }
}