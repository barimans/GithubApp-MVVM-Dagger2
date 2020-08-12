package com.example.githubperson.ui.favorite_ui

import android.content.Context
import com.example.githubperson.data.db.entity.UsersFavEntity

interface FavoriteContract {

    interface View{
        fun observeLoading(isLoading: Boolean?)

        fun observeError(error: Throwable?)

        fun observeFavoriteUsers(favoriteUsers: MutableList<UsersFavEntity>)
    }

    interface Interactor{}

    interface ViewModel{
        fun getListFavoriteUsers(context: Context)
    }
}