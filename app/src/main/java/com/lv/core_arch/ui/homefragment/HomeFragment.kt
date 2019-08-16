package com.lv.core_arch.ui.homefragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.lv.core_arch.BR
import com.lv.core_arch.R
import com.lv.core_arch.databinding.FragmentHomeBinding
import com.lv.core_arch.ui.base.BaseFragment
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    lateinit var homeFragmentViewModel: HomeFragmentViewModel

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getViewModel(): HomeFragmentViewModel {
        homeFragmentViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory)[HomeFragmentViewModel::class.java]
        return homeFragmentViewModel
    }

    companion object {

        fun newInstance(): Fragment = HomeFragment()
    }
}