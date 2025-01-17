package com.assesment.githubuser.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.assesment.domain.model.GithubUser
import com.assesment.domain.usecase.SaveSearchGithubUserParams
import com.assesment.domain.usecase.SaveSearchGithubUserUseCase
import com.assesment.githubuser.constant.WorkerConstant
import com.assesment.shared.utils.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CachedSearchWorker(
    context: Context,
    params: WorkerParameters,
    private val saveSearchGithubUserUseCase: SaveSearchGithubUserUseCase,
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = try {
        val jsonData =
            inputData.getString(WorkerConstant.KEY_CACHED_SEARCH_USER_WORKER)
        val query =
            inputData.getString(WorkerConstant.KEY_CACHED_SEARCH_QUERY_WORKER)

        if (jsonData != null && query != null) {
            val dataList = Gson().fromJson<List<GithubUser>>(
                jsonData,
                object : TypeToken<List<GithubUser>>() {}.type
            )

            saveSearchGithubUserUseCase.run(
                SaveSearchGithubUserParams(
                    query = query,
                    users = dataList
                )
            ).collect {
                when (it) {
                    is Resource.Empty -> {
                        Result.success()
                    }

                    is Resource.Error -> {
                        Result.failure()
                    }
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        Result.success()
                    }
                }
            }

        }

        Result.success()
    } catch (e: Exception) {
        e.printStackTrace()
        Result.failure()
    }
}