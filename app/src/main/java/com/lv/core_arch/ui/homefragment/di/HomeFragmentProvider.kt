package com.lv.core_arch.ui.homefragment.di

import com.lv.core_arch.ui.homefragment.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentProvider {

    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun bindHomeFragment(): HomeFragment
}