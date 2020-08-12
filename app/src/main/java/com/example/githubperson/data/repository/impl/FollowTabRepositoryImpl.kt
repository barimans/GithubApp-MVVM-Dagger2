package com.example.githubperson.data.repository.impl

import com.example.githubperson.data.model.UsersItems
import com.example.githubperson.data.remote.RemoteRepository
import com.example.githubperson.data.repository.FollowTabRepository
import io.reactivex.Observable
import javax.inject.Inject

class FollowTabRepositoryImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
): FollowTabRepository {

    override fun getFollowers(username: String): Observable<MutableList<UsersItems>> {
        return remoteRepository.getFollowersUsers(username)
    }

    override fun getFollowing(username: String): Observable<MutableList<UsersItems>> {
        return remoteRepository.getFollowingUsers(username)
    }
}