package com.example.senlastudy.service

interface ILiveData{
    interface Publisher{
        fun register(subscriber: InternetStateSubscriber)
        fun remove(subscriber: InternetStateSubscriber)
    }

    interface InternetStateSubscriber {
        fun update(internetState: Boolean)
    }

}


