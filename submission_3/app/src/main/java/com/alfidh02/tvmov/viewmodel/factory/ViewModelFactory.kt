package com.alfidh02.tvmov.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alfidh02.tvmov.testutil.di.Injection
import com.alfidh02.tvmov.model.repository.TVMovieRepository
import com.alfidh02.tvmov.viewmodel.detail.DetailViewModel
import com.alfidh02.tvmov.viewmodel.favorite.FavoriteViewModel
import com.alfidh02.tvmov.viewmodel.movies.MovieViewModel
import com.alfidh02.tvmov.viewmodel.tv.TVViewModel

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
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(tvMovieRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(tvMovieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}