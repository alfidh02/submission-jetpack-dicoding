package com.alfidh02.tvmov.model.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alfidh02.tvmov.model.data.entity.MovieEntity
import com.alfidh02.tvmov.model.data.entity.TVEntity

@Database(entities = [MovieEntity::class, TVEntity::class], version = 1, exportSchema = false)
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
                    "tvmovie.db"
                ).fallbackToDestructiveMigration().build().apply { INSTANCE = this }
            }
    }
}