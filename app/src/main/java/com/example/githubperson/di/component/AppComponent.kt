package com.example.githubperson.di.component

import com.example.githubperson.GithubPersonApplication
import com.example.githubperson.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBuilder::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        FragmentModule::class
    ]
)

interface AppComponent : AndroidInjector<GithubPersonApplication>{

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: GithubPersonApplication): Builder

        fun build(): AppComponent
    }
}