package com.alfidh02.tvmov.testutil.di

import android.content.Context
import com.alfidh02.tvmov.model.data.local.LocalDataSource
import com.alfidh02.tvmov.model.data.remote.RemoteDataSource
import com.alfidh02.tvmov.model.data.local.room.TVMovieDatabase
import com.alfidh02.tvmov.model.repository.TVMovieRepository
import com.alfidh02.tvmov.testutil.AppExecutors

object Injection {
    fun provideRepository(context: Context): TVMovieRepository {

        val tvMovieDb = TVMovieDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(tvMovieDb.filmDao())
        val appExecutors = AppExecutors()

        return TVMovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}