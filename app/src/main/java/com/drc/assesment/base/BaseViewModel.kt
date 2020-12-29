package com.drc.assesment.base

import androidx.lifecycle.ViewModel
import com.drc.assesment.injection.component.DaggerViewModelInjector
import com.drc.assesment.injection.component.ViewModelInjector
import com.drc.assesment.injection.module.NetworkModule
import com.drc.assesment.viewmodel.LoginViewModel
import com.drc.assesment.viewmodel.NewsListViewModel


abstract class BaseViewModel:ViewModel(){


    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {

            is NewsListViewModel -> injector.inject(this)
            is LoginViewModel -> injector.inject(this)
        }
    }
}