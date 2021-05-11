package com.project.alfidh02.jetpack.tvmov.view.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.alfidh02.jetpack.tvmov.R
import com.project.alfidh02.jetpack.tvmov.databinding.ItemsMovieTvBinding
import com.project.alfidh02.jetpack.tvmov.model.data.MovieTVEntity

class TVAdapter : RecyclerView.Adapter<TVAdapter.MoviesViewHolder>() {
    private val listMovie = ArrayList<MovieTVEntity>()

    fun setMovie(movies: List<MovieTVEntity>?) {
        if (movies == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movies)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        val view = ItemsMovieTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TVAdapter.MoviesViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

    class MoviesViewHolder(private val binding: ItemsMovieTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieTVEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(movie.pic)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(ivPosterImage)

                tvTitle.text = movie.title
                tvDate.text = movie.date
                tvGenre.text = movie.genre

//                itemView.setOnClickListener {
//                    Intent(itemView.context, DetailFilmActivity::class.java).also {
//                        it.putExtra(DetailFilmActivity.EXTRAS_DETAIL, movie.title)
//                        itemView.context.startActivity(it)
//                    }
//                }
            }
        }
    }
}