package com.example.senlastudy.presenter.share

import com.example.senlastudy.database.dao.contactdao.ContactsDao
import com.example.senlastudy.database.entity.Contact
import com.example.senlastudy.presenter.BasePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.schedulers.Schedulers

class ContactListPresenter(private val contactsDao: ContactsDao) :
    BasePresenter<ContactListContract.ViewContactList>(),
    ContactListContract.PresenterContactList {

    override fun getContactList() {
        val createObserver = Observable.create(ObservableOnSubscribe<List<Contact>?> { emitter ->
            val contactList = contactsDao.getContactList()
            emitter.onNext(contactList)
            emitter.onComplete()
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
            .subscribe({ contactList ->
                if (isViewAttached()) {
                    if (!contactList.isNullOrEmpty()) {
                        getView().setData(contactList)
                    }
                }
            }, { t ->
                if (isViewAttached()) {
                    getView().errorResponse(t)
                }
            })
    }

}

