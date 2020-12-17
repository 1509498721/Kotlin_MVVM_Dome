package com.thesame.baselibrary.ext
import com.thesame.baselibrary.response.BaseResponse
import com.thesame.baselibrary.response.BaseRepository

/**
 * 扩展函数
 */

/**
 * 数据仓库中的异步请求回调
 */
suspend fun <T> BaseRepository.call(job: suspend () -> T): BaseResponse<T> {
    return try {
        BaseResponse(job())
    } catch (e: java.lang.Exception) {
        BaseResponse(e)
    }
}
