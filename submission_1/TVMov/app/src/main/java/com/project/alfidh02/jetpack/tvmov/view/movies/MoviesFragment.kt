package com.project.alfidh02.jetpack.tvmov.view.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.alfidh02.jetpack.tvmov.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }
}