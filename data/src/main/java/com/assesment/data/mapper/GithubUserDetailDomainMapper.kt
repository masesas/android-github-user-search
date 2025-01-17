package com.assesment.data.mapper

import com.assesment.data.model.GithubUserDetailModel
import com.assesment.shared.utils.Mapper
import com.assesment.domain.model.GithubUserDetail

class GithubUserDetailDomainMapper: Mapper<GithubUserDetailModel, GithubUserDetail> {
    override fun from(i: GithubUserDetailModel?): GithubUserDetail {
        return GithubUserDetail(
            id = i?.id ?: 0,
            username = i?.username ?: "",
            avatarUrl = i?.avatarUrl ?: "",
            profileUrl = i?.profileUrl ?: "",
            name = i?.name ?: "",
            bio = i?.bio ?: "~ No Bio ~",
            location = i?.location ?: "",
            publicRepos = i?.publicRepos ?: 0,
            followers = i?.followers ?: 0,
            following = i?.following ?: 0,
            company = i?.company ?: "",
            blogUrl = i?.blogUrl ?: "",
            createdAt = i?.createdAt ?: "",
            updatedAt = i?.updatedAt ?: "",
            publicGists = i?.publicGists ?: 0
        )
    }

    override fun to(o: GithubUserDetail?): GithubUserDetailModel {
        return GithubUserDetailModel(
            id = o?.id ?: 0,
            username = o?.username ?: "",
            avatarUrl = o?.avatarUrl ?: "",
            profileUrl = o?.profileUrl ?: "",
            name = o?.name ?: "",
            bio = o?.bio ?: "~ No Bio ~",
            location = o?.location ?: "",
            publicRepos = o?.publicRepos ?: 0,
            followers = o?.followers ?: 0,
            following = o?.following ?: 0,
            company = o?.company ?: "",
            blogUrl = o?.blogUrl ?: "",
            createdAt = o?.createdAt ?: "",
            updatedAt = o?.updatedAt ?: "",
            publicGists = o?.publicGists ?: 0
        )
    }
}