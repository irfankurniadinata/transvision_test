package com.example.test.data.remote

import com.example.test.data.model.CategoryList
import com.example.test.data.model.Coupon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CategoryDataSource {
    @GET("getAllCategory")
    suspend fun getCategory(
        @QueryMap queryMap: HashMap<String, Any?>?
    ): Response<CategoryList>

    @GET("getCoupon")
    suspend fun getCoupon(
        @QueryMap queryMap: HashMap<String, Any?>?
    ): Response<Coupon>
}