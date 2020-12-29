package com.drc.assesment.injection.component

import com.drc.assesment.injection.module.NetworkModule
import com.drc.assesment.viewmodel.LoginViewModel
import com.drc.assesment.viewmodel.NewsListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(newsListViewModel: NewsListViewModel)
    fun inject(loginViewModel: LoginViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}