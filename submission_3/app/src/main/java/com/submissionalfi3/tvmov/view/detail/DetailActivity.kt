package com.submissionalfi3.tvmov.view.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.submissionalfi3.tvmov.R
import com.submissionalfi3.tvmov.databinding.ActivityDetailBinding
import com.submissionalfi3.tvmov.databinding.ContentDetailMovieTvBinding
import com.submissionalfi3.tvmov.model.data.local.entity.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entity.TVEntity
import com.submissionalfi3.tvmov.testutil.vo.Status
import com.submissionalfi3.tvmov.viewmodel.detail.DetailViewModel
import com.submissionalfi3.tvmov.viewmodel.factory.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

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

        val tvMovieId = intent.getIntExtra(EXTRA_DATA, 0)
        val tvMovieChoice = intent.getStringExtra(EXTRA_CHOICE)
        if (tvMovieId != 0 && tvMovieChoice != null) {
            when (tvMovieChoice) {
                "MOVIE" -> {
                    getMovieData(tvMovieId)
                }
                "TV_SHOW" -> {
                    getTVData(tvMovieId)
                }
            }
        }
        setFavorite()
    }

    private fun setFavorite() {
        val tvMovieChoose = intent.getStringExtra(EXTRA_CHOICE)
        if (tvMovieChoose != null) {
            detailContentBinding.btnFav.setOnClickListener {
                when (tvMovieChoose) {
                    "MOVIE" -> {
                        viewModel.setMovieFavorite()
                    }
                    "TV_SHOW" -> {
                        viewModel.setTVFavorite()
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

    private fun populateDetailMovie(movieDetail: MovieEntity) {

        if (supportActionBar != null) title = movieDetail.title

        detailContentBinding.apply {
            tvTitleDetail.text = movieDetail.title
            tvReleaseDate.text = movieDetail.date
            tvDesc.text = movieDetail.desc
            tvRateDetail.text = movieDetail.rate.toString()
            btnFav.isChecked = movieDetail.favorite

            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500" + movieDetail.image)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(ivPosterDetail)
        }
    }

    private fun populateDetailTV(detailTV: TVEntity) {

        if (supportActionBar != null) title = detailTV.title

        detailContentBinding.apply {
            tvTitleDetail.text = detailTV.title
            tvReleaseDate.text = detailTV.date
            tvDesc.text = detailTV.desc
            tvRateDetail.text = detailTV.rate.toString()
            btnFav.isChecked = detailTV.favorite

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