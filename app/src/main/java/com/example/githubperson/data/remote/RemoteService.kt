package com.example.githubperson.data.remote;

import com.example.githubperson.data.db.entity.UsersFavEntity
import com.example.githubperson.data.model.ResponseSearchUsers
import com.example.githubperson.data.model.UsersItems
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface RemoteService {

    @GET("search/users")
    fun searchUsers(@Query("q") search: String): Observable<ResponseSearchUsers>

    @GET("users/{username}")
    fun getDetailUsers(@Path("username") username: String): Observable<UsersFavEntity>

    @GET("users/{username}/followers")
    fun getFollowersUsers(@Path("username") username: String): Observable<MutableList<UsersItems>>

    @GET("users/{username}/following")
    fun getFollowingUsers(@Path("username") username: String): Observable<MutableList<UsersItems>>

    companion object {
        fun create(): RemoteService {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val builder = OkHttpClient().newBuilder()
            builder.readTimeout(30, TimeUnit.SECONDS)
            builder.connectTimeout(30, TimeUnit.SECONDS)
            builder.writeTimeout(30, TimeUnit.SECONDS)
            builder.addInterceptor(httpLoggingInterceptor)
            builder.addInterceptor(CustomInterceptor())

            val client = builder.build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(RemoteService::class.java)
        }
    }

    class CustomInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val builder = request.newBuilder()
            builder.addHeader("Authorization", "token b46719a108205d808eecb0edb415376f19381128")
            builder.addHeader("Content-Type", "application/json")

            return chain.proceed(builder.build())
        }

    }
}