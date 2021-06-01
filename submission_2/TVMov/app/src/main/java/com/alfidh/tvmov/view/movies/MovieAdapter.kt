package com.alfidh.tvmov.view.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfidh.tvmov.R
import com.alfidh.tvmov.databinding.ItemMovieBinding
import com.alfidh.tvmov.model.data.entity.MovieEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private var listMovie = ArrayList<MovieEntity>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies.isNullOrEmpty()) return
        this.listMovie.clear()
        this.listMovie.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movie = listMovie[position])

    override fun getItemCount(): Int = listMovie.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.pic)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(ivPosterMovieImage)

                tvTitleMovie.text = movie.title
                tvDateMovie.text = movie.date
                tvRateMovie.text = movie.rate.toString()

//                itemView.setOnClickListener {
//                    onItemClickCallback.onItemClicked(movie.id.toString())
//                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }

}