package com.example.senlastudy.service

import android.util.Log

data class ObserverStation(
    private val subscribers: ArrayList<ILiveData.InternetStateSubscriber> = arrayListOf(),
) : ILiveData.Publisher {

    private var internetState: Boolean = false
        set(value) {
            field = value
            this.notifySubscribers()
        }

    override fun register(subscriber: ILiveData.InternetStateSubscriber) {
        subscribers.add(subscriber)
        subscriber.update(internetState)
    }

    override fun remove(subscriber: ILiveData.InternetStateSubscriber) {
        subscribers.remove(subscriber)
    }

    private fun notifySubscribers() {
        subscribers.forEach {
            it.update(internetState)
        }
    }

    fun updateData(internetState: Boolean) {
        this.internetState = internetState
    }
}
