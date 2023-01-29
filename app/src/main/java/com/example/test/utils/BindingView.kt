package com.example.test.utils

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.LayoutRes
import androidx.leanback.widget.BaseCardView

abstract class BindingView<T> : BaseCardView {

    protected abstract fun initViewBinding()

    constructor(context: Context?) : super(context) {
        initViewBinding()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initViewBinding()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initViewBinding()

    }

    protected abstract fun bind(data: T)

    @get:LayoutRes
    protected abstract val layoutResource: Int
}