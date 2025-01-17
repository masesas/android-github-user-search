package com.assesment.remote.mapper

import com.assesment.data.model.GithubUserDetailModel
import com.assesment.remote.model.RemoteGithubUserDetail
import com.assesment.shared.utils.Mapper

class RemoteGithubUserDetailDataMapper: Mapper<RemoteGithubUserDetail, GithubUserDetailModel> {
    override fun from(i: RemoteGithubUserDetail?): GithubUserDetailModel {
        return GithubUserDetailModel(
            id = i?.id ?: 0,
            name = i?.name ?: "",
            bio = i?.bio ?: "",
            company = i?.company ?: "",
            location = i?.location ?: "",
            followers = i?.followers ?: 0,
            following = i?.following ?: 0,
            publicRepos = i?.publicRepos ?: 0,
            publicGists = i?.publicGists ?: 0,
            avatarUrl = i?.avatarUrl ?: "",
            profileUrl = i?.profileUrl ?: "",
            blogUrl = i?.blogUrl ?: "",
            createdAt = i?.createdAt ?: "",
            updatedAt = i?.updatedAt ?: "",
            username = i?.username ?: "",
        )
    }

    override fun to(o: GithubUserDetailModel?): RemoteGithubUserDetail {
        return RemoteGithubUserDetail(
            id = o?.id ?: 0,
            name = o?.name ?: "",
            bio = o?.bio ?: "",
            company = o?.company ?: "",
            location = o?.location ?: "",
            followers = o?.followers ?: 0,
            following = o?.following ?: 0,
            publicRepos = o?.publicRepos ?: 0,
            publicGists = o?.publicGists ?: 0,
            avatarUrl = o?.avatarUrl ?: "",
            profileUrl = o?.profileUrl ?: "",
            blogUrl = o?.blogUrl ?: "",
            createdAt = o?.createdAt ?: "",
            updatedAt = o?.updatedAt ?: "",
            username = o?.username ?: "",
        )
    }
}