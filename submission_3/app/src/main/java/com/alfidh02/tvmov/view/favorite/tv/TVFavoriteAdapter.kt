package com.alfidh02.tvmov.view.favorite.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alfidh02.tvmov.R
import com.alfidh02.tvmov.databinding.ItemTvBinding
import com.alfidh02.tvmov.model.data.local.entity.TVEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class TVFavoriteAdapter :
    PagedListAdapter<TVEntity, TVFavoriteAdapter.TVFavViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVEntity>() {
            override fun areItemsTheSame(oldItem: TVEntity, newItem: TVEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVEntity, newItem: TVEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getSwipedItem(swipedPosition: Int): TVEntity? = getItem(swipedPosition)

    inner class TVFavViewHolder(private val binding: ItemTvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TVEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + tvShow.image)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivPosterImage)

                tvTitleTv.text = tvShow.title
                tvDateTv.text = tvShow.date
                tvRateTv.text = tvShow.rate.toString()

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(tvShow.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVFavViewHolder {
        val view = ItemTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVFavViewHolder(view)
    }

    override fun onBindViewHolder(holder: TVFavViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) holder.bind(tvShow)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: Int)
    }
}