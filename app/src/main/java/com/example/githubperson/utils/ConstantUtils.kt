package com.example.githubperson.utils

/*Database*/
const val DB_NAME = "UsersFavorite.db"
const val DB_AUTHORITY = "com.example.githubperson"
const val DB_SCHEME = "content"
const val DB_CONTENT_URI = "$DB_SCHEME://$DB_AUTHORITY"
const val USER_TABLE_NAME = "users_favorite"
const val USER_CONTENT_URI = "$DB_CONTENT_URI/$USER_TABLE_NAME"

/*Table Column*/
const val USER_ID = "id"
const val USER_GISTS_URL = "gistsUrl"
const val USER_REPOS_URL = "reposUrl"
const val USER_FOLLOWING_URL = "followingUrl"
const val USER_TWITTER_USERNAME = "twitterUsername"
const val USER_BIO = "bio"
const val USER_CREATED_AT = "createdAt"
const val USER_LOGIN = "login"
const val USER_TYPE = "type"
const val USER_BLOG = "blog"
const val USER_SUBSCRIPTION_URL = "subscriptionsUrl"
const val USER_UPDATED_AT = "updatedAt"
const val USER_SITE_ADMIN = "siteAdmin"
const val USER_COMPANY = "company"
const val USER_PUBLIC_REPOS = "publicRepos"
const val USER_GRAVATAR_ID = "gravatarId"
const val USER_EMAIL = "email"
const val USER_ORGANIZATION_URL = "organizationsUrl"
const val USER_HIREABLE = "hireable"
const val USER_STARRED_URL = "starredUrl"
const val USER_FOLLOWERS_URL = "followersUrl"
const val USER_PUBLIC_GISTS = "publicGists"
const val USER_URL = "url"
const val USER_RECEIVED_EVENTS_URL = "receivedEventsUrl"
const val USER_FOLLOWERS = "followers"
const val USER_AVATAR_URL = "avatarUrl"
const val USER_EVENTS_URL = "eventsUrl"
const val USER_HTML_URL = "htmlUrl"
const val USER_FOLLOWING = "following"
const val USER_NAME = "name"
const val USER_LOCATION = "location"
const val USER_NODE_ID = "nodeId"

/*Alarm*/
const val ALARM_ID_REPEATING = 100
const val ALARM_CHANNEL_ID = "channel_reminder"
const val ALARM_CHANNEL_NAME = "Reminder Channel"
const val ALARM_TITTLE = "alarm_tittle"
const val ALARM_MESSAGE = "alarm_message"

/*Hawk Preference*/
const val PREF_REMINDER = "pref_reminder"