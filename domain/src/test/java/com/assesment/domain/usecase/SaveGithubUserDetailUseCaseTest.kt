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
class SaveGithubUserDetailUseCaseTest : BaseUseCaseTest() {
    private lateinit var saveGithubUserDetailUseCase: SaveGithubUserDetailUseCase

    override fun setup() {
        super.setup()
        saveGithubUserDetailUseCase = SaveGithubUserDetailUseCase(historyGithubUserRepository)
    }

    @Test
    override fun `execute should return success result when repository returns success data`() =
        runTest {
            val params = SaveGithubUserDetailParams(userDetailMock())

            coEvery {
                historyGithubUserRepository.saveUserDetail(any())
            } returns Resource.Success(true)

            val run = saveGithubUserDetailUseCase.run(params)

            assertTrue(run is Resource.Success)
            assertEquals(true, (run as Resource.Success).data)
            coVerify(exactly = 1) {
                historyGithubUserRepository.saveUserDetail(params.user)
            }
        }

    @Test
    override fun `execute should return error result when repository returns error`() = runTest {
        val params = SaveGithubUserDetailParams(userDetailMock())
        val expected = Exception("error")

        coEvery {
            historyGithubUserRepository.saveUserDetail(any())
        } returns Resource.Error(expected)

        val run = saveGithubUserDetailUseCase.run(params)

        assertTrue(run is Resource.Error)
        assertEquals(expected, (run as Resource.Error).exception)
        coVerify(exactly = 1) {
            historyGithubUserRepository.saveUserDetail(params.user)
        }
    }
}