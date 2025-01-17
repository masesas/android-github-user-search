package com.assesment.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assesment.local.constant.TableConstant

@Entity(tableName = TableConstant.QUERIES)
data class LocalQuery(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    @ColumnInfo(name = "query")
    var query: String
)