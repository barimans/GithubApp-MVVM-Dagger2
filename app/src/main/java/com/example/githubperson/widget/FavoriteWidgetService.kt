package com.example.githubperson.widget

import android.content.Intent
import android.widget.RemoteViewsService

class FavoriteWidgetService : RemoteViewsService(){

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return FavoriteWidgetRemoteViewsFactory(this.applicationContext)
    }
}