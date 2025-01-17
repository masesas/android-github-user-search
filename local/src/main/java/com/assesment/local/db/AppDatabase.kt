package com.assesment.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.assesment.local.converter.DateConverter
import com.assesment.local.model.LocalGithubUser
import com.assesment.local.model.LocalGithubUserDetail
import com.assesment.local.model.LocalQuery
import com.assesment.shared.constant.DbConstant

@TypeConverters(DateConverter::class)
@Database(
    entities = [
        LocalGithubUser::class,
        LocalGithubUserDetail::class,
        LocalQuery::class,
    ],
    version = DbConstant.DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): AppDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DbConstant.DB_NAME
        ).build()
    }

    abstract fun getGithubUserDao(): GithubUserDao
    abstract fun getGithubUserDetailDao(): GithubUserDetailDao
    abstract fun getQueryDao(): QueryDao
}