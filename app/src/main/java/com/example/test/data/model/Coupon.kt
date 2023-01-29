package com.example.test.data.model

data class Coupon(
    var status: Boolean? = null,
    var statusCode: String? = null,
    var result: List<CouponDetail>? = null
)
