package com.alfidh02.tvmov.view.favorite.movies

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
import com.alfidh02.tvmov.databinding.FragmentMovieFavoriteBinding
import com.alfidh02.tvmov.view.detail.DetailActivity
import com.alfidh02.tvmov.viewmodel.factory.ViewModelFactory
import com.alfidh02.tvmov.viewmodel.favorite.FavoriteViewModel

class MovieFavoriteFragment : Fragment(), MovieFavoriteAdapter.OnItemClickCallback{

    private lateinit var binding: FragmentMovieFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var movieFavoriteAdapter: MovieFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            FragmentMovieFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvMoviesFav)

        if (activity != null) {

            progressBarLoading(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            movieFavoriteAdapter = MovieFavoriteAdapter()

            viewModel.getFavListMovie().observe(viewLifecycleOwner, {
                progressBarLoading(false)
                with(movieFavoriteAdapter) {
                    submitList(it)
                    setOnItemClickCallback(this@MovieFavoriteFragment)
                }
            })

            binding.rvMoviesFav.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = movieFavoriteAdapter
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
                val movieEntity = movieFavoriteAdapter.getSwipedItem(swipePosition)
                movieEntity?.let {
                    viewModel.setFavListMovie(it)
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
            it.putExtra(DetailActivity.EXTRA_CHOICE, "MOVIE")
            context?.startActivity(it)
        }
    }
}