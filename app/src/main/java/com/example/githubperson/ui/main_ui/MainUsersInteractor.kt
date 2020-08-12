package com.example.githubperson.ui.main_ui

import com.example.githubperson.data.model.UsersItems
import com.example.githubperson.data.repository.impl.UsersRepositoryImpl
import io.reactivex.Observable
import javax.inject.Inject

class MainUsersInteractor @Inject constructor(
    private val repository: UsersRepositoryImpl
): MainUsersContract.Interactor {

    override fun searchUsers(search: String): Observable<List<UsersItems>> {
        return repository.searchUsers(search)
    }
}