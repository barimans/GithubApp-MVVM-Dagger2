package com.example.githubperson.ui.main_ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubperson.data.model.UsersItems
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: MainUsersInteractor
): ViewModel(), MainUsersContract.ViewModel{
    private val compositeDisposable = CompositeDisposable()

    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()
    val users = MutableLiveData<List<UsersItems>>()

    override fun searchUsers(search: String) {
        isLoading.postValue(true)
        compositeDisposable.add(
        interactor.searchUsers(search)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                isLoading.postValue(false)
                users.postValue(result)
            },{ error ->
                isLoading.postValue(false)
                isError.postValue(error)
            })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}