package com.example.senlastudy.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.senlastudy.retrofit.pojo.Movie


class MovieDatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object {
        //Database параметры
        private const val DATABASE_NAME = "Movie"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME_MOVIE_NOW_PLAYING = "now_playing"
        private const val TABLE_NAME_MOVIE_POPULAR = "popular"
        private const val TABLE_NAME_MOVIE_TOP_RATED = "top_rated"
        private const val TABLE_NAME_MOVIE_UPCOMING = "upcoming"

        //Наименование полей в Базе данных
        private const val ID = "id"
        private const val IMAGE = "image"
        private const val TITLE = "title"
        private const val RELEASE_DATE = "release_date"
        private const val ORIGINAL_TITLE = "original_title"
        private const val ORIGINAL_LANGUAGE = "original_language"

        private var movieDatabaseHelper: MovieDatabaseHelper? = null
    }

    fun getMovieDataBaseHelper(context: Context): MovieDatabaseHelper {
        if (movieDatabaseHelper == null) {
            movieDatabaseHelper = MovieDatabaseHelper(context.applicationContext)
        }
        return movieDatabaseHelper ?: error("Object MovieDatabaseHelper cannot create!")
    }

    override fun onConfigure(db: SQLiteDatabase?) {
        super.onConfigure(db)
        db?.setForeignKeyConstraintsEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_NOW_PLAYING_TABLE: String =
            StringBuilder(
                "CREATE TABLE $TABLE_NAME_MOVIE_NOW_PLAYING(" +
                        "$ID INTEGER PRIMARY KEY," +
                        "$IMAGE TEXT," +
                        "$TITLE TEXT," +
                        "$RELEASE_DATE TEXT," +
                        "$ORIGINAL_TITLE TEXT, " +
                        "$ORIGINAL_LANGUAGE TEXT)"
            ).toString()

        db?.execSQL(CREATE_NOW_PLAYING_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_MOVIE_NOW_PLAYING")
            onCreate(db)
        }
    }

    fun addMovie(movie: Movie) {
        val db = writableDatabase
        db.beginTransaction()
        try {
            val values = ContentValues()
            values.put(ID, movie.id)
            values.put(IMAGE, movie.image)
            values.put(RELEASE_DATE, movie.releaseDate)
            values.put(ORIGINAL_TITLE, movie.originalTitle)
            values.put(ORIGINAL_LANGUAGE, movie.originalLanguage)
            db.insertOrThrow(TABLE_NAME_MOVIE_NOW_PLAYING, null, values)
            db.setTransactionSuccessful()
        } catch (e: Exception) {

        } finally {
            db.endTransaction()
        }
    }

}