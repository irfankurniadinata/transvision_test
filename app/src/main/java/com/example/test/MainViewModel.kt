package com.example.test

import androidx.lifecycle.viewModelScope
import com.example.test.core.BaseViewModel
import com.example.test.data.model.CategoryList
import com.example.test.data.model.Coupon
import com.example.test.domain.usecase.GetCategoryUseCase
import com.example.test.domain.usecase.GetCouponUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getCouponUseCase: GetCouponUseCase
): BaseViewModel() {
    private val _state = MutableStateFlow<MainViewState>(MainViewState.Init)
    val state: StateFlow<MainViewState> get() = _state

    fun getCategory() {
        val queryFilter = HashMap<String, Any?>()
        queryFilter["username"] = "user1673281842743"

        viewModelScope.launch {
            getCategoryUseCase.execute(queryFilter)
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e.message.toString())
                }
                .collect { result ->
                    showCategory(result)
                }
        }
    }

    fun getCoupon() {
        val queryFilter = HashMap<String, Any?>()
        queryFilter["username"] = "user1673281842743"

        viewModelScope.launch {
            getCouponUseCase.execute(queryFilter)
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e.message.toString())
                }
                .collect { result ->
                    hideLoading()
                    showCoupon(result)
                }
        }
    }

    private fun showMessage(msg: String) {
        _state.value = MainViewState.ShowMessage(msg)
    }

    private fun showCoupon(result: Coupon) {
        _state.value = MainViewState.ShowCoupon(result)
    }

    private fun showCategory(result: CategoryList?) {
        _state.value = MainViewState.ShowCategory(result)
    }

    private fun showLoading() {
        _state.value = MainViewState.Progress(true)
    }

    private fun hideLoading() {
        _state.value = MainViewState.Progress(false)
    }
}