package com.assesment.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.assesment.local.constant.TableConstant
import com.assesment.local.converter.DateConverter
import java.util.Date


@Entity(tableName = TableConstant.GITHUB_USERS)
data class LocalGithubUser(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "query")
    val query: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?,

    @ColumnInfo(name = "profile_url")
    val profileUrl: String,

    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "created_at")
    var createdAt: Date = Date(System.currentTimeMillis()),
)