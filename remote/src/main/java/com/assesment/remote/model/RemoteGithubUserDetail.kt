package com.assesment.remote.model

import com.squareup.moshi.Json

data class RemoteGithubUserDetail(
    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "bio")
    val bio: String,

    @field:Json(name = "login")
    val username: String,

    @field:Json(name = "avatar_url")
    val avatarUrl: String,

    @field:Json(name = "html_url")
    val profileUrl: String,

    @field:Json(name = "company")
    val company: String,

    @field:Json(name = "location")
    val location: String,

    @field:Json(name = "public_repos")
    val publicRepos: Int,

    @field:Json(name = "public_gists")
    val publicGists: Int,

    @field:Json(name = "followers")
    val followers: Int,

    @field:Json(name = "following")
    val following: Int,

    @field:Json(name = "blog")
    val blogUrl: String,

    @field:Json(name = "created_at")
    val createdAt: String,

    @field:Json(name = "updated_at")
    val updatedAt: String,
)