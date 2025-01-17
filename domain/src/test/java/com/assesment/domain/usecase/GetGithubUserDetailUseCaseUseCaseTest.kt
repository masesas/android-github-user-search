package com.assesment.domain.usecase

import androidx.test.filters.SmallTest
import com.assesment.shared.utils.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@SmallTest
class GetGithubUserDetailUseCaseUseCaseTest : BaseUseCaseTest() {
    private lateinit var getGithubUserDetailUseCase: GetGithubUserDetailUseCase

    override fun setup() {
        super.setup()
        getGithubUserDetailUseCase = GetGithubUserDetailUseCase(githubUserRepository)
    }

    @Test
    override fun `execute should return success result when repository returns success data`() =
        runTest {
            val params = GetGithubUserDetailParams("test")
            val expectedObj = userDetailMock()
            val expected = Resource.Success(expectedObj)

            coEvery { githubUserRepository.getUserDetail(any()) } returns flowOf(expected)

            val run = getGithubUserDetailUseCase.run(params).single()

            assertTrue(run is Resource.Success)
            assertEquals(expectedObj, run.data)
            coVerify(exactly = 1) { githubUserRepository.getUserDetail(params.username) }
        }

    @Test
    override fun `execute should return error result when repository returns error`() = runTest {
        val params = GetGithubUserDetailParams("test")
        val expected = Exception("error")

        coEvery { githubUserRepository.getUserDetail(any()) } returns flowOf(Resource.Error(expected))
        val run = getGithubUserDetailUseCase.run(params).single()

        assertTrue(run is Resource.Error)
        assertEquals(expected, run.exception)
        coVerify(exactly = 1) { githubUserRepository.getUserDetail(params.username) }
    }
}