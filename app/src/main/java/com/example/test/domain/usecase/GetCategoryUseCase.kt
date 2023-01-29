package com.example.test.domain.usecase

import com.example.test.data.model.CategoryList
import com.example.test.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlin.collections.HashMap

class GetCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend fun execute(queryMap: HashMap<String, Any?>): Flow<CategoryList> {
        return categoryRepository.getCategory(queryMap)
    }
}