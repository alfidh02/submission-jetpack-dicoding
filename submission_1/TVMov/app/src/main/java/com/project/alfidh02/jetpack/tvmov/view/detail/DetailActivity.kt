package com.project.alfidh02.jetpack.tvmov.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.project.alfidh02.jetpack.tvmov.R
import com.project.alfidh02.jetpack.tvmov.databinding.ActivityDetailBinding
import com.project.alfidh02.jetpack.tvmov.databinding.ContentDetailMovieTvBinding
import com.project.alfidh02.jetpack.tvmov.model.data.MovieTVEntity
import com.project.alfidh02.jetpack.tvmov.viewmodel.detail.DetailViewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_TV = "extra_movie_tv"
    }

    private lateinit var detailContentBinding: ContentDetailMovieTvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailBinding.detailContent
        setContentView(activityDetailBinding.root)

        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieTitle = extras.getString(EXTRA_MOVIE_TV)
            if (movieTitle != null) {
                detailViewModel.setSelectedMovieTv(movieTitle)
                populateDetail(detailViewModel.getSelectedMovieTv())
            }
        }
    }

    private fun populateDetail(selectedMovieTv: MovieTVEntity) {

        Glide.with(this)
            .load(selectedMovieTv.pic)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(detailContentBinding.ivPosterDetail)

        with(detailContentBinding) {
            tvTitle.text = selectedMovieTv.title
            tvGenre.text = selectedMovieTv.genre
            tvReleaseDate.text = selectedMovieTv.date
            tvDesc.text = selectedMovieTv.desc
        }
    }
}