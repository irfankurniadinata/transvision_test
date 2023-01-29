package com.example.test.presentation.fragment

import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import com.example.test.data.model.CouponDetail

class CouponCardPresenter() : Presenter(){
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(CouponCardView(parent.context))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        (viewHolder.view as CouponCardView).bind(item as CouponDetail)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

    }

}