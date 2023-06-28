package com.dicoding.picodiploma.aplikasigithubuser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.picodiploma.aplikasigithubuser.databinding.ActivityDetailUserBinding


class DetailUser : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME="extra_username"
        const val EXTRA_URL = "extra_url"
    }
    private var isEdit = false
    private lateinit var detailUserViewModel: DetailUserViewModel
    private lateinit var binding : ActivityDetailUserBinding


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailUserBinding.inflate(layoutInflater)


        setContentView(binding.root)
        val user = intent.getStringExtra(EXTRA_USERNAME)
        val url = intent.getStringExtra(EXTRA_URL)

        showLoading(true)
        detailUserViewModel.setUserDetail(user.toString())
        detailUserViewModel.getUserDetail().observe(this) {user ->
            binding.apply {
                tvName.text = user?.name
                tvUsername.text = user?.login
                tvFollowers.text = "${user?.followers} Followers"
                tvFollowing.text = "${user?.following} Followings"
                Glide.with(this@DetailUser).load(user?.avatarUrl)
                    .transition(DrawableTransitionOptions.withCrossFade()).centerCrop()
                    .into(ivProfile)

                showLoading(false)
            }

        }

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
            binding.apply {
                viewPager.adapter = sectionPagerAdapter
                tabs.setupWithViewPager(viewPager)
            }


    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbBar.visibility = View.VISIBLE
        } else {
            binding.pbBar.visibility = View.GONE
        }
    }
}