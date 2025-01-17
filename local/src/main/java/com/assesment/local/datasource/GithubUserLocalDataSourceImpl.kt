package com.assesment.local.datasource

import com.assesment.data.datasource.local.GithubUserLocalDataSource
import com.assesment.data.model.GithubUserDetailModel
import com.assesment.data.model.GithubUserModel
import com.assesment.local.db.GithubUserDao
import com.assesment.local.db.GithubUserDetailDao
import com.assesment.local.db.QueryDao
import com.assesment.local.mapper.LocalGithubUserDataMapper
import com.assesment.local.mapper.LocalGithubUserDetailDataMapper
import com.assesment.local.model.LocalQuery

class GithubUserLocalDataSourceImpl(
    private val githubUserDao: GithubUserDao,
    private val githubUserDetailDao: GithubUserDetailDao,
    private val queryDao: QueryDao,
    private val localGithubUserDataMapper: LocalGithubUserDataMapper,
    private val localGithubUserDetailDataMapper: LocalGithubUserDetailDataMapper
) : GithubUserLocalDataSource {
    override suspend fun cachedQuery(query: String) {
        queryDao.insert(LocalQuery(query = query, id = null));
    }

    override suspend fun cachedUsers(query: String, users: List<GithubUserModel>) {
        githubUserDao.insertAll(
            localGithubUserDataMapper.toList(users).map { it.copy(query = query) })
    }

    override suspend fun cachedUserDetail(username: String, user: GithubUserDetailModel) {
        githubUserDetailDao.insert(localGithubUserDetailDataMapper.to(user))
    }

    override suspend fun getCachedQueries(): List<String> = queryDao.getAll().map { it.query }

    override suspend fun getCachedSearchUsers(
        query: String?,
        page: Int,
        perPage: Int
    ): List<GithubUserModel> {
        val result = githubUserDao.getAllByQuery(perPage, (page - 1) * perPage)
            .map { localGithubUserDataMapper.from(it) }
        return result
    }

    override suspend fun getCachedUserDetail(username: String): GithubUserDetailModel? =
        githubUserDetailDao.getById(username.toInt())
            ?.let { localGithubUserDetailDataMapper.from(it) }
}