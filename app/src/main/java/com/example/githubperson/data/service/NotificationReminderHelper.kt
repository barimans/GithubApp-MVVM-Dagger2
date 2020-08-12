package com.example.githubperson.data.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.githubperson.R
import com.example.githubperson.utils.ALARM_CHANNEL_ID
import com.example.githubperson.utils.ALARM_CHANNEL_NAME

fun showNotificationReminder(
    context: Context,
    title: String,
    message: String,
    notificationId: Int,
    intent: PendingIntent
){
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    // Create NotificationCompat Builder
    val notification = NotificationCompat.Builder(context, ALARM_CHANNEL_ID).apply {
        setSmallIcon(R.drawable.ic_notifications)
        setLargeIcon(
            BitmapFactory.decodeResource(
                context.resources,
                R.drawable.github_person
            )
        )
        setContentTitle(title)
        setContentText(message)
        setContentIntent(intent)
        color = ContextCompat.getColor(context, android.R.color.transparent)
        setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
        setSound(alarmSound)
        setAutoCancel(true)
    }

    // Add channel when SDK > 26 (Oreo)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        val channel = NotificationChannel(
            ALARM_CHANNEL_ID,
            ALARM_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            enableVibration(true)
            vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
        }

        notification.setChannelId(ALARM_CHANNEL_ID)
        notificationManager.createNotificationChannel(channel)
    }

    notificationManager.notify(notificationId, notification.build())
}