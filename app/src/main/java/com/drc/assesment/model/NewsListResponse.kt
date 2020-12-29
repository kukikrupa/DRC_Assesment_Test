package com.drc.assesment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsListResponse(
    val status: String,
    val totalResults: Int,
    val articles: MutableList<ArticlesResponse>): Parcelable

@Parcelize
data class ArticlesResponse(
    val source: SourceArticleResponse,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String): Parcelable

@Parcelize
data class SourceArticleResponse(
    val id: String,
    val name: String,
): Parcelable