package com.example.project_flow_android.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.BR

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity() {

    protected lateinit var binding: B
    abstract val vm: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this
        binding.setVariable(BR.vm, vm)
    }



}