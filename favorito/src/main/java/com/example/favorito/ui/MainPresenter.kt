package com.example.favorito.ui

import android.content.Context
import androidx.core.net.toUri
import com.example.favorito.utils.USER_CONTENT_URI
import com.example.favorito.utils.tranformListUserEntity

class MainPresenter(mView: MainContract.View) : MainContract.Presenter {

    private var view: MainContract.View? = null

    init {
        this.view = mView
    }

    override fun getListFavoriteUsers(context: Context) {
        view?.observeLoading(true)
        val cursor = context.contentResolver
            .query(USER_CONTENT_URI.toUri(), null, null, null, null)
        cursor?.let {
            try {
                view?.observeLoading(false)
                view?.observeFavoriteUsers(it.tranformListUserEntity())

                cursor.close()
            }catch (error: Throwable){
                view?.observeLoading(false)
                view?.observeError(error)
            }

        }
    }
}