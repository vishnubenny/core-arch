package com.lv.core_arch.ui.home.di

import com.lv.core_arch.ui.home.HomeActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @Provides
    fun providesHomeActivityViewModel(): HomeActivityViewModel = HomeActivityViewModel()
}