package com.assesment.domain.usecase

import androidx.test.filters.SmallTest
import com.assesment.shared.utils.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@SmallTest
class GetSearchGithubUserUseCaseUseCaseTest : BaseUseCaseTest() {
    private lateinit var getSearchGithubUserUseCase: GetSearchGithubUserUseCase

    @Before
    override fun setup() {
        super.setup()
        getSearchGithubUserUseCase = GetSearchGithubUserUseCase(githubUserRepository)
    }

    @Test
    override fun `execute should return success result when repository returns success data`() =
        runTest {
            val params = GetSearchGithubUserParams("test", 1, 20)
            val expectList = listOf(
               userMock()
            )
            val expectResult = Resource.Success(expectList)

            coEvery {
                githubUserRepository.getSearchUser(
                    any(),
                    any(),
                    any()
                )
            } returns flowOf(expectResult)

            val run = getSearchGithubUserUseCase.run(params).first()

            assertTrue(run is Resource.Success)
            assertEquals(expectList, run.data)
            coVerify(exactly = 1) {
                githubUserRepository.getSearchUser(
                    params.query,
                    params.page,
                    params.perPage
                )
            }
        }

    @Test
    override fun `execute should return error result when repository returns error`() = runTest {
        val params = GetSearchGithubUserParams("test", 1, 20)
        val expected = Exception("error")

        coEvery {
            githubUserRepository.getSearchUser(
                any(),
                any(),
                any()
            )
        } returns flowOf(Resource.Error(expected))

        val run = getSearchGithubUserUseCase.run(params).first()

        assertTrue(run is Resource.Error)
        assertEquals(expected, run.exception)
        coVerify(exactly = 1) {
            githubUserRepository.getSearchUser(
                params.query,
                params.page,
                params.perPage
            )
        }
    }
}