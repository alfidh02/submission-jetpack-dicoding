package com.alfidh02.tvmov.view.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alfidh02.tvmov.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favoritePagerAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = favoritePagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        supportActionBar?.elevation = 0f
    }
}