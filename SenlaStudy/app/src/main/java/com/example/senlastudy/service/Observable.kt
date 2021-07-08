package com.example.senlastudy.service

interface Observable {
    val observers: ArrayList<Observer>
    var isConnected:Boolean

    fun add(observer: Observer) {
        observers.add(observer)
    }

    fun remove(observer: Observer) {
        observers.remove(observer)
    }

    fun sendUpdateEvent() {
        observers.forEach { it.update() }
    }
}