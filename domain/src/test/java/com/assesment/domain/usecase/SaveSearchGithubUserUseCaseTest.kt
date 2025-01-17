package com.assesment.domain.usecase

import androidx.test.filters.SmallTest
import com.assesment.shared.utils.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class SaveSearchGithubUserUseCaseTest : BaseUseCaseTest() {
    private lateinit var saveSearchGithubUserUseCase: SaveSearchGithubUserUseCase

    override fun setup() {
        super.setup()
        saveSearchGithubUserUseCase = SaveSearchGithubUserUseCase(historyGithubUserRepository)
    }

    @Test
    override fun `execute should return success result when repository returns success data`() =
        runTest {
            val params = SaveSearchGithubUserParams("test", listOf(userMock()))

            coEvery {
                historyGithubUserRepository.saveSearchUser(
                    any(),
                    any()
                )
            } returns Resource.Success(true)

            val run = saveSearchGithubUserUseCase.run(params)

            assertTrue(run is Resource.Success)
            assertEquals(true, (run as Resource.Success).data)
            coVerify(exactly = 1) {
                historyGithubUserRepository.saveSearchUser(params.query, params.users)
            }
        }

    @Test
    override fun `execute should return error result when repository returns error`() = runTest {
        val params = SaveSearchGithubUserParams("test", listOf(userMock()))
        val expected = Exception("error")

        coEvery {
            historyGithubUserRepository.saveSearchUser(
                any(),
                any()
            )
        } returns Resource.Error(expected)

        val run = saveSearchGithubUserUseCase.run(params)

        assertTrue(run is Resource.Error)
        assertEquals(expected, (run as Resource.Error).exception)
        coVerify(exactly = 1) {
            historyGithubUserRepository.saveSearchUser(params.query, params.users)
        }
    }
}