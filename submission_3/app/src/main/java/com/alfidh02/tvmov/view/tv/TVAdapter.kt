package com.alfidh02.tvmov.view.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alfidh02.tvmov.R
import com.alfidh02.tvmov.databinding.ItemMovieBinding
import com.alfidh02.tvmov.model.data.entity.TVEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TVAdapter : PagedListAdapter<TVEntity, TVAdapter.TVViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVEntity>() {
            override fun areItemsTheSame(oldItem: TVEntity, newItem: TVEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TVEntity, newItem: TVEntity): Boolean =
                oldItem == newItem
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVViewHolder(view)
    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) holder.bind(tvShow)
    }

    inner class TVViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TVEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + tv.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(ivPosterMovieImage)

                tvTitleMovie.text = tv.title
                tvDateMovie.text = tv.date
                tvRateMovie.text = tv.rate.toString()

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(tv.id)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: Int)
    }

}