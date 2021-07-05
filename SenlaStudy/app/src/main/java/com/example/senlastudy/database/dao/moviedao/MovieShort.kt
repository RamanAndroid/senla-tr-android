package com.example.senlastudy.database.dao.moviedao

import android.database.sqlite.SQLiteDatabase
import com.example.senlastudy.database.dao.BaseDao

class MovieShort : BaseDao(), MovieShortDao {
    override fun create() {
//Какое-то создание таблицы
    }

    override fun migrate(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}