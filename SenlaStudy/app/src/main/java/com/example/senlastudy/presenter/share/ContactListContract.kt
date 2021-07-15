package com.example.senlastudy.presenter.share

import com.example.senlastudy.database.entity.Contact
import com.example.senlastudy.presenter.MainContract

interface ContactListContract{

    interface ViewContactList:MainContract.View{
        fun setData(contactList:List<Contact>)
        fun errorResponse(throwable: Throwable)
    }

    interface PresenterContactList:MainContract.Presenter<ViewContactList>{
        fun getContactList()
    }
}