package com.drc.assesment.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.drc.assesment.R
import com.drc.assesment.databinding.ActivityLoginBinding
import com.drc.assesment.injection.ViewModelFactory
import com.drc.assesment.utils.validator.Validator
import com.drc.assesment.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var mBinding: ActivityLoginBinding? = null
    private lateinit var viewModel: LoginViewModel
    lateinit var strUsername: String
    lateinit var strPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        setClickLister()

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(LoginViewModel::class.java)

    }

    fun setClickLister() {
        mBinding!!.buttonLogin.setOnClickListener(this)
        mBinding!!.buttonLogin.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonLogin -> {
                checkLoginValidation(mBinding!!.edtUsername.text.toString(),mBinding!!.edtPassword.text.toString())
            }

        }
    }


    private fun checkLoginValidation(username: String, password: String) {
        Validator.validateUserName(username)?.let {
            mBinding!!.edtUsername.setError(getString(it.msg))
            mBinding!!.edtUsername.requestFocus()

            return
        }
        mBinding!!.edtUsername.setError(null)

        Validator.validatePassword(password, R.string.blank_password)?.let {
            mBinding!!.edtPassword.setError(getString(it.msg))
            mBinding!!.edtPassword.requestFocus()

            return
        }
        mBinding!!.edtPassword.setError(null)


        strUsername = mBinding!!.edtUsername.text.toString().trim()
        strPassword = mBinding!!.edtPassword.text.toString().trim()

        viewModel.getLoginDetails(this, strUsername)!!.observe(this, Observer {

            if (it == null) {
                viewModel.insertData(this, strUsername, strPassword)
                Toast.makeText(this,"added",Toast.LENGTH_LONG).show()
            }
            else {

                Toast.makeText(this,"already",Toast.LENGTH_LONG).show()

            }
        })
    }

}
