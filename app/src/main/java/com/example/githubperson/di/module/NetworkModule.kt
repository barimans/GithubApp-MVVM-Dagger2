package com.example.githubperson.di.module

import com.example.githubperson.data.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(): RemoteRepository = RemoteRepository.getInstance()
}