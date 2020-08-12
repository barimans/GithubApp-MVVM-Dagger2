package com.example.githubperson.data.db.dao

import android.database.Cursor
import androidx.room.*
import com.example.githubperson.data.db.entity.UsersFavEntity

@Dao
interface UsersFavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserFav(userFavEntity: UsersFavEntity): Long

    @Query("DELETE FROM users_favorite WHERE id = :userId")
    fun deleteUserFav(userId: Int): Int

    @Query("SELECT * FROM users_favorite ORDER BY name ASC")
    fun getUsersFav(): Cursor

    @Query("SELECT * FROM users_favorite WHERE id = :userId")
    fun getUserFavById(userId: Int): Cursor
}