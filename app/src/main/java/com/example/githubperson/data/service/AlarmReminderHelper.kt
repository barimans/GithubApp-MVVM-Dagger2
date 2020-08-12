package com.example.githubperson.data.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.githubperson.utils.ALARM_MESSAGE
import com.example.githubperson.utils.ALARM_TITTLE
import java.util.*

object AlarmReminderHelper {

    fun setAlarmReminder(
        context: Context,
        tittle: String,
        message: String,
        requestCode: Int,
        time: Calendar
    ){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReminderReceiver::class.java).apply {
            putExtra(ALARM_TITTLE, tittle)
            putExtra(ALARM_MESSAGE, message)
        }
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            time.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    fun cancelAlarmReminer(
        context: Context,
        requestCode: Int
    ){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0).also {
            it.cancel()
        }

        alarmManager.cancel(pendingIntent)
    }
}