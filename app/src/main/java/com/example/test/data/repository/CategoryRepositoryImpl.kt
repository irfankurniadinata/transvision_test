package com.example.test.data.repository

import com.example.test.data.model.CategoryList
import com.example.test.data.model.Coupon
import com.example.test.data.remote.CategoryDataSource
import com.example.test.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryRepositoryImpl(private val dataSource: CategoryDataSource) : CategoryRepository {
    override suspend fun getCategory(queryMap: HashMap<String, Any?>): Flow<CategoryList> {
        return flow {
            val response = dataSource.getCategory(queryMap)
            if (response.isSuccessful) {
                val body = response.body()
                emit(body!!)
            } else {
                /*Handle Error*/
            }
        }
    }

    override suspend fun getCoupon(queryMap: HashMap<String, Any?>): Flow<Coupon> {
        return flow {
            val response = dataSource.getCoupon(queryMap)
            if (response.isSuccessful) {
                val body = response.body()
                emit(body!!)
            } else {
                /*Handle Error*/
            }
        }
    }

}