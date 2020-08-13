package com.example.githubperson.ui.detail_ui.followtab_ui

import com.example.githubperson.data.model.UsersItems
import com.example.githubperson.data.repository.impl.FollowTabRepositoryImpl
import io.reactivex.Observable
import javax.inject.Inject

class FollowTabInteractor@Inject constructor(
    private val repositoryImpl: FollowTabRepositoryImpl
): FollowTabContract.Interactor {

    override fun getFollowers(username: String): Observable<MutableList<UsersItems>> {
        return repositoryImpl.getFollowers(username)
    }

    override fun getFollowing(username: String): Observable<MutableList<UsersItems>> {
        return repositoryImpl.getFollowing(username)
    }
}