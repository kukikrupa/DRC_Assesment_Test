package com.drc.assesment.ui.news_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.drc.assesment.R
import com.drc.assesment.databinding.ActivityNewsListBinding

class NewsListActivity : AppCompatActivity() {

    private var mBinding: ActivityNewsListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_list)
        mBinding!!.txtTitle.text = intent.getStringExtra("username")

    }
}