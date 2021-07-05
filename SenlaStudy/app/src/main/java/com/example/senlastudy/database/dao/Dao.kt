package com.example.senlastudy.database.dao

import android.database.sqlite.SQLiteDatabase
import com.example.senlastudy.retrofit.pojo.DetailsMovie

interface Dao {
    fun create()
    fun migrate(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    fun init(db: SQLiteDatabase)
}