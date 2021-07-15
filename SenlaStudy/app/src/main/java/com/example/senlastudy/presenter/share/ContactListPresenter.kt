package com.example.senlastudy.presenter.share

import android.provider.ContactsContract
import com.example.senlastudy.database.dao.contactdao.ContactListDao
import com.example.senlastudy.database.entity.Contact
import com.example.senlastudy.presenter.BasePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.schedulers.Schedulers

class ContactListPresenter(private val contactListDao: ContactListDao) : BasePresenter<ContactListContract.ViewContactList>(),
    ContactListContract.PresenterContactList {

    override fun getContactList() {

        val listContact = arrayListOf<Contact>()

        val createObserver = Observable.create(ObservableOnSubscribe<Contact> { emitter ->
            val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            val cursor = contentResolver.query(uri, null, null, null, null)
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
        })

        createObserver.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                if (isViewAttached()) {
                    getView().showViewLoading()
                }
            }
            .doFinally {
                if (isViewAttached()) {
                    getView().hideViewLoading()
                }
            }
            .subscribe({ movieTest ->
                if (isViewAttached()) {
                    getView().setData(movieTest)
                }
            }, { t ->
                if (isViewAttached()) {
                    getView().errorResponse(t)
                }
            })
    }
}

