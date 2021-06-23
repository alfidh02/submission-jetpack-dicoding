package com.submissionalfi3.tvmov.model.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.submissionalfi3.tvmov.model.data.local.entities.DetailEntity
import com.submissionalfi3.tvmov.model.data.local.entities.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entities.TVEntity
import com.submissionalfi3.tvmov.utilities.Converters

@Database(
    entities = [MovieEntity::class, TVEntity::class, DetailEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
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