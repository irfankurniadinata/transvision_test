package com.example.test.presentation.detail_coupon

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.test.databinding.ActivityDetailCouponBinding
import com.example.test.utils.setImage
import com.example.test.utils.setTextHtml

class DetailCouponActivity : FragmentActivity() {

    private lateinit var binding : ActivityDetailCouponBinding

    var couponBrandName: String? = null
    var couponTnc: String? = null
    var couponBrandLogo: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCouponBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        couponBrandName = intent.getStringExtra("couponBrandName")
        couponTnc = intent.getStringExtra("couponTnc")
        couponBrandLogo = intent.getStringExtra("couponBrandLogo")

        binding.apply {
            image.setImage(couponBrandLogo)
            tvName.text = couponBrandName
            tvDescription.setTextHtml(couponTnc)
        }
    }
}