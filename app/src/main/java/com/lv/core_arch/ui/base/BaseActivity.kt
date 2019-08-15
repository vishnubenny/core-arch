package com.lv.core_arch.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<BaseNavigator>> :
    AppCompatActivity() {

    var mViewDataBinding: T? = null
    var mViewModel: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()
    }

    /**
     *Setting content view and setting data variable
     */
    private fun performDataBinding() {
        this.mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        if (this.mViewModel == null) {
            this.mViewModel = getViewModel()
        }
        this.mViewDataBinding?.setVariable(getBindingVariable(), this.mViewModel)
        this.mViewDataBinding?.lifecycleOwner = this
        this.mViewDataBinding?.executePendingBindings()
    }

    /**
     *@return abstract function needed to override in child class and return layout resource
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    abstract fun getBindingVariable(): Int
}