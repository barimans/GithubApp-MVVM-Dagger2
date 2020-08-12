package com.example.githubperson.ui.favorite_ui

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubperson.data.db.entity.UsersFavEntity
import com.example.githubperson.utils.USER_CONTENT_URI
import com.example.githubperson.utils.tranformListUserEntity
import javax.inject.Inject

class FavoriteViewModel @Inject constructor()
    : ViewModel(), FavoriteContract.ViewModel {

    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()
    val favoriteUsers = MutableLiveData<MutableList<UsersFavEntity>>()

    override fun getListFavoriteUsers(context: Context) {
        isLoading.postValue(true)
        val cursor = context.contentResolver
            .query(USER_CONTENT_URI.toUri(), null, null, null, null)
        cursor?.let {
            try {
                isLoading.postValue(false)
                favoriteUsers.postValue(it.tranformListUserEntity())

                cursor.close()
            }catch (error: Throwable){
                isLoading.postValue(false)
                isError.postValue(error)
            }

        }
    }
}