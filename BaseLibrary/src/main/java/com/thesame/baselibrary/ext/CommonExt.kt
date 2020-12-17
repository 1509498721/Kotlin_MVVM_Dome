package com.thesame.baselibrary.ext

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.lifecycle.MutableLiveData
import com.thesame.baselibrary.response.ApiConfig
import com.thesame.baselibrary.response.BaseResponse
import com.thesame.baselibrary.response.Resource



fun <T> BaseResponse<T>.execute(mutableLiveData:MutableLiveData<Triple<Boolean, T?, Resource<String>>>){
    if (this.code == ApiConfig.BaseSuccess) {
        mutableLiveData.value = Triple(true, this.body, Resource())
    } else {
        mutableLiveData.value =
            Triple(false, null, Resource(status = this.code, msg = this.msg, obj = ""))
    }
}


//Float类型转换dp
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

//Int类型转换dp[本质上就是Float转换dp,只不过我们的编码习惯更习惯于int.dp 例: 18.dp]
val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

//Float类型转换sp
val Float.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )

//Int类型转换sp[本质上就是Float转换sp,只不过我们的编码习惯更习惯于int.sp 例: 18.sp]
val Int.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

//转换dip
fun Context.dip(value: Int): Int {
    val scale = resources.displayMetrics.density;
    return (value * scale + 0.5f).toInt()
}

//转换sp
fun Context.sp(value: Int): Int {
    val scale = resources.displayMetrics.scaledDensity;
    return (value * scale + 0.5f).toInt()
}