package com.alfidh.tvmov.di

import android.content.Context
import com.alfidh.tvmov.model.data.remote.source.RemoteDataSource
import com.alfidh.tvmov.model.repository.TVMovieRepository

object Injection {
    fun provideRepository(context: Context): TVMovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return TVMovieRepository.getInstance(remoteDataSource)
    }
}