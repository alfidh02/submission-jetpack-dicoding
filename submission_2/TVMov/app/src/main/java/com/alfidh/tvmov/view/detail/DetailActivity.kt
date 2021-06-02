package com.alfidh.tvmov.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.alfidh.tvmov.R
import com.alfidh.tvmov.databinding.ActivityDetailBinding
import com.alfidh.tvmov.databinding.ContentDetailMovieTvBinding
import com.alfidh.tvmov.model.data.entity.DetailEntity
import com.alfidh.tvmov.viewmodel.detail.DetailViewModel
import com.alfidh.tvmov.viewmodel.factory.ViewModelFactory
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailActivityBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = detailActivityBinding.detailContent
        setContentView(detailActivityBinding.root)

        setSupportActionBar(detailActivityBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {

            val tvMovieID = extras.getString(EXTRA_DATA)
            val tvMovieChoice = extras.getString(EXTRA_CHOICE)

            if (tvMovieID != null && tvMovieChoice != null) {
                progressBarLoading(true)

                viewModel.setDetailList(tvMovieID, tvMovieChoice)
                viewModel.getDetailList().observe(this, { detail ->
                    progressBarLoading(false)
                    populateDetail(detail)
                })
            }
        }
    }

    private fun populateDetail(detail: DetailEntity) {

        if (supportActionBar != null) title = detail.title

        val genre = detail.genres.toString()
            .replace("[", "")
            .replace("]", "")

        detailContentBinding.apply {
            tvTitleDetail.text = detail.title
            tvReleaseDate.text = detail.date
            tvGenreDetail.text = genre
            tvDesc.text = detail.desc
            tvRateDetail.text = detail.rate.toString()

            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500" + detail.pic)
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