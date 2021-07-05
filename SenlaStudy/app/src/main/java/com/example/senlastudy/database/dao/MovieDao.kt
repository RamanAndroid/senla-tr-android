package com.example.senlastudy.database.dao

import com.example.senlastudy.MovieApplication
import com.example.senlastudy.database.MovieDatabaseHelper
import com.example.senlastudy.retrofit.pojo.DetailsMovie
import java.util.*
/*
class MovieDao : Dao {

    private val db = MovieApplication.movieDatabaseHelper.writableDatabase

    companion object {
        //Database параметры
        private const val DATABASE_TABLE_MOVIES = "movies"

        //Наименование полей в Базе данных
        private const val ID_DATABASE = "id_database"
        private const val ID_MOVIE = "id_movie"
        private const val TITLE = "title"
        private const val RELEASE_DATE = "release_date"
        private const val ORIGINAL_TITLE = "original_title"
        private const val ORIGINAL_LANGUAGE = "original_language"
        private const val BACKDROP_PATH = "backdrop_path"
        private const val OVERVIEW = "overview"
        private const val POPULARITY = "popularity"
        private const val VOTE_AVERAGE = "vote_average"
        private const val VOTE_COUNT = "vote_count"
        private const val RECORDING_TIME = "recording_time"
    }

    override fun findById(id: String): DetailsMovie? {

    }

    override fun put(movie: DetailsMovie) {
        val currentDate = Date()
        val dateText: String = MovieDatabaseHelper.dateFormat.format(currentDate)
        val sqlInsert =
            "INSERT INTO $DATABASE_TABLE_MOVIES ($ID_MOVIE," +
                    "$TITLE,$RELEASE_DATE,$ORIGINAL_TITLE," +
                    "$ORIGINAL_LANGUAGE,$BACKDROP_PATH," +
                    "$OVERVIEW,$POPULARITY,$VOTE_AVERAGE, $VOTE_COUNT, $RECORDING_TIME) VALUES (?,?,?,?,?,?,?,?,?,?,?)"

        db.beginTransaction()
        try {
            val sqlStatement = db.compileStatement(sqlInsert)
            sqlStatement.bindString(1, movie.id.toString())
            sqlStatement.bindString(2, movie.title)
            sqlStatement.bindString(3, movie.releaseDate)
            sqlStatement.bindString(4, movie.originalTitle)
            sqlStatement.bindString(5, movie.originalLanguage)
            sqlStatement.bindString(6, movie.backdropPath)
            sqlStatement.bindString(7, movie.overview)
            sqlStatement.bindString(8, movie.popularity)
            sqlStatement.bindString(9, movie.voteAverage)
            sqlStatement.bindString(10, movie.voteCount)
            sqlStatement.bindString(11, dateText)
            db.setTransactionSuccessful()
            sqlStatement.executeInsert()
        } finally {
            db.endTransaction()
        }
    }
}

 */