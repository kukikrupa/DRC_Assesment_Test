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
import com.drc.assesment.model.NewsListResponse
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
        val param = HashMap<String, String>()
        param["sources"] = "google-news"
        param["apiKey"] = "9a0c8e375ada4198a26f7a52638c4b78"



        if (Common.hasInternet(activity)) {
            subscription = postApi.getNewsList(param).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(fun(result: NewsListResponse) {
                    onRetrievePostListSuccess(result)
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

    private fun onRetrievePostListSuccess(result: NewsListResponse) {
        if (result.status.equals("ok")) {
            Toast.makeText(activity, result.toString(), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(activity, result.status, Toast.LENGTH_LONG).show()
        }

    }

    private fun onRetrievePostListError() {
        errorMessage.value = context.getString(R.string.error)
    }


}