package com.submissionalfi3.tvmov.view.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submissionalfi3.tvmov.R
import com.submissionalfi3.tvmov.databinding.ItemMovieTvBinding
import com.submissionalfi3.tvmov.model.data.local.entities.TVEntity

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
        val view = ItemMovieTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVViewHolder(view)
    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) holder.bind(tvShow)
    }

    inner class TVViewHolder(private val binding: ItemMovieTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TVEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + tv.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(ivPoster)

                tvTitle.text = tv.title

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(tv)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: TVEntity)
    }

}