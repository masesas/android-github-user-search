package com.assesment.remote.api

import android.content.Context
import com.assesment.shared.constant.AppConstant
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfig {
    companion object {
        fun build(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

        fun provideOkHttpClient(
            logging: HttpLoggingInterceptor,
            chucker: ChuckerInterceptor
        ): OkHttpClient = OkHttpClient.Builder()
            .readTimeout(6, TimeUnit.SECONDS)
            .writeTimeout(6, TimeUnit.SECONDS)
            .connectTimeout(6, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(chucker)
            .build()

        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
/*
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.NONE
            }
*/

            return logging
        }

        fun provideChuckerInterceptor(context: Context): ChuckerInterceptor {
            val collector = ChuckerCollector(
                context = context,
                showNotification = true,
                retentionPeriod = RetentionManager.Period.ONE_HOUR
            )

            return ChuckerInterceptor.Builder(context)
                .collector(collector)
                .redactHeaders("Auth-Token", "Bearer")
                .alwaysReadResponseBody(true)
                .createShortcut(true)
                .build()
        }

        fun provideGithubUserApi(retrofit: Retrofit): GithubUserApiService =
            retrofit.create(GithubUserApiService::class.java)
    }
}