package com.example.githubperson.widget

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Binder
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import com.example.githubperson.R
import com.example.githubperson.data.db.entity.UsersFavEntity
import com.example.githubperson.utils.USER_CONTENT_URI
import com.example.githubperson.utils.toBitmap
import com.example.githubperson.utils.tranformListUserEntity

internal class FavoriteWidgetRemoteViewsFactory(private val mContext: Context) :
    RemoteViewsService.RemoteViewsFactory{

    private var list: List<UsersFavEntity> = listOf()
    private var cursor: Cursor? = null

    override fun onDataSetChanged() {
        cursor?.close()

        val identityToken = Binder.clearCallingIdentity()

        cursor = mContext.contentResolver?.query(USER_CONTENT_URI.toUri(), null, null, null, null)
        cursor?.let {
            list = it.tranformListUserEntity()
        }

        Binder.restoreCallingIdentity(identityToken)
    }

    override fun getViewAt(position: Int): RemoteViews {
        val views = RemoteViews(mContext.packageName, R.layout.favorite_widget_item)

        if (!list.isNullOrEmpty()) {
            // Set item stack view
            views.apply {
                list[position].apply {
                    setImageViewBitmap(
                        R.id.ivFavoriteWidget, avatarUrl?.toBitmap(mContext)
                    )
                }
            }

            val extras = bundleOf(
                FavoriteWidget.EXTRA_ITEM to position
            )
            val fillInIntent = Intent()
            fillInIntent.putExtras(extras)

            views.setOnClickFillInIntent(R.id.ivFavoriteWidget, fillInIntent)
        }

        return views
    }

    override fun onDestroy() {
        cursor?.close()
        list = listOf()
    }

    override fun onCreate() {}

    override fun getCount(): Int = list.size

    override fun hasStableIds(): Boolean = true

    override fun getViewTypeCount(): Int = 1

    override fun getLoadingView(): RemoteViews? = null

    override fun getItemId(position: Int): Long = 0
}