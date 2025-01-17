package com.assesment.remote.mapper

import com.assesment.data.model.GithubUserModel
import com.assesment.remote.model.RemoteGithubUser
import com.assesment.shared.utils.Mapper

class RemoteGithubUserDataMapper: Mapper<RemoteGithubUser, GithubUserModel> {
    override fun from(i: RemoteGithubUser?): GithubUserModel {
        return GithubUserModel(
            id = i?.id ?: 0,
            username = i?.username ?: "",
            avatarUrl = i?.avatarUrl ?: "",
            profileUrl = i?.profileUrl ?: "",
        )
    }

    override fun to(o: GithubUserModel?): RemoteGithubUser {
        return RemoteGithubUser(
            id = o?.id ?: 0,
            username = o?.username ?: "",
            avatarUrl = o?.avatarUrl ?: "",
            profileUrl = o?.profileUrl ?: "",
        )
    }
}