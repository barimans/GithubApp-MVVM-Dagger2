package com.example.githubperson.ui.detail_ui

import com.example.githubperson.data.db.entity.UsersFavEntity
import com.example.githubperson.data.repository.impl.DetailUsersRepositoryImpl
import io.reactivex.Observable
import javax.inject.Inject

class DetailUsersInteractor @Inject constructor(
    private val repositoryImpl: DetailUsersRepositoryImpl
): DetailUsersContract.Interactor{

    override fun getDetailUsers(username: String): Observable<UsersFavEntity> {
        return repositoryImpl.getDetailUsers(username)
    }
}