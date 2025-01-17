package com.assesment.domain.usecase

import com.assesment.domain.model.GithubUser
import com.assesment.domain.model.GithubUserDetail
import com.assesment.domain.repository.GtihubUserRepository
import com.assesment.domain.repository.HistoryGithubUserRepository
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@ExperimentalCoroutinesApi
abstract class BaseUseCaseTest {
    protected val githubUserRepository: GtihubUserRepository = mockk()
    protected val historyGithubUserRepository: HistoryGithubUserRepository = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    open fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    open fun tearDown() {
        Dispatchers.resetMain()
    }

    abstract fun `execute should return success result when repository returns success data`()

    abstract fun `execute should return error result when repository returns error`()

    protected fun userMock(): GithubUser = GithubUser(
        id = 1,
        username = "khesa",
        avatarUrl = "https://khesa.ava",
        profileUrl = "https://khesa.profile"
    )

    protected fun userDetailMock(): GithubUserDetail = GithubUserDetail(
        id = 1,
        name = "khesa",
        bio = "bio",
        username = "masesas",
        avatarUrl = "https://khesa.ava",
        profileUrl = "https://khesa.ava",
        company = "oto",
        location = "id",
        publicRepos = 10,
        publicGists = 10,
        followers = 10,
        following = 10,
        blogUrl = "https://khesa.ava",
        createdAt = "2018-01-20",
        updatedAt = "2018-01-20"
    )
}