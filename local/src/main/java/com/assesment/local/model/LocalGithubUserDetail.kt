package com.assesment.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assesment.local.constant.TableConstant

@Entity(tableName = TableConstant.GITHUB_USER_DETAILS)
data class LocalGithubUserDetail(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "bio")
    val bio: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,

    @ColumnInfo(name = "profile_url")
    val profileUrl: String,

    @ColumnInfo(name = "company")
    val company: String,

    @ColumnInfo(name = "location")
    val location: String,

    @ColumnInfo(name = "public_repos")
    val publicRepos: Int,

    @ColumnInfo(name = "public_gists")
    val publicGists: Int,

    @ColumnInfo(name = "followers")
    val followers: Int,

    @ColumnInfo(name = "following")
    val following: Int,

    @ColumnInfo(name = "blog_url")
    val blogUrl: String,

    @ColumnInfo(name = "created_at")
    val createdAt: String,

    @ColumnInfo(name = "updated_at")
    val updatedAt: String,
)