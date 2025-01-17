package com.assesment.local.mapper

import com.assesment.data.model.GithubUserDetailModel
import com.assesment.local.model.LocalGithubUserDetail
import com.assesment.shared.utils.Mapper

class LocalGithubUserDetailDataMapper : Mapper<LocalGithubUserDetail, GithubUserDetailModel> {
    override fun from(i: LocalGithubUserDetail?): GithubUserDetailModel {
        return GithubUserDetailModel(
            id = i?.id ?: 0,
            name = i?.name ?: "",
            username = i?.username ?: "",
            avatarUrl = i?.avatarUrl ?: "",
            bio = i?.bio ?: "",
            company = i?.company ?: "",
            location = i?.location ?: "",
            followers = i?.followers ?: 0,
            following = i?.following ?: 0,
            publicRepos = i?.publicRepos ?: 0,
            publicGists = i?.publicGists ?: 0,
            createdAt = i?.createdAt ?: "",
            updatedAt = i?.updatedAt ?: "",
            profileUrl = i?.profileUrl ?: "",
            blogUrl = i?.blogUrl ?: "",
        )
    }

    override fun to(o: GithubUserDetailModel?): LocalGithubUserDetail {
        return LocalGithubUserDetail(
            id = o?.id ?: 0,
            name = o?.name ?: "",
            username = o?.username ?: "",
            avatarUrl = o?.avatarUrl ?: "",
            bio = o?.bio ?: "",
            company = o?.company ?: "",
            location = o?.location ?: "",
            followers = o?.followers ?: 0,
            following = o?.following ?: 0,
            publicRepos = o?.publicRepos ?: 0,
            publicGists = o?.publicGists ?: 0,
            createdAt = o?.createdAt ?: "",
            updatedAt = o?.updatedAt ?: "",
            profileUrl = o?.profileUrl ?: "",
            blogUrl = o?.blogUrl ?: "",
        )
    }
}