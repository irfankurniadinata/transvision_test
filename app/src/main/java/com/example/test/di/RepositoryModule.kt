package com.example.test.di

import com.example.test.data.remote.CategoryDataSource
import com.example.test.data.repository.CategoryRepositoryImpl
import com.example.test.domain.repository.CategoryRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    single { provideCategoryDataSource(get()) }
    single { provideCategoryRepository(get()) }
}

fun provideCategoryDataSource(retrofit: Retrofit): CategoryDataSource {
    return retrofit.create(CategoryDataSource::class.java)
}

fun provideCategoryRepository(categoryDataSource: CategoryDataSource): CategoryRepository {
    return CategoryRepositoryImpl(categoryDataSource)
}
