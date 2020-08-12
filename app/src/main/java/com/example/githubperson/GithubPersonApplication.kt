package com.example.githubperson

import com.example.githubperson.di.component.DaggerAppComponent
import com.orhanobut.hawk.Hawk
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class GithubPersonApplication : DaggerApplication(){

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}