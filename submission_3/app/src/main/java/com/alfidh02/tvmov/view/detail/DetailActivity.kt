package com.alfidh02.tvmov.view.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alfidh02.tvmov.R
import com.alfidh02.tvmov.databinding.ActivityDetailBinding
import com.alfidh02.tvmov.databinding.ContentDetailMovieTvBinding
import com.alfidh02.tvmov.model.data.entity.MovieEntity
import com.alfidh02.tvmov.model.data.entity.TVEntity
import com.alfidh02.tvmov.viewmodel.detail.DetailViewModel
import com.alfidh02.tvmov.viewmodel.factory.ViewModelFactory
import com.alfidh02.tvmov.vo.Status
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
                    Toast.makeText(applicationContext, "Data tidak berhasil dimuat", Toast.LENGTH_LONG)
                        .show()
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
                    Toast.makeText(applicationContext, "Data tidak berhasil dimuat", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    private fun populateDetailMovie(detail: MovieEntity) {

        if (supportActionBar != null) title = detail.title

        detailContentBinding.apply {
            tvTitleDetail.text = detail.title
            tvReleaseDate.text = detail.date
            tvDesc.text = detail.desc
            tvRateDetail.text = detail.rate.toString()
            btnFav.isChecked = detail.addFav

            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500" + detail.image)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(ivPosterDetail)
        }
    }

    private fun populateDetailTV(detail: TVEntity) {

        if (supportActionBar != null) title = detail.title

        detailContentBinding.apply {
            tvTitleDetail.text = detail.title
            tvReleaseDate.text = detail.date
            tvDesc.text = detail.desc
            tvRateDetail.text = detail.rate.toString()
            btnFav.isChecked = detail.addFav

            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500" + detail.image)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(ivPosterDetail)
        }
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

    private fun progressBarLoading(value: Boolean) {
        detailActivityBinding.progressBar.visibility = if (value) View.VISIBLE else View.GONE
    }
}