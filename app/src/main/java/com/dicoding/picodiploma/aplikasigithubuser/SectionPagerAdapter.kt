package com.dicoding.picodiploma.aplikasigithubuser

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionPagerAdapter(private val mCtx: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TITLES = intArrayOf(R.string.Following, R.string.Follower)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var frag: Fragment? = null
        when (position) {
            0 -> frag = FollowingFragment()
            1 -> frag = FollowerFragment()
        }
        return frag as Fragment
    }
    override fun getPageTitle(position: Int): CharSequence? {
            return mCtx.resources.getString(TITLES[position])
    }
}