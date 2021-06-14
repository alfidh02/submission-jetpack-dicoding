package com.alfidh02.tvmov.view.favorite.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alfidh02.tvmov.databinding.FragmentTvFavoriteBinding
import com.alfidh02.tvmov.view.detail.DetailActivity
import com.alfidh02.tvmov.viewmodel.factory.ViewModelFactory
import com.alfidh02.tvmov.viewmodel.favorite.FavoriteViewModel

class TVFavoriteFragment : Fragment(), TVFavoriteAdapter.OnItemClickCallback {

    private lateinit var binding: FragmentTvFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var tvFavAdapter: TVFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            FragmentTvFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding.rvTvFav)

        if (activity != null) {

            progressBarLoading(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            tvFavAdapter = TVFavoriteAdapter()

            viewModel.getFavListTV().observe(viewLifecycleOwner, {
                progressBarLoading(false)
                with(tvFavAdapter) {
                    submitList(it)
                    setOnItemClickCallback(this@TVFavoriteFragment)
                }
            })

            with(binding.rvTvFav) {
                layoutManager = LinearLayoutManager(context)
                adapter = tvFavAdapter
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
        ): Int = makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipePosition = viewHolder.bindingAdapterPosition
                val tvShowEntity = tvFavAdapter.getSwipedItem(swipePosition)
                tvShowEntity?.let {
                    viewModel.setFavListTV(it)
                }
            }
        }

    })

    private fun progressBarLoading(value: Boolean) {
        binding.progressBar.visibility = if (value) View.VISIBLE else View.GONE
    }

    override fun onItemClicked(id: Int) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_DATA, id)
            it.putExtra(DetailActivity.EXTRA_CHOICE, "TV_SHOW")
            context?.startActivity(it)
        }
    }
}