package com.example.senlastudy.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.senlastudy.retrofit.pojo.TestMovie
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class MovieDatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object {
        //Database параметры
        private const val DATABASE_NAME = "Movie"
        private const val DATABASE_VERSION = 1

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

        private var movieDatabaseHelper: SQLiteDatabase? = null

        fun insertMovie(movie: TestMovie) {
            val currentDate = Date()
            val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val dateText: String = dateFormat.format(currentDate)
            movieDatabaseHelper?.compileStatement(
                InsertQueryBuilder().table("movies")
                    .insertValue(ID_MOVIE, "${movie.id}")
                    .insertValue(TITLE, "\"${movie.title}\"")
                    .insertValue(RELEASE_DATE, "\"${movie.releaseDate}\"")
                    .insertValue(ORIGINAL_TITLE, "\"${movie.originalTitle}\"")
                    .insertValue(ORIGINAL_LANGUAGE, "\"${movie.originalLanguage}\"")
                    .insertValue(BACKDROP_PATH, "\"${movie.backdropPath}\"")
                    .insertValue(OVERVIEW, "\"${movie.overview}\"")
                    .insertValue(POPULARITY, "\"${movie.popularity}\"")
                    .insertValue(VOTE_AVERAGE, "\"${movie.voteAverage}\"")
                    .insertValue(VOTE_COUNT, "\"${movie.voteCount}\"")
                    .insertValue(RECORDING_TIME, "\"${dateText}\"")
                    .insert()
            )?.execute()
        }

        fun selectByFieldValue(tableName: String, field: String, value: Int): TestMovie? {

            var movie: TestMovie? = null
            val cursor = movieDatabaseHelper?.rawQuery(
                "SELECT * FROM $tableName WHERE $field = '$value'",
                null
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


                        movie = TestMovie(
                            id,
                            title,
                            releaseDate,
                            originalTitle,
                            originalLanguage,
                            backdropPath,
                            overview,
                            popularity,
                            voteAverage,
                            voteCount
                        )
                    } while (cursor.moveToNext())
                }
                cursor.close()
            }
            return movie
        }
    }

    fun movieDatabaseHelper(context: Context) {
        movieDatabaseHelper = if (movieDatabaseHelper == null) {
            MovieDatabaseHelper(context).writableDatabase
        } else {
            null
        }
    }

    override fun onConfigure(db: SQLiteDatabase?) {
        super.onConfigure(db)
        db?.setForeignKeyConstraintsEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            CreateQueryBuilder().table("movies").pkField(ID_DATABASE, true)
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

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }
}