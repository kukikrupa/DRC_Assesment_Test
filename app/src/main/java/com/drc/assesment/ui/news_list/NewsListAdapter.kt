package com.drc.assesment.ui.news_list

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drc.assesment.R
import com.drc.assesment.databinding.ItemNewsListBinding
import com.drc.assesment.model.ArticlesResponse
import com.drc.assesment.ui.new_details.NewsDetailsActivity
import com.drc.assesment.utils.Common


class NewsListAdapter() : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    var dataList = mutableListOf<ArticlesResponse>()
    var txtHeading: TextView? = null
    var txtAuthorName: TextView? = null
    var txtDate: TextView? = null
    var imgPhoto: ImageView? = null


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListAdapter.ViewHolder {
        val binding: ItemNewsListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_news_list, parent, false)
        return ViewHolder(binding)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: NewsListAdapter.ViewHolder, position: Int) {
        holder.bind(dataList[position])

        val article : ArticlesResponse = dataList[position]

        txtHeading!!.text = article.title
        txtAuthorName!!.text = article.author
        txtDate!!.text = Common.dateConverter(article.publishedAt)

        Glide.with(holder.itemView.context).load(article.urlToImage)
            .into(imgPhoto!!);

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(holder.itemView.context, NewsDetailsActivity::class.java)
            intent.putExtra("data",article)
            holder.itemView.context.startActivity(intent)
        })

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return dataList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(private val binding: ItemNewsListBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bind(dataList: ArticlesResponse) {
            txtHeading = binding.txtHeading
            txtAuthorName = binding.txtAuthorName
            txtDate = binding.txtDate
            imgPhoto = binding.imgPhoto

        }
    }

    fun updateList(dataList: MutableList<ArticlesResponse>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    fun updateAddList(dataList: MutableList<ArticlesResponse>) {
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun clearList()
    {
        this.dataList.clear()
    }
}