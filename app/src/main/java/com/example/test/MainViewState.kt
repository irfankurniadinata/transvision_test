package com.example.test

import com.example.test.data.model.CategoryList
import com.example.test.data.model.Coupon

sealed class MainViewState {
    object Init : MainViewState()
    data class Progress(val isLoading: Boolean) : MainViewState()
    data class ShowMessage(val msg: String) : MainViewState()
    data class ShowCategory(val category: CategoryList?) : MainViewState()
    data class ShowCoupon(val coupon: Coupon?) : MainViewState()
}
