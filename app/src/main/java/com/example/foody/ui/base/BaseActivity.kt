package com.example.foody.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<V : ViewDataBinding> :
    AppCompatActivity() {
    protected lateinit var viewBinding: V

    @get: LayoutRes
    protected abstract val layoutId: Int

    @get: StyleRes
    protected var themeId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        themeId?.let {
            setTheme(it)
        }
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, layoutId)
        viewBinding.lifecycleOwner = this@BaseActivity
        initComponents()
        initListeners()
    }

    protected abstract fun initComponents()
    protected abstract fun initListeners()
}
