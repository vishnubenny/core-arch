package com.lv.core_arch.di.module

import com.lv.core_arch.ui.home.HomeActivity
import com.lv.core_arch.ui.home.di.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun bindHomeActivity(): HomeActivity
}