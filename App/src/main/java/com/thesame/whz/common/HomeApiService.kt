package com.thesame.whz.common

import com.thesame.whz.bean.TranslateBean
import retrofit2.http.*

interface HomeApiService{


    /**
     *
     * 翻译
     */
    @GET(HomeApiConfig.Translate)
    suspend fun translate(@Query("doctype") doctype: String = "json", @Query("type") type: String = "AUTO", @Query("i") text: String): TranslateBean
}
