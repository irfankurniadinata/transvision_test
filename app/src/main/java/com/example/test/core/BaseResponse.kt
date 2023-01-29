package com.example.test.core

data class BaseResponse<T>(
    val status: Boolean? = null,
    val statusCode: String? = null,
    val result: T? = null,
    val message: List<String>? = null
)
