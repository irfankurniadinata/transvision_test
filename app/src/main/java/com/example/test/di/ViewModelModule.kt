package com.example.test.di

import com.example.test.MainViewModel
import com.example.test.presentation.activity.detail_coupon.DetailCouponViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DetailCouponViewModel() }
    viewModel { MainViewModel(get(), get()) }
}
