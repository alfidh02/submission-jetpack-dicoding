package com.alfidh.tvmov.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alfidh.tvmov.di.Injection
import com.alfidh.tvmov.model.repository.TVMovieRepository
import com.alfidh.tvmov.viewmodel.movies.MovieViewModel
import com.alfidh.tvmov.viewmodel.tv.TVViewModel

class ViewModelFactory private constructor(private val tvMovieRepository: TVMovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(tvMovieRepository) as T
            }
            modelClass.isAssignableFrom(TVViewModel::class.java) -> {
                TVViewModel(tvMovieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}