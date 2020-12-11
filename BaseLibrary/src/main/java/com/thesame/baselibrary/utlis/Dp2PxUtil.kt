package com.thesame.baselibrary.utlis

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * @Description 尺寸转化
 * Created by whz  on 2019-06-27
 */
class Dp2PxUtil {
    /**
     * 设置全屏(true 全屏, false 取消全屏)
     *
     * @param pFullScreen
     */
    protected fun setfullScreen(activity: Activity, pFullScreen: Boolean) {
        val attrs = activity.window.attributes
        if (pFullScreen) {
            attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
            activity.window.attributes = attrs
            // 全屏设置
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        } else {
            attrs.flags = attrs.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
            activity.window.attributes = attrs
            // 取消全屏设置
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

    companion object {
        fun dip2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }

        fun px2dip(context: Context, pxValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }

        /**
         * 将px值转换为sp值，保证文字大小不变
         *
         * @param pxValue
         * @param fontScale（DisplayMetrics类中属性scaledDensity）
         * @return
         */
        fun px2sp(pxValue: Float, fontScale: Float): Int {
            return (pxValue / fontScale + 0.5f).toInt()
        }

        /**
         * 将sp值转换为px值，保证文字大小不变
         *
         * @param spValue
         * @param fontScale（DisplayMetrics类中属性scaledDensity）
         * @return
         */
        fun sp2px(spValue: Float, fontScale: Float): Int {
            return (spValue * fontScale + 0.5f).toInt()
        }

        /**
         * 获取屏幕宽，单位为dip
         *
         * @return
         */
        fun getScreenWidth(activity: Activity): Int {
            val dm = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(dm)
            return dm.widthPixels
        }

        /**
         * 获取屏幕高，单位为dip
         *
         * @return
         */
        fun getScreenHeight(activity: Activity): Int {
            val dm = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(dm)
            return dm.heightPixels
        }
    }
}