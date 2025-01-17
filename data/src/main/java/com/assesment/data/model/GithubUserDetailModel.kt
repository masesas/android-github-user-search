package com.assesment.data.model

data class GithubUserDetailModel(
    val id: Int,
    val name: String,
    val bio: String,
    val username: String,
    val avatarUrl: String,
    val profileUrl: String,
    val company: String,
    val location: String,
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int,
    val blogUrl: String,
    val createdAt: String,
    val updatedAt: String,
)