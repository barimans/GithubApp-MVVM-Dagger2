package com.example.githubperson.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubperson.di.ViewModelKey
import com.example.githubperson.di.factory.ViewModelFactory
import com.example.githubperson.ui.detail_ui.DetailViewModel
import com.example.githubperson.ui.detail_ui.followtab_ui.FollowTabViewModel
import com.example.githubperson.ui.favorite_ui.FavoriteViewModel
import com.example.githubperson.ui.main_ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun providesUsersViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun providesDetailUsersViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FollowTabViewModel::class)
    internal abstract fun providesFollowTabViewModel(viewModel: FollowTabViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    internal abstract fun providesFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel
}