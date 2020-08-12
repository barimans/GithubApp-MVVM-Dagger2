package com.example.githubperson.data.repository

import com.example.githubperson.data.model.UsersItems
import io.reactivex.Observable

interface UsersRepository {

    fun searchUsers(search: String) : Observable<List<UsersItems>>
}