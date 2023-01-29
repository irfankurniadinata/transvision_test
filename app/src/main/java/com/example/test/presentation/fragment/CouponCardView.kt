package com.example.test.presentation.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.test.R
import com.example.test.data.model.CouponDetail
import com.example.test.utils.BindingView
import com.example.test.utils.setImage

class CouponCardView(context: Context?) : BindingView<CouponDetail>(context) {
    private var view: View? = null

    override fun initViewBinding() {
        val inflated = LayoutInflater.from(context)
        view = inflated.inflate(layoutResource, this)
    }

    public override fun bind(data: CouponDetail) {
        view?.findViewById<ImageView>(R.id.iv_coupon)?.setImage(data.couponBrandLogo)
    }

    override val layoutResource: Int
        get() = R.layout.item_view_coupon

}