package com.example.senlastudy.database.dao

import android.database.sqlite.SQLiteDatabase

abstract class BaseDao : Dao {

    protected var db: SQLiteDatabase? = null
    private set

    override fun init(db: SQLiteDatabase) {
        this.db = db
    }
}