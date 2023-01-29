package com.example.test

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.test.databinding.ActivityMainBinding
import com.example.test.presentation.fragment.CouponFragment
import com.example.test.utils.setImage

class MainActivity : FragmentActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_browse_fragment, CouponFragment())
                .commitNow()
        }
    }

    fun setImage(url: String?) {
        binding.image.setImage(url)
    }
}