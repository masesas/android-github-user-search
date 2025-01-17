package com.assesment.shared.utils

/**
* Low: Low level model
* High: High level model
**/
interface Mapper<Low, High> {
    fun from(i: Low?): High

    fun to(o: High?): Low

    fun fromList(list: List<Low>?): List<High> {
        return list?.mapNotNull { from(it) } ?: listOf()
    }

    fun toList(list: List<High>?): List<Low> {
        return list?.mapNotNull { to(it) } ?: listOf()
    }
}