package com.example.test.domain.repository

import com.example.test.data.model.CategoryList
import com.example.test.data.model.Coupon
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategory(queryMap: HashMap<String, Any?>): Flow<CategoryList>

    suspend fun getCoupon(queryMap: HashMap<String, Any?>): Flow<Coupon>
}