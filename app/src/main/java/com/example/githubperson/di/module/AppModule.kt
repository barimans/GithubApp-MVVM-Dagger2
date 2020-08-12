package com.example.githubperson.di.module

import android.app.Application
import android.content.Context
import com.example.githubperson.GithubPersonApplication
import com.example.githubperson.data.db.UsersDatabase
import com.example.githubperson.data.remote.RemoteRepository
import com.example.githubperson.data.repository.impl.DetailUsersRepositoryImpl
import com.example.githubperson.data.repository.impl.FollowTabRepositoryImpl
import com.example.githubperson.data.repository.impl.UsersRepositoryImpl
import com.example.githubperson.ui.detail_ui.DetailUsersInteractor
import com.example.githubperson.ui.detail_ui.followtab_ui.FollowTabInteractor
import com.example.githubperson.ui.main_ui.MainUsersInteractor
import com.example.githubperson.utils.PreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(app: GithubPersonApplication): Context = app

    @Provides
    @Singleton
    fun provideApplication(app: GithubPersonApplication): Application = app

    @Provides
    @Singleton
    fun provideRoomDB(app: Application) = UsersDatabase.getInstance(app)

    @Provides
    @Singleton
    fun providePlayerDao(db: UsersDatabase) = db.userFavoriteDao()

    @Provides
    @Singleton
    fun providePreference() = PreferencesHelper.getInstance()

    @Provides
    @Singleton
    fun providesUsersRepository(remote: RemoteRepository) = UsersRepositoryImpl(remote)

    @Provides
    @Singleton
    fun providesDetailUsersRepository(remote: RemoteRepository) = DetailUsersRepositoryImpl(remote)

    @Provides
    @Singleton
    fun providesFollowTabRepository(remote: RemoteRepository) = FollowTabRepositoryImpl(remote)

    @Provides
    @Singleton
    fun providesUsersInteractor(repositoryImpl: UsersRepositoryImpl) = MainUsersInteractor(repositoryImpl)

    @Provides
    @Singleton
    fun providesDetailUsersInteractor(repositoryImpl: DetailUsersRepositoryImpl) = DetailUsersInteractor(repositoryImpl)

    @Provides
    @Singleton
    fun providesFollowTabInteractor(repositoryImpl: FollowTabRepositoryImpl) = FollowTabInteractor(repositoryImpl)

}