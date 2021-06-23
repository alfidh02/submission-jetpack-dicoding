package com.submissionalfi3.tvmov.view.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submissionalfi3.tvmov.R
import com.submissionalfi3.tvmov.databinding.ItemMovieTvBinding
import com.submissionalfi3.tvmov.model.data.local.entities.MovieEntity

class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMovieTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) holder.bind(movies)
    }

    inner class MovieViewHolder(private val binding: ItemMovieTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(ivPoster)

                tvTitle.text = movie.title

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(movie)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: MovieEntity)
    }

}