package com.example.senlastudy.database.dao.moviedao

import android.content.ContentResolver
import android.database.sqlite.SQLiteDatabase
import com.example.senlastudy.database.MovieDatabaseHelper
import com.example.senlastudy.database.dao.BaseDao
import com.example.senlastudy.database.querybuilder.CreateQueryBuilder
import com.example.senlastudy.retrofit.pojo.DetailsMovie
import java.util.*

class MovieDetailsDaoImpl : BaseDao(), MovieDetailsDao {

    companion object {
        private const val DATABASE_TABLE_MOVIES = "movies"
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
        val sqlSelect = "SELECT * FROM $DATABASE_TABLE_MOVIES WHERE id_movie = ?"
        var movie: DetailsMovie? = null
        if (db != null) {
            db?.let {
                it.beginTransaction()
                try {
                    val cursor = it.rawQuery(sqlSelect, arrayOf(id))
                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            val idColumn = cursor.getColumnIndexOrThrow(ID_MOVIE)
                            val titleColumn = cursor.getColumnIndexOrThrow(TITLE)
                            val releaseDateColumn =
                                cursor.getColumnIndexOrThrow(RELEASE_DATE)
                            val originalTitleColumn =
                                cursor.getColumnIndexOrThrow(ORIGINAL_TITLE)
                            val originalLanguageColumn =
                                cursor.getColumnIndexOrThrow(ORIGINAL_LANGUAGE)
                            val backdropPathColumn =
                                cursor.getColumnIndexOrThrow(BACKDROP_PATH)
                            val overviewColumn = cursor.getColumnIndexOrThrow(OVERVIEW)
                            val popularityColumn =
                                cursor.getColumnIndexOrThrow(POPULARITY)
                            val voteAverageColumn =
                                cursor.getColumnIndexOrThrow(VOTE_AVERAGE)
                            val voteCountColumn =
                                cursor.getColumnIndexOrThrow(VOTE_COUNT)
                            val recordingTImeColumn =
                                cursor.getColumnIndexOrThrow(RECORDING_TIME)
                            do {
                                val idDb = cursor.getInt(idColumn)
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
                                    idDb,
                                    title,
                                    releaseDate,
                                    originalTitle,
                                    originalLanguage,
                                    backdropPath,
                                    overview,
                                    popularity,
                                    voteAverage,
                                    voteCount,
                                    MovieDatabaseHelper.dateFormat.parse(recordingTIme)
                                )
                            } while (cursor.moveToNext())
                        }
                        it.setTransactionSuccessful()
                        cursor.close()
                    }
                } finally {
                    it.endTransaction()
                }
            }
        } else {
            error("SQLiteDatabase was not created")
        }
        return movie
    }

    override fun put(movie: DetailsMovie) {
        if (db != null) {
            db?.let {
                val currentDate = Date()
                val dateText: String = MovieDatabaseHelper.dateFormat.format(currentDate)
                val sqlInsert =
                    "INSERT INTO $DATABASE_TABLE_MOVIES ($ID_MOVIE," +
                            "$TITLE,$RELEASE_DATE,$ORIGINAL_TITLE," +
                            "$ORIGINAL_LANGUAGE,$BACKDROP_PATH," +
                            "$OVERVIEW,$POPULARITY,$VOTE_AVERAGE, $VOTE_COUNT, $RECORDING_TIME) VALUES (?,?,?,?,?,?,?,?,?,?,?)"

                it.beginTransaction()
                try {
                    val sqlStatement = it.compileStatement(sqlInsert)
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
                    it.setTransactionSuccessful()
                    sqlStatement.executeInsert()
                } finally {
                    it.endTransaction()
                }
            }
        } else {
            error("SQLiteDatabase was not created")
        }
    }

    override fun update(movie: DetailsMovie) {
        if (db != null) {
            db?.let {
                val currentDate = Date()
                val dateText: String = MovieDatabaseHelper.dateFormat.format(currentDate)
                val sqlInsert =
                    "UPDATE $DATABASE_TABLE_MOVIES SET $ID_MOVIE = ?," +
                            "$TITLE = ?,$RELEASE_DATE = ?,$ORIGINAL_TITLE = ?," +
                            "$ORIGINAL_LANGUAGE = ?,$BACKDROP_PATH = ?," +
                            "$OVERVIEW = ?,$POPULARITY = ?,$VOTE_AVERAGE = ?, $VOTE_COUNT = ?, $RECORDING_TIME = ? WHERE $ID_MOVIE = ?"

                it.beginTransaction()
                try {
                    val sqlStatement = it.compileStatement(sqlInsert)
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
                    sqlStatement.bindString(12, movie.id.toString())
                    it.setTransactionSuccessful()
                    sqlStatement.executeUpdateDelete()
                } finally {
                    it.endTransaction()
                }
            }
        } else {
            error("SQLiteDatabase was not created")
        }
    }

    override fun create() {
        if (db != null) {
            db?.execSQL(
                CreateQueryBuilder().table(DATABASE_TABLE_MOVIES).pkField(
                    ID_DATABASE, true
                )
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

        } else {
            error("SQLiteDatabase was not created")
        }
    }

    override fun migrate(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}