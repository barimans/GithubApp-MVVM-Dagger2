package com.example.favorito.ui

import android.content.Context
import com.example.favorito.data.UsersFavEntity

interface MainContract {

    interface Presenter{
        fun getListFavoriteUsers(context: Context)
    }

    interface View{
        fun observeFavoriteUsers(dataFavorite: List<UsersFavEntity>)

        fun observeError(error: Throwable)

        fun observeLoading(isLoading: Boolean)
    }
}