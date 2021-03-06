package com.submissionalfi3.tvmov.utilities.di

import android.content.Context
import com.submissionalfi3.tvmov.model.data.local.LocalDataSource
import com.submissionalfi3.tvmov.model.data.remote.RemoteDataSource
import com.submissionalfi3.tvmov.model.data.local.room.TVMovieDatabase
import com.submissionalfi3.tvmov.model.repository.TVMovieRepository
import com.submissionalfi3.tvmov.utilities.AppExecutors

object Injection {
    fun provideRepository(context: Context): TVMovieRepository {

        val tvMovieDb = TVMovieDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(tvMovieDb.filmDao())
        val appExecutors = AppExecutors()

        return TVMovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}