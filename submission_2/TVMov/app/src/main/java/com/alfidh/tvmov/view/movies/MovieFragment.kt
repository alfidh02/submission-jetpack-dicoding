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
            true.progressBar()

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            movieAdapter = MovieAdapter()

            viewModel.getListMovie().observe(viewLifecycleOwner, { movies ->
                false.progressBar()
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

    private fun Boolean.progressBar() {
        fragmentMovieBinding.progressBar.visibility = if (this) View.VISIBLE else View.GONE
    }

    override fun onItemClicked(id: String) {
//        Intent(context, DetailActivity::class.java).also {
//            it.putExtra(DetailActivity.EXTRAS_DATA, id)
//            it.putExtra(DetailActivity.EXTRAS_CHOOSE, MOVIE)
//            context?.startActivity(it)
//        }
    }
}