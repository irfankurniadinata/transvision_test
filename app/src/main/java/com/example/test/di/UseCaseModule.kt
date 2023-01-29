package com.example.test.di

import com.example.test.domain.usecase.GetCategoryUseCase
import com.example.test.domain.usecase.GetCouponUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCategoryUseCase(get()) }
    single { GetCouponUseCase(get()) }
}
