package com.example.githubperson.data.remote;

import com.example.githubperson.data.db.entity.UsersFavEntity
import com.example.githubperson.data.model.ResponseSearchUsers
import com.example.githubperson.data.model.UsersItems
import io.reactivex.Observable

class RemoteRepository : RemoteDataSource {

    private var apiService: RemoteService? = null

    init {
        apiService =
            RemoteService.create()
    }

    companion object {
        @Synchronized
        fun getInstance(): RemoteRepository {
            return RemoteRepository()
        }

    }

    override fun searchUsers(search: String): Observable<ResponseSearchUsers> {
        return apiService!!.searchUsers(search)
    }

    override fun getDetailUsers(usersname: String): Observable<UsersFavEntity> {
        return apiService!!.getDetailUsers(usersname)
    }

    override fun getFollowersUsers(usersname: String): Observable<MutableList<UsersItems>> {
        return apiService!!.getFollowersUsers(usersname)
    }

    override fun getFollowingUsers(usersname: String): Observable<MutableList<UsersItems>> {
        return apiService!!.getFollowingUsers(usersname)
    }
}