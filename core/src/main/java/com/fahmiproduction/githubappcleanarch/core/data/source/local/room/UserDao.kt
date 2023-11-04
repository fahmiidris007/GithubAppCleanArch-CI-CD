package com.fahmiproduction.githubappcleanarch.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fahmiproduction.githubappcleanarch.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getFavoriteUser(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user WHERE login = :username")
    fun getFavoriteState(username: String): Flow<UserEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteUser(user: UserEntity?)

    @Delete
    suspend fun deleteFavoriteUser(user: UserEntity): Int
}