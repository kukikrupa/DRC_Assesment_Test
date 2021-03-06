package com.drc.assesment.ui.news_list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.drc.assesment.R
import com.drc.assesment.databinding.ActivityNewsListBinding
import com.drc.assesment.injection.ViewModelFactory
import com.drc.assesment.ui.login.LoginActivity
import com.drc.assesment.ui.new_details.NewsDetailsActivity
import com.drc.assesment.utils.Common
import com.drc.assesment.utils.Prefs
import com.drc.assesment.viewmodel.NewsListViewModel

class NewsListActivity : AppCompatActivity() ,View.OnClickListener{

    private var mBinding: ActivityNewsListBinding? = null
    private lateinit var viewModel: NewsListViewModel
    var mLayoutManager: LinearLayoutManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_list)

        if (intent.hasExtra("username"))
            mBinding!!.txtTitle.text = intent.getStringExtra("username")
        else
            mBinding!!.txtTitle.text = "Wel-come"

        viewModel =
            ViewModelProviders.of(this, ViewModelFactory(this)).get(NewsListViewModel::class.java)
        mLayoutManager = LinearLayoutManager(this!!.applicationContext)
        mBinding!!.recyclerView.layoutManager = mLayoutManager
        mBinding!!.recyclerView.itemAnimator = DefaultItemAnimator()

        mBinding!!.viewModel = viewModel

        viewModel.getNewsList()

        mBinding!!.imgLogout.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view)
        {
            mBinding!!.imgLogout->
            {
                Common.logoutUser(this)
            }
        }
    }
}