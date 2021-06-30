package com.example.senlastudy.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.senlastudy.retrofit.pojo.Movie
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
        private const val RECORDING_TIME = "recording_time"

        private var movieWritableDatabaseHelper: SQLiteDatabase? = null
        private var movieReadableDatabaseHelper: SQLiteDatabase? = null

        fun insertMovie(movie: Movie) {
            val currentDate = Date()
            val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val dateText: String = dateFormat.format(currentDate)
            movieWritableDatabaseHelper?.compileStatement(
                QueryBuilder().table("movies")
                    .insertField(ID_MOVIE, "").insertValue("${movie.id},")
                    .insertField(IMAGE, "").insertValue("\"${movie.image}\",")
                    .insertField(TITLE, "").insertValue("\"${movie.title}\",")
                    .insertField(RELEASE_DATE, "").insertValue("\"${movie.releaseDate}\",")
                    .insertField(ORIGINAL_TITLE, "").insertValue("\"${movie.originalTitle}\",")
                    .insertField(ORIGINAL_LANGUAGE, "")
                    .insertValue("\"${movie.originalLanguage}\",")
                    .insertField(BACKDROP_PATH, "").insertValue("\"${movie.backdropPath}\",")
                    .insertField(OVERVIEW, "").insertValue("\"${movie.overview}\",")
                    .insertField(POPULARITY, "").insertValue("\"${movie.popularity}\",")
                    .insertField(VOTE_AVERAGE, "").insertValue("\"${movie.voteAverage}\",")
                    .insertField(VOTE_COUNT, "").insertValue("\"${movie.voteCount}\",")
                    .insertField(RECORDING_TIME, "").insertValue("\"${dateText}\"")
                    .insert()
            )?.execute()
        }

        fun selectByFieldValue(tableName: String, field: String, value: String): TestMovie {

            lateinit var movie:TestMovie
            val cursor = movieReadableDatabaseHelper?.rawQuery(
                "SELECT * FROM $tableName WHERE $field = '$value'",
                null
            )
            if (cursor != null) {

                if (cursor.moveToFirst()) {
                    val idColumn = cursor.getColumnIndexOrThrow(ID_DATABASE)
                    val imageColumn = cursor.getColumnIndexOrThrow(IMAGE)
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
                        val image = cursor.getString(imageColumn)
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
                                image,
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

    fun openDatabaseHelper(context: Context) {
        if (movieWritableDatabaseHelper == null && movieReadableDatabaseHelper == null) {
            movieWritableDatabaseHelper = MovieDatabaseHelper(context).writableDatabase
            movieReadableDatabaseHelper = MovieDatabaseHelper(context).readableDatabase
        }
    }


    fun closeDatabaseHelper() {
        movieReadableDatabaseHelper = null
        movieReadableDatabaseHelper = null
    }

    override fun onConfigure(db: SQLiteDatabase?) {
        super.onConfigure(db)
        db?.setForeignKeyConstraintsEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            QueryBuilder().table("movies").pkField(ID_DATABASE)
                .insertField(ID_MOVIE, "INTEGER NOT NULL")
                .insertField(IMAGE, "TEXT NOT NULL")
                .insertField(TITLE, "TEXT NOT NULL")
                .insertField(RELEASE_DATE, "TEXT NOT NULL")
                .insertField(ORIGINAL_TITLE, "TEXT NOT NULL")
                .insertField(ORIGINAL_LANGUAGE, "TEXT NOT NULL")
                .insertField(BACKDROP_PATH, "TEXT NOT NULL")
                .insertField(OVERVIEW, "TEXT NOT NULL")
                .insertField(POPULARITY, "TEXT NOT NULL")
                .insertField(VOTE_AVERAGE, "TEXT NOT NULL")
                .insertField(VOTE_COUNT, "TEXT NOT NULL")
                .insertField(RECORDING_TIME, "TEXT NOT NULL")
                .create()
        )

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }
}