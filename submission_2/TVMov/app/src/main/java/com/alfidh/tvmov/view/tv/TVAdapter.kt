package com.alfidh.tvmov.view.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfidh.tvmov.R
import com.alfidh.tvmov.databinding.ItemMovieBinding
import com.alfidh.tvmov.model.data.entity.TVEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TVAdapter : RecyclerView.Adapter<TVAdapter.MovieViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private var listTV = ArrayList<TVEntity>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setTVShow(tvShow: List<TVEntity>?) {
        if (tvShow.isNullOrEmpty()) return
        this.listTV.clear()
        this.listTV.addAll(tvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(tv = listTV[position])

    override fun getItemCount(): Int = listTV.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TVEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + tv.pic)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(ivPosterMovieImage)

                tvTitleMovie.text = tv.title
                tvDateMovie.text = tv.date
                tvRateMovie.text = tv.rate.toString()

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