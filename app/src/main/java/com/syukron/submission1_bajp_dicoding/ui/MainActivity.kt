package com.syukron.submission1_bajp_dicoding.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syukron.submission1_bajp_dicoding.adapter.SectionPagerAdapter
import com.syukron.submission1_bajp_dicoding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "BAJP Dicoding Movie Catalogue"

        binding.mainViewPager.adapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)
        supportActionBar?.elevation = 0f
    }
}