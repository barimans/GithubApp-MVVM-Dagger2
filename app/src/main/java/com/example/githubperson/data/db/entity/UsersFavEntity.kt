package com.example.githubperson.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.githubperson.utils.USER_TABLE_NAME
import com.google.gson.annotations.SerializedName

@Entity(tableName = USER_TABLE_NAME)
data class UsersFavEntity(

	@PrimaryKey(autoGenerate = false)
	@field:SerializedName("id")
	val id: Int? = 0,

	@field:SerializedName("gists_url")
	val gistsUrl: String? = "",

	@field:SerializedName("repos_url")
	val reposUrl: String? = "",

	@field:SerializedName("following_url")
	val followingUrl: String? = "",

	@field:SerializedName("twitter_username")
	val twitterUsername: String? = "",

	@field:SerializedName("bio")
	val bio: String? = "",

	@field:SerializedName("created_at")
	val createdAt: String? = "",

	@field:SerializedName("login")
	val login: String? = "",

	@field:SerializedName("type")
	val type: String? = "",

	@field:SerializedName("blog")
	val blog: String? = "",

	@field:SerializedName("subscriptions_url")
	val subscriptionsUrl: String? = "",

	@field:SerializedName("updated_at")
	val updatedAt: String? = "",

	@field:SerializedName("site_admin")
	val siteAdmin: Boolean? = false,

	@field:SerializedName("company")
	val company: String? = "",

	@field:SerializedName("public_repos")
	val publicRepos: Int? = 0,

	@field:SerializedName("gravatar_id")
	val gravatarId: String? = "",

	@field:SerializedName("email")
	val email: String? = "",

	@field:SerializedName("organizations_url")
	val organizationsUrl: String? = "",

	@field:SerializedName("hireable")
	val hireable: String? = "",

	@field:SerializedName("starred_url")
	val starredUrl: String? = "",

	@field:SerializedName("followers_url")
	val followersUrl: String? = "",

	@field:SerializedName("public_gists")
	val publicGists: Int? = 0,

	@field:SerializedName("url")
	val url: String? = "",

	@field:SerializedName("received_events_url")
	val receivedEventsUrl: String? = "",

	@field:SerializedName("followers")
	val followers: Int? = 0,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = "",

	@field:SerializedName("events_url")
	val eventsUrl: String? = "",

	@field:SerializedName("html_url")
	val htmlUrl: String? = "",

	@field:SerializedName("following")
	val following: Int? = 0,

	@field:SerializedName("name")
	val name: String? = "",

	@field:SerializedName("location")
	val location: String? = "",

	@field:SerializedName("node_id")
	val nodeId: String? = ""
)
