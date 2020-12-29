package com.drc.assesment.network


import com.drc.assesment.model.CommonResponse
import com.drc.assesment.model.LoginResponse
import com.drc.assesment.utils.GET_NEWS_LIST
import com.drc.assesment.utils.SIGNUP
import io.reactivex.Observable
import retrofit2.http.*
import retrofit2.http.FormUrlEncoded


/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {




    @GET(GET_NEWS_LIST)
    fun getNewsList(@QueryMap params: HashMap<String, String>): Observable<CommonResponse>

}
