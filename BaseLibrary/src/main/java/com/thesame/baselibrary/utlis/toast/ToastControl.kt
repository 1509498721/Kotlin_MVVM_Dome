package com.thesame.baselibrary.utlis.toast

import android.widget.Toast
import com.aries.ui.view.radius.RadiusTextView

/**
 * @Author: whz on 2019/1/18 17:49
 * @Description:
 */
 interface ToastControl {
    /**
     * 处理其它异常情况
     *
     * @return
     */
    val toast: Toast?

    /**
     * 设置Toast
     *
     * @param toast    ToastUtil 中的Toast
     * @param textView ToastUtil 中的Toast设置的View
     */
    fun setToast(toast: Toast?, textView: RadiusTextView?)
}