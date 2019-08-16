package com.lv.core_arch.ui.home

import com.lv.core_arch.BR
import com.lv.core_arch.R
import com.lv.core_arch.databinding.ActivityHomeBinding
import com.lv.core_arch.ui.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {

    @Inject
    lateinit var homeActivityViewModel: HomeActivityViewModel

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun getViewModel(): HomeActivityViewModel = homeActivityViewModel

    override fun getBindingVariable(): Int = BR.viewModel
}