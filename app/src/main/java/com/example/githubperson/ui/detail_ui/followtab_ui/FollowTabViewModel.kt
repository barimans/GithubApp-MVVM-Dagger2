package com.example.githubperson.ui.detail_ui.followtab_ui

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubperson.data.model.UsersItems
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FollowTabViewModel @Inject constructor(
    private val interactor: FollowTabInteractor
): ViewModel(), FollowTabContract.ViewModel {

    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()
    val listDataFollow = MutableLiveData<MutableList<UsersItems>>()

    @SuppressLint("CheckResult")
    override fun getFollowers(username: String) {
        isLoading.postValue(true)
        interactor.getFollowers(username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                isLoading.postValue(false)
                listDataFollow.postValue(result)
            },{ error ->
                isLoading.postValue(false)
                isError.postValue(error)
            })
    }

    @SuppressLint("CheckResult")
    override fun getFollowing(username: String) {
        isLoading.postValue(true)
        interactor.getFollowing(username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                isLoading.postValue(false)
                listDataFollow.postValue(result)
            },{ error ->
                isLoading.postValue(false)
                isError.postValue(error)
            })
    }
}