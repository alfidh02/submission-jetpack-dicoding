package com.alfidh.tvmov.view.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfidh.tvmov.databinding.FragmentMovieBinding
import com.alfidh.tvmov.view.detail.DetailActivity
import com.alfidh.tvmov.viewmodel.detail.DetailViewModel.Companion.MOVIE
import com.alfidh.tvmov.viewmodel.factory.ViewModelFactory
import com.alfidh.tvmov.viewmodel.movies.MovieViewModel

class MovieFragment : Fragment(), MovieAdapter.OnItemClickCallback {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            progressBarLoading(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            movieAdapter = MovieAdapter()

            viewModel.getListMovie().observe(viewLifecycleOwner, { movies ->
                progressBarLoading(false)
                movieAdapter.apply {
                    setMovies(movies)
                    notifyDataSetChanged()
                    setOnItemClickCallback(this@MovieFragment)
                }
                setRecyclerView()
            })
        }
    }

    private fun setRecyclerView() {
        fragmentMovieBinding.rvMovies.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter

        }
    }

    private fun progressBarLoading(value: Boolean) {
        fragmentMovieBinding.progressBar.visibility = if (value) View.VISIBLE else View.GONE
    }

    override fun onItemClicked(id: String) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_DATA, id)
            it.putExtra(DetailActivity.EXTRA_CHOICE, MOVIE)
            context?.startActivity(it)
        }
    }
}