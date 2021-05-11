package com.project.alfidh02.jetpack.tvmov.view.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.alfidh02.jetpack.tvmov.R
import com.project.alfidh02.jetpack.tvmov.databinding.ItemsMovieBinding
import com.project.alfidh02.jetpack.tvmov.model.data.MovieTVEntity

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private val listMovie = ArrayList<MovieTVEntity>()

    fun setMovie(movies: List<MovieTVEntity>?) {
        if (movies == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movies)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MoviesViewHolder {
        val view = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MoviesViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

    class MoviesViewHolder(private val binding: ItemsMovieBinding) :
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