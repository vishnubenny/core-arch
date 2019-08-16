package com.lv.core_arch.ui.homefragment.di

import androidx.lifecycle.ViewModelProvider
import com.lv.core_arch.ui.base.util.ViewModelProviderFactory
import com.lv.core_arch.ui.homefragment.HomeFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeFragmentModule {

    @Provides
    fun providesHomeFragmentViewModel(): HomeFragmentViewModel = HomeFragmentViewModel()

    @Provides
    fun providesHomeFragmentViewModelProviderFactory(homeFragmentViewModel: HomeFragmentViewModel):
            ViewModelProvider.Factory {
        return ViewModelProviderFactory(homeFragmentViewModel)
    }
}