package com.example.githubperson.utils

import com.orhanobut.hawk.Hawk

class PreferencesHelper {

    companion object{
        fun getInstance(): PreferencesHelper{
            return PreferencesHelper()
        }
    }

    fun setReminder(reminder: Boolean){
        Hawk.put(PREF_REMINDER, reminder)
    }

    fun getReminder(): Boolean{
        return Hawk.get(PREF_REMINDER, false)
    }
}