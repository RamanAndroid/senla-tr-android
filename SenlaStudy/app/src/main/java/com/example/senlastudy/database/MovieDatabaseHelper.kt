package com.example.senlastudy.database

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
        private const val TABLE_NAME_MOVIE = "now_playing"

        //Наименование полей в Базе данных
        private const val ID = "id"
        private const val IMAGE = "image"
        private const val TITLE = "title"
        private const val RELEASE_DATE = "release_date"
        private const val ORIGINAL_TITLE = "original_title"
        private const val ORIGINAL_LANGUAGE = "original_language"
        private const val BACKDROP_PATH = "backdrop_path"
        private const val OVERVIEW = "overview"
        private const val POPULARITY = "popularity"
        private const val VOTE_AVERAGE = "vote_average"
        private const val VOTE_COUNT = "vote_count"

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
        /*
        val CREATE_NOW_PLAYING_TABLE: String =
            StringBuilder(
                "CREATE TABLE $TABLE_NAME_MOVIE(" +
                        "$ID INTEGER PRIMARY KEY," +
                        "$IMAGE TEXT," +
                        "$TITLE TEXT," +
                        "$RELEASE_DATE TEXT," +
                        "$ORIGINAL_TITLE TEXT," +
                        "$ORIGINAL_LANGUAGE TEXT," +
                        "$BACKDROP_PATH TEXT," +
                        "$OVERVIEW TEXT," +
                        "$POPULARITY INTEGER," +
                        "$VOTE_AVERAGE INTEGER," +
                        "$VOTE_COUNT INTEGER)"
            ).toString()

         */



        db?.execSQL(
            QueryBuilder().table("movies").pkField("id").insertField("title", "TEXT NOT NULL")
                .create()
        )
        db?.compileStatement(
            QueryBuilder().table("movies").insertValue("123").insertField("title", "")
                .insert()
        )?.execute()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

}