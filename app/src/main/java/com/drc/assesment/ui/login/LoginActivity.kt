package com.drc.assesment.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.drc.assesment.R
import com.drc.assesment.databinding.ActivityLoginBinding
import com.drc.assesment.injection.ViewModelFactory
import com.drc.assesment.utils.validator.Validator
import com.drc.assesment.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var mBinding: ActivityLoginBinding? = null
    private lateinit var viewModel: LoginViewModel
    private var errorSnackbar: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        setClickLister()

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(LoginViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

    }

    fun setClickLister() {
        mBinding!!.buttonLogin.setOnClickListener(this)
        mBinding!!.buttonLogin.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonLogin -> {
                checkLoginValidation(mBinding!!.edtEmpCode.text.toString(),mBinding!!.edtPassword.text.toString())
            }

        }
    }

    private fun showError(errorMessage: String) {
        errorSnackbar = Snackbar.make(mBinding!!.root, errorMessage, Snackbar.LENGTH_LONG)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    fun checkLoginValidation(username: String, password: String) {
        Validator.validateUserName(username)?.let {
            mBinding!!.edtEmpCode.setError(getString(it.msg))
            mBinding!!.edtEmpCode.requestFocus()

            return
        }
        mBinding!!.edtEmpCode.setError(null)

        Validator.validatePassword(password, R.string.blank_password)?.let {
            mBinding!!.edtPassword.setError(getString(it.msg))
            mBinding!!.edtPassword.requestFocus()

            return
        }
        mBinding!!.edtPassword.setError(null)


        //viewModel.apiLogin(username,password)
    }

}
