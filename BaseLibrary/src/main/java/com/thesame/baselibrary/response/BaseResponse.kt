package com.thesame.baselibrary.response
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import retrofit2.Response
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.ParseException

class BaseResponse<T> {
    var code = SUCCESS_CODE
    var body: T? = null
    var msg: String? = null

    constructor(response: Response<T>) {
        body = response.body()
    }

    constructor(t: T) {
        body = t
    }

    constructor(throwable: Throwable) {
        body = null
        code = ERROR_CODE
        msg = when (throwable) {
            is HttpException -> {
                code = throwable.code()
                val errorBody = throwable.response()?.errorBody()?.string()
                when (throwable.code()) {
                    404 -> "The right resources were not found"
                    500 -> "Server internal error"
                    else -> errorBody
                }
            }
            is ConnectException, is UnknownHostException -> "网络连接失败,请检查网络"
            is InterruptedIOException -> "连接超时,请稍后再试"
            is JsonParseException, is JSONException, is ParseException ->"解析服务器响应数据失败"
            else -> throwable.message
        }
    }

    companion object {
        const val ERROR_CODE = 99999
        const val SUCCESS_CODE = 200
    }
}
