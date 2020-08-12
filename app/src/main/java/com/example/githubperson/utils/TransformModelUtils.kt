package com.example.githubperson.utils

import android.content.ContentValues
import android.database.Cursor
import com.example.githubperson.data.db.entity.UsersFavEntity
import java.util.ArrayList

fun ContentValues.transformUserFavEntity(): UsersFavEntity{
    return UsersFavEntity(
        id = getAsInteger(USER_ID),
        gistsUrl = getAsString(USER_GISTS_URL),
        reposUrl = getAsString(USER_REPOS_URL),
        followingUrl = getAsString(USER_FOLLOWING_URL),
        twitterUsername = getAsString(USER_TWITTER_USERNAME),
        bio = getAsString(USER_BIO),
        createdAt = getAsString(USER_CREATED_AT),
        login = getAsString(USER_LOGIN),
        type = getAsString(USER_TYPE),
        blog = getAsString(USER_BLOG),
        subscriptionsUrl = getAsString(USER_SUBSCRIPTION_URL),
        updatedAt = getAsString(USER_UPDATED_AT),
        siteAdmin = getAsBoolean(USER_SITE_ADMIN),
        company = getAsString(USER_COMPANY),
        publicRepos = getAsInteger(USER_PUBLIC_REPOS),
        gravatarId = getAsString(USER_GRAVATAR_ID),
        email = getAsString(USER_EMAIL),
        organizationsUrl = getAsString(USER_ORGANIZATION_URL),
        hireable = getAsString(USER_HIREABLE),
        starredUrl = getAsString(USER_STARRED_URL),
        followersUrl = getAsString(USER_FOLLOWERS_URL),
        publicGists = getAsInteger(USER_PUBLIC_GISTS),
        url = getAsString(USER_URL),
        receivedEventsUrl = getAsString(USER_RECEIVED_EVENTS_URL),
        followers = getAsInteger(USER_FOLLOWERS),
        avatarUrl = getAsString(USER_AVATAR_URL),
        eventsUrl = getAsString(USER_EVENTS_URL),
        htmlUrl = getAsString(USER_HTML_URL),
        following = getAsInteger(USER_FOLLOWING),
        name = getAsString(USER_NAME),
        location = getAsString(USER_LOCATION),
        nodeId = getAsString(USER_NODE_ID)
    )
}

fun UsersFavEntity.transformContentValues(): ContentValues{
    return ContentValues().apply {
        put(USER_ID, id)
        put(USER_GISTS_URL, gistsUrl)
        put(USER_REPOS_URL, reposUrl)
        put(USER_FOLLOWING_URL, followingUrl)
        put(USER_TWITTER_USERNAME, twitterUsername)
        put(USER_BIO, bio)
        put(USER_CREATED_AT, createdAt)
        put(USER_LOGIN, login)
        put(USER_TYPE, type)
        put(USER_BLOG, blog)
        put(USER_SUBSCRIPTION_URL, subscriptionsUrl)
        put(USER_UPDATED_AT, updatedAt)
        put(USER_SITE_ADMIN, siteAdmin)
        put(USER_COMPANY, company)
        put(USER_PUBLIC_REPOS, publicRepos)
        put(USER_GRAVATAR_ID, gravatarId)
        put(USER_EMAIL, email)
        put(USER_ORGANIZATION_URL, organizationsUrl)
        put(USER_HIREABLE, hireable)
        put(USER_STARRED_URL, starredUrl)
        put(USER_FOLLOWERS_URL, followersUrl)
        put(USER_PUBLIC_GISTS, publicGists)
        put(USER_URL, url)
        put(USER_RECEIVED_EVENTS_URL, receivedEventsUrl)
        put(USER_FOLLOWERS, followers)
        put(USER_AVATAR_URL, avatarUrl)
        put(USER_EVENTS_URL, eventsUrl)
        put(USER_HTML_URL, htmlUrl)
        put(USER_FOLLOWING, following)
        put(USER_LOCATION, location)
        put(USER_NAME, name)
        put(USER_NODE_ID, nodeId)
    }
}

fun Cursor.transformUserFavEntity(): UsersFavEntity {
    return  UsersFavEntity(
        getInt(getColumnIndexOrThrow(USER_ID)),
        getString(getColumnIndexOrThrow(USER_GISTS_URL)),
        getString(getColumnIndexOrThrow(USER_REPOS_URL)),
        getString(getColumnIndexOrThrow(USER_FOLLOWING_URL)),
        getString(getColumnIndexOrThrow(USER_TWITTER_USERNAME)),
        getString(getColumnIndexOrThrow(USER_BIO)),
        getString(getColumnIndexOrThrow(USER_CREATED_AT)),
        getString(getColumnIndexOrThrow(USER_LOGIN)),
        getString(getColumnIndexOrThrow(USER_TYPE)),
        getString(getColumnIndexOrThrow(USER_BLOG)),
        getString(getColumnIndexOrThrow(USER_SUBSCRIPTION_URL)),
        getString(getColumnIndexOrThrow(USER_UPDATED_AT)),
        (getInt(getColumnIndexOrThrow(USER_SITE_ADMIN)) > 0),
        getString(getColumnIndexOrThrow(USER_COMPANY)),
        getInt(getColumnIndexOrThrow(USER_PUBLIC_REPOS)),
        getString(getColumnIndexOrThrow(USER_GRAVATAR_ID)),
        getString(getColumnIndexOrThrow(USER_EMAIL)),
        getString(getColumnIndexOrThrow(USER_ORGANIZATION_URL)),
        getString(getColumnIndexOrThrow(USER_HIREABLE)),
        getString(getColumnIndexOrThrow(USER_STARRED_URL)),
        getString(getColumnIndexOrThrow(USER_FOLLOWERS_URL)),
        getInt(getColumnIndexOrThrow(USER_PUBLIC_GISTS)),
        getString(getColumnIndexOrThrow(USER_URL)),
        getString(getColumnIndexOrThrow(USER_RECEIVED_EVENTS_URL)),
        getInt(getColumnIndexOrThrow(USER_FOLLOWERS)),
        getString(getColumnIndexOrThrow(USER_AVATAR_URL)),
        getString(getColumnIndexOrThrow(USER_EVENTS_URL)),
        getString(getColumnIndexOrThrow(USER_HTML_URL)),
        getInt(getColumnIndexOrThrow(USER_FOLLOWING)),
        getString(getColumnIndexOrThrow(USER_LOCATION)),
        getString(getColumnIndexOrThrow(USER_NAME)),
        getString(getColumnIndexOrThrow(USER_NODE_ID))
    )
}

fun Cursor.tranformListUserEntity(): ArrayList<UsersFavEntity> {
    val userEntityList = ArrayList<UsersFavEntity>()

    apply {
        while (moveToNext()) {
            userEntityList.add(
                transformUserFavEntity()
            )
        }
    }
    return userEntityList
}