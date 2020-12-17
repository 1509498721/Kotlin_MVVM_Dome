package com.thesame.whz.ui.home.repository

import com.thesame.baselibrary.ext.call
import com.thesame.baselibrary.response.BaseRepository
import com.thesame.baselibrary.response.RetrofitFactory
import com.thesame.whz.common.HomeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
object MenuRepository : BaseRepository() {
    //网络请求，翻译
    suspend fun translate(text:String) = withContext(Dispatchers.IO) {
        call {
            RetrofitFactory.instance.create(HomeApiService::class.java).translate(text=text)
        }
    }
}