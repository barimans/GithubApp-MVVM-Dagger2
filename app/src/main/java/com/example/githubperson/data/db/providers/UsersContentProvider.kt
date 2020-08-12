package com.example.githubperson.data.db.providers

import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.core.net.toUri
import com.example.githubperson.data.db.UsersDatabase
import com.example.githubperson.utils.DB_AUTHORITY
import com.example.githubperson.utils.USER_CONTENT_URI
import com.example.githubperson.utils.USER_TABLE_NAME
import com.example.githubperson.utils.transformUserFavEntity
import dagger.android.DaggerContentProvider
import javax.inject.Inject

class UsersContentProvider: DaggerContentProvider() {

    @Inject
    lateinit var db: UsersDatabase

    companion object{

        private const val USER = 1
        private const val USER_ID = 2

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(DB_AUTHORITY, USER_TABLE_NAME, USER)

            uriMatcher.addURI(DB_AUTHORITY, "$USER_TABLE_NAME/#", USER_ID)
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val added: Long = when (USER) {
            uriMatcher.match(uri) -> values?.transformUserFavEntity()?.let {
                db.userFavoriteDao().insertUserFav(
                    it
                )
            } ?: 0
            else -> 0
        }

        context?.contentResolver?.notifyChange(USER_CONTENT_URI.toUri(), null)

        return Uri.parse("$USER_CONTENT_URI/$added")
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            USER -> db.userFavoriteDao().getUsersFav()
            USER_ID -> uri.lastPathSegment?.toInt()?.let { db.userFavoriteDao().getUserFavById(it) }
            else -> null
        }
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val deleted: Int = when (USER_ID) {
            uriMatcher.match(uri) -> uri.lastPathSegment?.toInt()?.let {
                db.userFavoriteDao().deleteUserFav(
                    it
                )
            } ?: 0
            else -> 0
        }

        context?.contentResolver?.notifyChange(USER_CONTENT_URI.toUri(), null)

        return deleted
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}