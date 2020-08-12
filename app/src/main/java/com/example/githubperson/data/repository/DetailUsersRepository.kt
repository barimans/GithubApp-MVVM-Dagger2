package com.example.githubperson.data.repository

import com.example.githubperson.data.db.entity.UsersFavEntity
import io.reactivex.Observable

interface DetailUsersRepository {

    fun getDetailUsers(username: String): Observable<UsersFavEntity>

}