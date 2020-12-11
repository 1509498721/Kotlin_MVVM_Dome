package com.thesame.baselibrary.utlis.toast;

import android.widget.Toast;

import com.aries.ui.view.radius.RadiusTextView;

/**
 * @Author: whz on 2019/1/18 17:49
 * @Description:
 */
public interface ToastControl {

    /**
     * 处理其它异常情况
     *
     * @return
     */
    Toast getToast();

    /**
     * 设置Toast
     *
     * @param toast    ToastUtil 中的Toast
     * @param textView ToastUtil 中的Toast设置的View
     */
    void setToast(Toast toast, RadiusTextView textView);
}
