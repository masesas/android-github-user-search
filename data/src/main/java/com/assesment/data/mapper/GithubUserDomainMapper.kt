package com.assesment.data.mapper

import com.assesment.data.model.GithubUserModel
import com.assesment.shared.utils.Mapper
import com.assesment.domain.model.GithubUser

class GithubUserDomainMapper : Mapper<GithubUserModel, GithubUser> {
    override fun from(i: GithubUserModel?): GithubUser {
        return GithubUser(
            id = i?.id ?: 0,
            username = i?.username ?: "",
            avatarUrl = i?.avatarUrl ?: "",
            profileUrl = i?.profileUrl ?: ""
        )
    }

    override fun to(o: GithubUser?): GithubUserModel {
        return GithubUserModel(
            id = o?.id ?: 0,
            username = o?.username ?: "",
            avatarUrl = o?.avatarUrl ?: "",
            profileUrl = o?.profileUrl ?: ""
        )
    }
}