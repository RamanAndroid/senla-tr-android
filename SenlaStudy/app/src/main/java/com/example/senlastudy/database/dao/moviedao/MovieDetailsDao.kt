package com.example.senlastudy.database.dao.moviedao

import com.example.senlastudy.database.dao.Dao
import com.example.senlastudy.retrofit.pojo.DetailsMovie

interface MovieDetailsDao : Dao {
    fun findById(id: String): DetailsMovie?
    fun put(movie: DetailsMovie)
    fun update(movie: DetailsMovie)
}