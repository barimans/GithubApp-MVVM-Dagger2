package com.example.githubperson.data.repository.impl

import com.example.githubperson.data.model.UsersItems
import com.example.githubperson.data.remote.RemoteRepository
import com.example.githubperson.data.repository.UsersRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteRepository
): UsersRepository{

    override fun searchUsers(search: String): Observable<List<UsersItems>> {
        return remoteSource.searchUsers(search).map { it.items }
    }
}