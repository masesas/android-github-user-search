package com.assesment.domain.usecase

import com.assesment.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class UseCaseWithParams<in Params, out Type> {
    abstract suspend fun run(params: Params): Flow<Resource<Type>>

    suspend fun execute(params: Params): Flow<Resource<Type>> {
        return try {
            run(params)
        } catch (exception: Exception) {
            flow { emit(Resource.Error(exception)) }
        }
    }
}

abstract class UseCaseWithNoParams<out Type> {
    abstract suspend fun run(): Flow<Resource<Type>>
    
    suspend fun execute(): Flow<Resource<Type>> {
        return try {
            run()
        } catch (exception: Exception) {
            flow { emit(Resource.Error(exception)) }
        }
    }
}