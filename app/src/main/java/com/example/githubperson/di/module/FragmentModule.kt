package com.example.githubperson.di.module

import com.example.githubperson.ui.detail_ui.followtab_ui.FollowTabFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeFollowTabFragment(): FollowTabFragment
}