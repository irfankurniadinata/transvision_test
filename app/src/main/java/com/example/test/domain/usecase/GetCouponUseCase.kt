package com.example.test.domain.usecase

import com.example.test.data.model.Coupon
import com.example.test.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlin.collections.HashMap

class GetCouponUseCase(private val categoryRepository: CategoryRepository) {
    suspend fun execute(queryMap: HashMap<String, Any?>): Flow<Coupon> {
        return categoryRepository.getCoupon(queryMap)
    }
}