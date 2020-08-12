package com.example.githubperson.ui.main_ui

import com.example.githubperson.data.model.UsersItems
import io.reactivex.Observable

interface MainUsersContract {

    interface View{
        fun observeLoading(isLoading: Boolean?)

        fun observeError(error: Throwable?)

        fun observeSearchUsers(users: List<UsersItems>)
    }

    interface ViewModel{
        fun searchUsers(search: String)
    }

    interface Interactor{
        fun searchUsers(search: String): Observable<List<UsersItems>>
    }
}