package com.example.githubperson.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<V: ViewModel>: DaggerFragment() {

    @Inject
    lateinit var viewModelfactory: ViewModelProvider.Factory

    protected lateinit var mViewModel: V

    val viewModel: V get() = mViewModel

    abstract fun injectViewModel()
    abstract fun getViewModelClass(): Class<V>

    @LayoutRes
    abstract fun getLayoutResorceId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injectViewModel()
        return inflater.inflate(getLayoutResorceId(), container, false)
    }
}