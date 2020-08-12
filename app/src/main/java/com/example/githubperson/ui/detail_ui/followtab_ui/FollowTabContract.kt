package com.example.githubperson.ui.detail_ui.followtab_ui

import com.example.githubperson.data.model.UsersItems
import io.reactivex.Observable

interface FollowTabContract {

    interface View{
        fun observeLoading(isLoading: Boolean?)

        fun observeError(error: Throwable?)

        fun observeDataFollow(listData: MutableList<UsersItems>)
    }

    interface ViewModel{
        fun getFollowers(username: String)

        fun getFollowing(username: String)
    }

    interface Interactor{
        fun getFollowers(username: String): Observable<MutableList<UsersItems>>

        fun getFollowing(username: String): Observable<MutableList<UsersItems>>
    }
}