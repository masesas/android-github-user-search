package com.assesment.githubuser.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.assesment.domain.model.GithubUserDetail
import com.assesment.domain.usecase.SaveGithubUserDetailParams
import com.assesment.domain.usecase.SaveGithubUserDetailUseCase
import com.assesment.githubuser.constant.WorkerConstant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CachedDetailWorker(
    context: Context,
    params: WorkerParameters,
    private val saveGithubUserDetailUseCase: SaveGithubUserDetailUseCase
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = try {
        val jsonData =
            inputData.getString(WorkerConstant.KEY_CACHED_USER_DETAIL_WORKER) ?: Result.failure()

        val data = Gson().fromJson<GithubUserDetail>(
            jsonData as String,
            object : TypeToken<GithubUserDetail>() {}.type
        )

        saveGithubUserDetailUseCase.run(SaveGithubUserDetailParams(user = data))

        Result.success()
    } catch (e: Exception) {
        Result.failure()
    }
}