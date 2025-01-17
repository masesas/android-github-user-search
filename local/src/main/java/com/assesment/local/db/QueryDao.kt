package com.assesment.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assesment.local.constant.TableConstant
import com.assesment.local.model.LocalQuery

@Dao
interface QueryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(query: LocalQuery)

    @Query("SELECT * FROM ${TableConstant.QUERIES}")
    fun getAll(): List<LocalQuery>
}