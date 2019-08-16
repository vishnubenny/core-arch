package com.lv.core_arch.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {

    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null
    private var mRootView: View? = null

    var mActivity: BaseActivity<*, *>? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        when (context) {
            is BaseActivity<*, *> -> mActivity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return performDataBinding(inflater, container)
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    private fun performDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View? {
        if (null == mViewModel) {
            mViewModel = getViewModel()
        }
        mViewDataBinding = DataBindingUtil.inflate<T>(inflater, getLayoutId(), container, false)
        mRootView = mViewDataBinding?.root

        mViewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding?.lifecycleOwner = this
        mViewDataBinding?.executePendingBindings()

        return mRootView
    }

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): V

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int
}