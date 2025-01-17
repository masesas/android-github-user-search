package com.assesment.remote.model

import com.squareup.moshi.Json

data class RemoteGithubUserResponse(
    @field:Json(name = "total_count")
    val totalCount: Int,

    @field:Json(name = "incomplete_results")
    val incompleteResults: Boolean,

    @field:Json(name = "items")
    val items: List<RemoteGithubUser>,
)

data class RemoteGithubUser(
    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "login")
    val username: String,

    @field:Json(name = "avatar_url")
    val avatarUrl: String,

    @field:Json(name = "html_url")
    val profileUrl: String,
)