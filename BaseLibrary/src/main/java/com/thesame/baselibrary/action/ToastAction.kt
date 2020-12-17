package com.thesame.baselibrary.action

import androidx.annotation.StringRes
import com.thesame.baselibrary.utlis.toast.ToastUtil

/**
 * 在需要的地方实现该接口即可，简单轻便
 */
interface ToastAction {
    fun toast(text: CharSequence?) {
        ToastUtil.show(text)
    }

    fun toast(@StringRes id: Int) {
        ToastUtil.show(id)
    }

    fun toast(`object`: Any?) {
        ToastUtil.show(`object`)
    }
}