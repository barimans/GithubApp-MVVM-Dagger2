package com.example.githubperson.ui.settings_ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.widget.Toast
import com.example.githubperson.R
import com.example.githubperson.data.service.AlarmReminderHelper
import com.example.githubperson.utils.ALARM_ID_REPEATING
import com.example.githubperson.utils.PreferencesHelper
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*
import java.util.*
import javax.inject.Inject

class SettingsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var pref: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setupToolbar()

        navSettings.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }

        switchReminder.apply {
            isChecked = pref.getReminder()

            setOnCheckedChangeListener { buttonView, isChecked ->
                setReminderAlarm(isChecked)
            }
        }

    }

    private fun setReminderAlarm(reminder: Boolean){
        pref.setReminder(reminder)

        if (reminder) {
            AlarmReminderHelper.setAlarmReminder(
                this,
                getString(R.string.app_name),
                "Let's find popular and favorite user on Github!",
                ALARM_ID_REPEATING,
                Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, 9)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                }
            )

            Toast.makeText(this, getString(R.string.wording_alarm_on), Toast.LENGTH_SHORT).show()
        } else {
            AlarmReminderHelper.cancelAlarmReminer(this, ALARM_ID_REPEATING)

            Toast.makeText(this, getString(R.string.wording_alarm_off), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupToolbar(){
        supportActionBar?.title = getString(R.string.settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}