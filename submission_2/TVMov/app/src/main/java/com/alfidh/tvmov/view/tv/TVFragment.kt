package com.alfidh.tvmov.view.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfidh.tvmov.databinding.FragmentTvBinding
import com.alfidh.tvmov.view.detail.DetailActivity
import com.alfidh.tvmov.viewmodel.detail.DetailViewModel
import com.alfidh.tvmov.viewmodel.factory.ViewModelFactory
import com.alfidh.tvmov.viewmodel.tv.TVViewModel

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
                progressBarLoading(false)
                tvAdapter.apply {
                    setTVShow(tvs)
                    notifyDataSetChanged()
                    setOnItemClickCallback(this@TVFragment)
                }
                setRecyclerView()
            })
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

    override fun onItemClicked(id: String) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_DATA, id)
            it.putExtra(DetailActivity.EXTRA_CHOICE, DetailViewModel.TV)
            context?.startActivity(it)
        }
    }
}