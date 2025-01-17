package com.assesment.local.mapper

import com.assesment.data.model.GithubUserModel
import com.assesment.local.model.LocalGithubUser
import com.assesment.shared.utils.Mapper

class LocalGithubUserDataMapper : Mapper<LocalGithubUser, GithubUserModel> {
    override fun from(i: LocalGithubUser?): GithubUserModel {
        return GithubUserModel(
            id = i?.id ?: 0,
            username = i?.username ?: "",
            avatarUrl = i?.avatarUrl ?: "",
            profileUrl = i?.profileUrl ?: "",
        )
    }

    override fun to(o: GithubUserModel?): LocalGithubUser {
        return LocalGithubUser(
            id = o?.id ?: 0,
            query = "",
            username = o?.username ?: "",
            avatarUrl = o?.avatarUrl ?: "",
            profileUrl = o?.profileUrl ?: "",
        )
    }
}