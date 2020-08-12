package com.example.githubperson.ui.detail_ui

import android.content.Context
import com.example.githubperson.data.db.entity.UsersFavEntity
import io.reactivex.Observable

interface DetailUsersContract {

    interface View{

        fun observeLoading(isLoading: Boolean?)

        fun observeError(error: Throwable?)

        fun observeDetailUsers(detailUsers: UsersFavEntity)

        fun observeCheckFavoriteUsers(favUsers: UsersFavEntity?)

        fun observeAddDeleteFavorites(favUsers: Long)
    }

    interface ViewModel{

        fun getDetailUsers(username: String)

        fun checkFavoriteUser(userId: Int, context: Context)

        fun addFavoriteUser(usersFavEntity: UsersFavEntity, context: Context)

        fun deleteFavoriteUser(usersFavEntity: UsersFavEntity, context: Context)
    }

    interface Interactor{
        fun getDetailUsers(username: String): Observable<UsersFavEntity>
    }
}