package com.submissionalfi3.tvmov.view.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.submissionalfi3.tvmov.databinding.FragmentTvBinding
import com.submissionalfi3.tvmov.view.detail.DetailActivity
import com.submissionalfi3.tvmov.viewmodel.factory.ViewModelFactory
import com.submissionalfi3.tvmov.viewmodel.tv.TVViewModel
import com.submissionalfi3.tvmov.testutil.vo.Status

class TVFragment : Fragment(), TVAdapter.OnItemClickCallback {

    private lateinit var fragmentTvBinding: FragmentTvBinding
    private lateinit var tvAdapter: TVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            progressBarLoading(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TVViewModel::class.java]
            tvAdapter = TVAdapter()

            viewModel.getListTV().observe(viewLifecycleOwner, { tvs ->
                if (tvs != null) {
                    when (tvs.status) {
                        Status.LOADING -> progressBarLoading(true)
                        Status.SUCCESS -> {
                            progressBarLoading(false)
                            with(tvAdapter) {
                                submitList(tvs.data)
                                setOnItemClickCallback(this@TVFragment)
                            }
                        }
                        Status.ERROR -> {
                            progressBarLoading(false)
                            Toast.makeText(context, "Data tidak berhasil dimuat!", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
            setRecyclerView()
        }
    }

    private fun setRecyclerView() {
        fragmentTvBinding.rvTv.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvAdapter

        }
    }

    private fun progressBarLoading(value: Boolean) {
        fragmentTvBinding.progressBar.visibility = if (value) View.VISIBLE else View.GONE
    }

    override fun onItemClicked(id: Int) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_DATA, id)
            it.putExtra(DetailActivity.EXTRA_CHOICE, "TV_SHOW")
            context?.startActivity(it)
        }
    }
}