package com.example.githubperson.data.repository

import com.example.githubperson.data.model.UsersItems
import io.reactivex.Observable

interface FollowTabRepository {

    fun getFollowers(username: String): Observable<MutableList<UsersItems>>

    fun getFollowing(username: String): Observable<MutableList<UsersItems>>
}