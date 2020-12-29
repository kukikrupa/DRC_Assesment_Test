package com.drc.assesment.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import com.drc.assesment.viewmodel.NewsListViewModel


class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsListViewModel(activity) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}