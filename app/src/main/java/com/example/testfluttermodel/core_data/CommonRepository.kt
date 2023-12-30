package com.example.testfluttermodel.core_data

import kotlinx.coroutines.delay

interface CommonRepository {

    suspend fun foo(): Int

    suspend fun bar(): String
}

internal class CommonRepositoryImpl : CommonRepository {
    override suspend fun foo(): Int {
        delay(5000)
        return 1000
    }

    override suspend fun bar(): String {
        delay(1000)
        return "AAAA"
    }
}