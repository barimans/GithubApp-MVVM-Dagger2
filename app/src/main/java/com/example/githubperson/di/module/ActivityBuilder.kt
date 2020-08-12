package com.example.githubperson.di.module

import com.example.githubperson.data.db.providers.UsersContentProvider
import com.example.githubperson.ui.detail_ui.DetailActivity
import com.example.githubperson.ui.favorite_ui.FavoriteActivity
import com.example.githubperson.ui.main_ui.MainActivity
import com.example.githubperson.ui.settings_ui.SettingsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesDetailAcivity(): DetailActivity

    @ContributesAndroidInjector
    abstract fun contributesFavoriteAcivity(): FavoriteActivity

    @ContributesAndroidInjector
    abstract fun contributesSettingsAcivity(): SettingsActivity

    @ContributesAndroidInjector
    abstract fun contributesUsersContentProvider(): UsersContentProvider
}