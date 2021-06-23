package com.submissionalfi3.tvmov.view.favorite.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.submissionalfi3.tvmov.R
import com.submissionalfi3.tvmov.databinding.ItemMovieBinding
import com.submissionalfi3.tvmov.model.data.local.entities.MovieEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class MovieFavoriteAdapter : PagedListAdapter<MovieEntity, MovieFavoriteAdapter.MovieFavViewHolder>(
    DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class MovieFavViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(movie: MovieEntity) {
                binding.apply {
                    Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w500" + movie.image)
                        .transform(RoundedCorners(20))
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error)
                        )
                        .into(ivPosterImage)

                    tvTitleTv.text = movie.title
                    tvDateTv.text = movie.date
                    tvRateTv.text = movie.rate.toString()

                    itemView.setOnClickListener {
                        onItemClickCallback.onItemClicked(movie)
                    }
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieFavViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieFavViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) holder.bind(movie)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: MovieEntity)
    }
}