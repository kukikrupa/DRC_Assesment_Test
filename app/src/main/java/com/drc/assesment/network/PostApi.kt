package com.drc.assesment.network


import com.drc.assesment.model.NewsListResponse
import com.drc.assesment.utils.GET_NEWS_LIST
import io.reactivex.Observable
import retrofit2.http.*


/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {

    @GET(GET_NEWS_LIST)
    fun getNewsList(@QueryMap params: HashMap<String, String>): Observable<NewsListResponse>

}
