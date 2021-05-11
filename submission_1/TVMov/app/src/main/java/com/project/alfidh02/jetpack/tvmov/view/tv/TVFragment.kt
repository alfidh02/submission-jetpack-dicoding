package com.project.alfidh02.jetpack.tvmov.view.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.alfidh02.jetpack.tvmov.databinding.FragmentTvBinding

class TVFragment : Fragment() {

    private lateinit var fragmentTvBinding: FragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return fragmentTvBinding.root
    }
}