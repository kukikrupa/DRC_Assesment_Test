package com.drc.assesment.viewmodel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData

import com.drc.assesment.base.BaseViewModel
import com.drc.assesment.model.LoginTableModel
import com.drc.assesment.repository.LoginRepository

class LoginViewModel(activity: AppCompatActivity) : BaseViewModel() {


    var liveDataLogin: LiveData<LoginTableModel>? = null

    fun insertData(context: Context, username: String, password: String) {
        LoginRepository.insertData(context, username, password)
    }

    fun getLoginDetails(context: Context, username: String) : LiveData<LoginTableModel>? {
        liveDataLogin = LoginRepository.getLoginDetails(context, username)
        return liveDataLogin
    }


}