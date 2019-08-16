package com.lv.core_arch.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> :
    AppCompatActivity(), HasSupportFragmentInjector {

    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null

    /*
     * Step 1: Rather than injecting the ViewModelFactory
     * in the activity, we are going to implement the
     * HasActivityInjector and inject the ViewModelFactory
     * into our MovieListFragment
     * */
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)

        performDataBinding()
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
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

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    fun replaceFragment(fragment: Fragment, containerId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(containerId, fragment, fragment::class.java.simpleName).commit()
    }
}