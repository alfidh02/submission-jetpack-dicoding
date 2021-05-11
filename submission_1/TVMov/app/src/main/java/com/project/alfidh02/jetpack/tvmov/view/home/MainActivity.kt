package com.project.alfidh02.jetpack.tvmov.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.alfidh02.jetpack.tvmov.R
import com.project.alfidh02.jetpack.tvmov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityMainBinding.viewPager.adapter = sectionsPagerAdapter
        activityMainBinding.tabs.setupWithViewPager(activityMainBinding.viewPager)

        supportActionBar?.elevation = 0f
    }
}