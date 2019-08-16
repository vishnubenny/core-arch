package com.lv.core_arch.di

import android.app.Application
import com.lv.core_arch.di.module.ActivityModule
import com.lv.core_arch.ui.base.AppController
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Component(
    modules = [ActivityModule::class,
        AndroidSupportInjectionModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(appController: AppController)
}