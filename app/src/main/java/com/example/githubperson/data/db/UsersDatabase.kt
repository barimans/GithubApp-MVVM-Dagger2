package com.example.githubperson.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubperson.data.db.dao.UsersFavoriteDao
import com.example.githubperson.data.db.entity.UsersFavEntity
import com.example.githubperson.utils.DB_NAME

@Database(entities = [UsersFavEntity::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun userFavoriteDao(): UsersFavoriteDao

    companion object{
        @Volatile
        private var instance: UsersDatabase? = null

        fun getInstance(context: Context): UsersDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        }

        private fun buildDatabase(context: Context): UsersDatabase {
            return Room.databaseBuilder(context, UsersDatabase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }
}