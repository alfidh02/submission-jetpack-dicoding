package com.submissionalfi3.tvmov.view.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.submissionalfi3.tvmov.R
import com.submissionalfi3.tvmov.databinding.ActivityDetailBinding
import com.submissionalfi3.tvmov.databinding.ContentDetailMovieTvBinding
import com.submissionalfi3.tvmov.model.data.local.entities.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entities.TVEntity
import com.submissionalfi3.tvmov.utilities.vo.Status
import com.submissionalfi3.tvmov.viewmodel.detail.DetailViewModel
import com.submissionalfi3.tvmov.viewmodel.factory.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.submissionalfi3.tvmov.model.data.local.entities.DetailEntity

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_CHOICE = "choice"
    }

    private lateinit var detailActivityBinding: ActivityDetailBinding
    private lateinit var detailContentBinding: ContentDetailMovieTvBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailActivityBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = detailActivityBinding.detailContent
        setContentView(detailActivityBinding.root)

        setSupportActionBar(detailActivityBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val tvMovieChoice = intent.getStringExtra(EXTRA_CHOICE)
        if (tvMovieChoice != null) {
            when (tvMovieChoice) {
                "MOVIE" -> {
                    val movie = intent.getParcelableExtra<MovieEntity>(EXTRA_DATA) as MovieEntity
                    getMovieData(movie.id)
                    setFavButtonState(movie.favorite)
                }
                "TV_SHOW" -> {
                    val tv = intent.getParcelableExtra<TVEntity>(EXTRA_DATA) as TVEntity
                    getTVData(tv.id)
                    setFavButtonState(tv.favorite)
                }
            }
        }
        setFavorite()
    }

    private fun setFavButtonState(state: Boolean) {
        detailContentBinding.btnFav.isChecked = state
    }

    private fun setFavorite() {
        val tvMovieChoose = intent.getStringExtra(EXTRA_CHOICE)
        if (tvMovieChoose != null) {
            detailContentBinding.btnFav.setOnClickListener {
                when (tvMovieChoose) {
                    "MOVIE" -> {
                        val movie =
                            intent.getParcelableExtra<MovieEntity>(EXTRA_DATA) as MovieEntity
                        val newState = !movie.favorite
                        if (newState) {
                            Toast.makeText(this, "Berhasil ditambahkan ke favorit!", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(
                                this,
                                "Dihapus dari favorit.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        viewModel.setMovieFavorite(movie, newState)
                    }
                    "TV_SHOW" -> {
                        val tv =
                            intent.getParcelableExtra<TVEntity>(EXTRA_DATA) as TVEntity
                        val newState = !tv.favorite
                        if (newState) {
                            Toast.makeText(this, "Berhasil ditambahkan ke favorit!", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(
                                this,
                                "Dihapus dari favorit.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        viewModel.setTVFavorite(tv, newState)
                    }
                }
            }
        }
    }

    private fun getMovieData(movieID: Int) {
        viewModel.setDataMovie(movieID).observe(this, {
            when (it.status) {
                Status.LOADING -> progressBarLoading(true)
                Status.SUCCESS -> {
                    if (it.data != null) {
                        progressBarLoading(false)
                        populateDetailMovie(it.data)
                    }
                }
                Status.ERROR -> {
                    progressBarLoading(false)
                }
            }
        })
    }

    private fun getTVData(tvShowID: Int) {
        viewModel.setDataTV(tvShowID).observe(this, {
            when (it.status) {
                Status.LOADING -> progressBarLoading(true)
                Status.SUCCESS -> {
                    if (it.data != null) {
                        progressBarLoading(false)
                        populateDetailTV(it.data)
                    }
                }
                Status.ERROR -> {
                    progressBarLoading(false)
                }
            }
        })
    }

    private fun populateDetailMovie(movieDetail: DetailEntity) {

        if (supportActionBar != null) title = movieDetail.title

        val genre = movieDetail.genres.toString()
            .replace("[", "")
            .replace("]", "")

        detailContentBinding.apply {
            tvTitleDetail.text = movieDetail.title
            tvReleaseDate.text = movieDetail.date
            tvDesc.text = movieDetail.desc
            tvGenreDetail.text = genre
            tvRateDetail.text = movieDetail.rate.toString()

            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500" + movieDetail.image)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(ivPosterDetail)
        }
    }

    private fun populateDetailTV(detailTV: DetailEntity) {

        if (supportActionBar != null) title = detailTV.title

        val genre = detailTV.genres.toString()
            .replace("[", "")
            .replace("]", "")

        detailContentBinding.apply {
            tvTitleDetail.text = detailTV.title
            tvReleaseDate.text = detailTV.date
            tvDesc.text = detailTV.desc
            tvGenreDetail.text = genre
            tvRateDetail.text = detailTV.rate.toString()

            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500" + detailTV.image)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(ivPosterDetail)
        }
    }

    private fun progressBarLoading(value: Boolean) {
        detailActivityBinding.progressBar.visibility = if (value) View.VISIBLE else View.GONE
    }
}