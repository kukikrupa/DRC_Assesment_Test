package com.drc.assesment.ui.new_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.drc.assesment.R
import com.drc.assesment.databinding.ActivityNewsDetailsBinding
import com.drc.assesment.model.ArticlesResponse
import com.drc.assesment.utils.Common


class NewsDetailsActivity : AppCompatActivity() ,View.OnClickListener{

    private var mBinding: ActivityNewsDetailsBinding? = null
    private var article:ArticlesResponse?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_details)
        article= intent.getParcelableExtra<ArticlesResponse>("data")

        mBinding!!.txtHeading.text = article!!.title
        mBinding!!.txtDescription.text = article!!.description
        mBinding!!.txtAuthorName.text = article!!.author
        mBinding!!.txtDate.text = Common.dateConverter2(article!!.publishedAt)

        Glide.with(this).load(article!!.urlToImage)
            .into(mBinding!!.imgPhoto);

        mBinding!!.imgBack.setOnClickListener(this)
        mBinding!!.cardView.setOnClickListener(this)



    }

    override fun onClick(view: View?) {
        when(view)
        {
            mBinding!!.imgBack -> {
                finish()
            }

            mBinding!!.cardView -> {
                val viewIntent = Intent(
                    "android.intent.action.VIEW",
                    Uri.parse(article!!.url)
                )
                startActivity(viewIntent)
            }
        }
    }
}