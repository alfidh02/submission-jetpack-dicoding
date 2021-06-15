package com.submissionalfi3.tvmov.model.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.submissionalfi3.tvmov.model.data.local.entity.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entity.TVEntity

@Database(
    entities = [MovieEntity::class, TVEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TVMovieDatabase : RoomDatabase() {
    abstract fun filmDao(): TVMovieDao

    companion object {

        @Volatile
        private var INSTANCE: TVMovieDatabase? = null

        fun getInstance(context: Context): TVMovieDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TVMovieDatabase::class.java,
                    "TVMovie.db"
                ).build().apply { INSTANCE = this }
            }
    }
}