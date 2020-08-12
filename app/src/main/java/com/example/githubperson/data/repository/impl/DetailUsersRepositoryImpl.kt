package com.example.githubperson.data.repository.impl

import com.example.githubperson.data.db.entity.UsersFavEntity
import com.example.githubperson.data.remote.RemoteRepository
import com.example.githubperson.data.repository.DetailUsersRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailUsersRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteRepository
): DetailUsersRepository{

    override fun getDetailUsers(username: String): Observable<UsersFavEntity> {
        return remoteSource.getDetailUsers(username)
    }
}