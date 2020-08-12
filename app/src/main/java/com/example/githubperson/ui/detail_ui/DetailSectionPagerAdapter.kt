package com.example.githubperson.ui.detail_ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.githubperson.R
import com.example.githubperson.ui.detail_ui.followtab_ui.FollowTabFragment

class DetailSectionPagerAdapter(
    fm: FragmentManager,
    context: Context,
    private val username: String
): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val tabTitles = listOf(
        context.getString(R.string.followers),
        context.getString(R.string.following)
    )

    override fun getItem(position: Int): Fragment =
        FollowTabFragment.newInstance(position, username)

    override fun getPageTitle(position: Int): CharSequence? =
        tabTitles[position]

    override fun getCount(): Int =
        tabTitles.size
}