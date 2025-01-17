package com.assesment.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assesment.local.model.LocalGithubUser


@Dao
interface GithubUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<LocalGithubUser>)

    @Query("SELECT * FROM github_users")
    suspend fun getAll(): List<LocalGithubUser>

    @Query("SELECT * FROM github_users ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
    suspend fun getAllByQuery(limit: Int, offset: Int): List<LocalGithubUser>
}