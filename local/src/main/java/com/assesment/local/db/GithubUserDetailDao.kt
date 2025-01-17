package com.assesment.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assesment.local.constant.TableConstant
import com.assesment.local.model.LocalGithubUserDetail

@Dao
interface GithubUserDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: LocalGithubUserDetail)

    @Query("SELECT * FROM ${TableConstant.GITHUB_USER_DETAILS}")
    suspend fun getAll(): List<LocalGithubUserDetail>

    @Query("SELECT * FROM ${TableConstant.GITHUB_USER_DETAILS} WHERE id = :id")
    suspend fun getById(id: Int): LocalGithubUserDetail?
}