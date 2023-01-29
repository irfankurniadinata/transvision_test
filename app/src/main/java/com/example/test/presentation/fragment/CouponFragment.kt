package com.example.test.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.lifecycleScope
import com.example.test.MainActivity
import com.example.test.MainViewModel
import com.example.test.MainViewState
import com.example.test.data.model.Category
import com.example.test.data.model.CategoryList
import com.example.test.data.model.Coupon
import com.example.test.data.model.CouponDetail
import com.example.test.presentation.detail_coupon.DetailCouponActivity
import com.example.test.presentation.dialog.DialogProgress
import com.example.test.utils.CardListRow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class CouponFragment : RowsSupportFragment() {

    private var mRowsAdapter: ArrayObjectAdapter? = null

    private val viewModel by viewModel<MainViewModel>()

    var categoryList: List<Category>? = null

    private lateinit var progressDialog : DialogProgress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_LARGE))
        adapter = mRowsAdapter

        progressDialog = DialogProgress(requireContext())
        viewModel.getCategory()
        onItemClick()
    }

    private fun createRow(couponList: List<CouponDetail>, header: String?) : Row {
        val cardPresenter : Presenter = CouponCardPresenter()
        val adapter = ArrayObjectAdapter(cardPresenter)
        for (data in couponList) {
            adapter.add(data)
        }
        val headerItem = HeaderItem(header)
        return CardListRow(headerItem, adapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeState()
    }

    private fun subscribeState() {
        lifecycleScope.launch {
            viewModel.state
                .onEach { state ->
                    handleFlowState(state)
                }
                .launchIn(this)
        }
    }

    private fun handleFlowState(state: MainViewState) {
        when (state) {
            is MainViewState.Init -> Unit
            is MainViewState.Progress -> onProgress(state.isLoading)
            is MainViewState.ShowMessage -> onShowMessage(state.msg)
            is MainViewState.ShowCategory -> onShowCategory(state.category)
            is MainViewState.ShowCoupon -> onShowCoupon(state.coupon)
        }
    }

    private fun getCoupon(categoryId: String?, couponDetail: List<CouponDetail>?) : List<CouponDetail>{
        val mutableListCouponDetail = mutableListOf<CouponDetail>()
        couponDetail?.forEach {
            if (categoryId == it.couponCategoryId) {
                mutableListCouponDetail.add(it)
            }
        }
        return mutableListCouponDetail
    }

    private fun onShowCoupon(coupon: Coupon?) {
        categoryList?.forEach {
            val coupon = getCoupon(it.categoryId, coupon?.result)
            if (!coupon.isNullOrEmpty()) {
                mRowsAdapter?.add(createRow(coupon, it.categoryName))
            }
        }
    }

    private fun onShowCategory(category: CategoryList?) {
        categoryList = category?.result
        viewModel.getCoupon()
    }

    private fun onProgress(loading: Boolean) {
        if (loading) showProgress() else hideProgress()
    }

    private fun onShowMessage(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    private fun onItemClick() {
        onItemViewClickedListener = ItemViewClickListener()
        onItemViewSelectedListener = OnItemViewSelectedListener()
    }

    private fun showProgress() : DialogProgress {
        try {
            progressDialog.show()
        }catch (e : Exception) {
            e.printStackTrace()
        }
        return progressDialog
    }

    private fun hideProgress() {
        try {
            progressDialog.dismiss()
        }catch (e : Exception) {
            e.printStackTrace()
        }
    }

    inner class ItemViewClickListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if (item is CouponDetail) {
                val intent = Intent(requireContext(), DetailCouponActivity::class.java)
                intent.putExtra("couponBrandName", item.couponBrandName)
                intent.putExtra("couponTnc", item.couponTnc)
                intent.putExtra("couponBrandLogo", item.couponBrandLogo)
                startActivity(intent)
            }
        }
    }

    inner class OnItemViewSelectedListener : androidx.leanback.widget.OnItemViewSelectedListener {
        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if (item is CouponDetail) {
                (activity as MainActivity).setImage(item.couponBrandLogo)
            }
        }

    }
}