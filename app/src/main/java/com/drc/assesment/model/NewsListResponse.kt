package com.drc.assesment.model

data class NewsListResponse(
    val status: String,
    val totalResults: Int,
    val articles: MutableList<ArticlesResponse>)


data class ArticlesResponse(
    val source: SourceArticleResponse,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String)

data class SourceArticleResponse(
    val id: String,
    val name: String,
)