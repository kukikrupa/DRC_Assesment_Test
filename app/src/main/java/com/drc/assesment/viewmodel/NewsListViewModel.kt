package com.drc.assesment.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.drc.assesment.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.drc.assesment.base.BaseViewModel
import com.drc.assesment.model.CommonResponse
import com.drc.assesment.model.LoginResponse
import com.drc.assesment.network.PostApi
import com.drc.assesment.utils.Common
import javax.inject.Inject

class NewsListViewModel(activity: AppCompatActivity) : BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
    lateinit var context: Context
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    private lateinit var subscription: Disposable
    private var activity: AppCompatActivity = activity


    override fun onCleared() {
        super.onCleared()
        if (::subscription.isInitialized)
            subscription.dispose()
    }

    fun getNewsList() {

        if (Common.hasInternet(activity)) {
            subscription = postApi.getNewsList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(fun(result: CommonResponse) {
                    onRetrievePostListSuccess(result,email)
                }) { onRetrievePostListError() }
        }
        else
        {
            Toast.makeText(activity!!,activity.getString(R.string.msg_no_internet),Toast.LENGTH_LONG).show()
        }

    }

    private fun onRetrievePostListStart() {
        Common.showProgress(activity)
    }

    private fun onRetrievePostListFinish() {
        Common.dismissProgress()
    }

    private fun onRetrievePostListSuccess(result: LoginResponse,email: String) {
        if (result.status.equals("200")) {
            Toast.makeText(activity, result.data.user.subject + " Now", Toast.LENGTH_LONG).show()
        } else {
            errorMessage.value = result.message
            Toast.makeText(activity, result.message, Toast.LENGTH_LONG).show()
        }

    }

    private fun onRetrievePostListError() {
        errorMessage.value = "Email is already registered.."
    }


}