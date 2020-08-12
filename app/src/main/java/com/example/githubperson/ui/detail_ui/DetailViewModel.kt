package com.example.githubperson.ui.detail_ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubperson.data.db.entity.UsersFavEntity
import com.example.githubperson.utils.USER_CONTENT_URI
import com.example.githubperson.utils.transformContentValues
import com.example.githubperson.utils.transformUserFavEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val interactor: DetailUsersInteractor
) : ViewModel(), DetailUsersContract.ViewModel{

    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()
    val detailUsers = MutableLiveData<UsersFavEntity>()
    val favoriteUsers = MutableLiveData<UsersFavEntity>()
    val favUsers = MutableLiveData<Long>()


    @SuppressLint("CheckResult")
    override fun getDetailUsers(username: String) {
        isLoading.postValue(true)
        interactor.getDetailUsers(username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                isLoading.postValue(false)
                detailUsers.postValue(result)
            },{ error ->
                isLoading.postValue(false)
                isError.postValue(error)
            })
    }

    override fun checkFavoriteUser(userId: Int, context: Context) {
        val uri = "$USER_CONTENT_URI/$userId".toUri()
        val cursor = context.contentResolver
            .query(uri, null, null, null, null)

        cursor?.let {
            try {
                if (cursor.moveToFirst()) {
                    favoriteUsers.postValue(it.transformUserFavEntity())
                }else{
                    favoriteUsers.postValue(null)
                }
                cursor.close()
            }catch (error: Throwable){
                isError.postValue(error)
            }
        }

    }

    override fun addFavoriteUser(usersFavEntity: UsersFavEntity, context: Context) {
        val cursor = context.contentResolver
            .insert(USER_CONTENT_URI.toUri(), usersFavEntity.transformContentValues())

        cursor?.let { favUsers.postValue(1) }
    }

    override fun deleteFavoriteUser(usersFavEntity: UsersFavEntity, context: Context) {
        val uri = "$USER_CONTENT_URI/${usersFavEntity.id}".toUri()
        val cursor = context.contentResolver
            .delete(uri, null, null)

        cursor.let { favUsers.postValue(2) }
    }
}