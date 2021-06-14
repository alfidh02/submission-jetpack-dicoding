package com.alfidh02.tvmov.di

import android.content.Context
import com.alfidh02.tvmov.model.data.remote.source.LocalDataSource
import com.alfidh02.tvmov.model.data.remote.source.RemoteDataSource
import com.alfidh02.tvmov.model.data.room.TVMovieDatabase
import com.alfidh02.tvmov.model.repository.TVMovieRepository
import com.alfidh02.tvmov.util.AppExecutors

object Injection {
    fun provideRepository(context: Context): TVMovieRepository {

        val db = TVMovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(db.filmDao())
        val appExecutors = AppExecutors()

        return TVMovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}