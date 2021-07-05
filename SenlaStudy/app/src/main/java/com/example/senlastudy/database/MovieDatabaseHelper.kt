package com.example.senlastudy.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.senlastudy.database.dao.Dao
import com.example.senlastudy.database.dao.moviedao.MovieDetailsDao
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class MovieDatabaseHelper(context: Context, private val arrayWithDao: List<Dao>) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object {
        //Database параметры
        private const val DATABASE_NAME = "Movie"
        private const val DATABASE_VERSION = 1
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

        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    }

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db?.setForeignKeyConstraintsEnabled(true)

        arrayWithDao.forEach {
            it.init(db)
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        arrayWithDao.forEach {
            it.create()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }
}
//Создание Таблицы onCreate(
/*
   val execSQL = db?.execSQL(
       CreateQueryBuilder().table(DATABASE_TABLE_MOVIES).pkField(ID_DATABASE, true)
           .insertTextField(ID_MOVIE, true)
           .insertTextField(TITLE, true)
           .insertTextField(RELEASE_DATE, true)
           .insertTextField(ORIGINAL_TITLE, true)
           .insertTextField(ORIGINAL_LANGUAGE, true)
           .insertTextField(BACKDROP_PATH, true)
           .insertTextField(OVERVIEW, true)
           .insertTextField(POPULARITY, true)
           .insertTextField(VOTE_AVERAGE, true)
           .insertTextField(VOTE_COUNT, true)
           .insertTextField(RECORDING_TIME, true)
           .create()
   )
    */

//Методы SelectById and insertMovie
/*
   private val db = writableDatabase
   fun selectByFieldValue(tableName: String, field: String, value: Int): DetailsMovie? {
       db.beginTransaction()
       try {
           var movie: DetailsMovie? = null
           val cursor = db.rawQuery(
               SelectQueryBuilder().table(tableName).selectByField(field, value), null
           )
           if (cursor != null) {
               if (cursor.moveToFirst()) {
                   val idColumn = cursor.getColumnIndexOrThrow(ID_MOVIE)
                   val titleColumn = cursor.getColumnIndexOrThrow(TITLE)
                   val releaseDateColumn = cursor.getColumnIndexOrThrow(RELEASE_DATE)
                   val originalTitleColumn = cursor.getColumnIndexOrThrow(ORIGINAL_TITLE)
                   val originalLanguageColumn = cursor.getColumnIndexOrThrow(ORIGINAL_LANGUAGE)
                   val backdropPathColumn = cursor.getColumnIndexOrThrow(BACKDROP_PATH)
                   val overviewColumn = cursor.getColumnIndexOrThrow(OVERVIEW)
                   val popularityColumn = cursor.getColumnIndexOrThrow(POPULARITY)
                   val voteAverageColumn = cursor.getColumnIndexOrThrow(VOTE_AVERAGE)
                   val voteCountColumn = cursor.getColumnIndexOrThrow(VOTE_COUNT)
                   val recordingTImeColumn = cursor.getColumnIndexOrThrow(RECORDING_TIME)
                   do {
                       val id = cursor.getInt(idColumn)
                       val title = cursor.getString(titleColumn)
                       val releaseDate = cursor.getString(releaseDateColumn)
                       val originalTitle = cursor.getString(originalTitleColumn)
                       val originalLanguage = cursor.getString(originalLanguageColumn)
                       val backdropPath = cursor.getString(backdropPathColumn)
                       val overview = cursor.getString(overviewColumn)
                       val popularity = cursor.getString(popularityColumn)
                       val voteAverage = cursor.getString(voteAverageColumn)
                       val voteCount = cursor.getString(voteCountColumn)
                       val recordingTIme = cursor.getString(recordingTImeColumn)
                       movie = DetailsMovie(
                           id,
                           title,
                           releaseDate,
                           originalTitle,
                           originalLanguage,
                           backdropPath,
                           overview,
                           popularity,
                           voteAverage,
                           voteCount,
                           dateFormat.parse(recordingTIme)
                       )
                   } while (cursor.moveToNext())
               }
               cursor.close()
           }
           db.setTransactionSuccessful()
           return movie
       } finally {
           db.endTransaction()
       }
   }

   fun insertMovie(movie: DetailsMovie) {
       db.beginTransaction()
       try {
           val currentDate = Date()
           val dateText: String = dateFormat.format(currentDate)
           db.compileStatement(
               InsertQueryBuilder().table(DATABASE_TABLE_MOVIES)
                   .insertTextValue(ID_MOVIE, "${movie.id}")
                   .insertTextValue(TITLE, movie.title)
                   .insertTextValue(RELEASE_DATE, movie.releaseDate)
                   .insertTextValue(ORIGINAL_TITLE, movie.originalTitle)
                   .insertTextValue(ORIGINAL_LANGUAGE, movie.originalLanguage)
                   .insertTextValue(BACKDROP_PATH, movie.backdropPath)
                   .insertTextValue(OVERVIEW, movie.overview)
                   .insertTextValue(POPULARITY, movie.popularity)
                   .insertTextValue(VOTE_AVERAGE, movie.voteAverage)
                   .insertTextValue(VOTE_COUNT, movie.voteCount)
                   .insertTextValue(RECORDING_TIME, dateText)
                   .insert()
           )?.execute()
           db.setTransactionSuccessful()
       } finally {
           db.endTransaction()
       }

   }

    */