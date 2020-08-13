package com.example.githubperson.data.remote

import com.example.githubperson.data.db.entity.UsersFavEntity
import com.example.githubperson.data.model.ResponseSearchUsers
import com.example.githubperson.data.model.UsersItems
import io.reactivex.Observable

interface RemoteDataSource {

    fun searchUsers(search: String): Observable<ResponseSearchUsers>

    fun getDetailUsers(usersname: String): Observable<UsersFavEntity>

    fun getFollowersUsers(usersname: String): Observable<MutableList<UsersItems>>

    fun getFollowingUsers(usersname: String): Observable<MutableList<UsersItems>>

}