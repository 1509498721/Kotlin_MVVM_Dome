package com.thesame.baselibrary.utlis

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager

/**
 * Created by whz  on 2019-06-27
 */
object SystemUtil {
    /**
     * 获取当前系统的版本
     *
     * @param context
     * @return
     */
    fun getVesion(context: Context): Int {
        var pi: PackageInfo? = null
        try {
            val pm = context.packageManager
            pi = pm.getPackageInfo(
                context.packageName,
                PackageManager.GET_CONFIGURATIONS
            )
            return pi.versionCode
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }

    fun getVersionName(context: Context): String {
        var pi: PackageInfo? = null
        try {
            val pm = context.packageManager
            pi = pm.getPackageInfo(
                context.packageName,
                PackageManager.GET_CONFIGURATIONS
            )
            return pi.versionName
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    fun getApplicationName(context: Context): String {
        var packageManager: PackageManager? = null
        var applicationInfo: ApplicationInfo? = null
        try {
            packageManager = context.applicationContext.packageManager
            applicationInfo = packageManager.getApplicationInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            applicationInfo = null
        }
        return packageManager!!.getApplicationLabel(applicationInfo!!) as String
    }

    /**
     * 取签名证书信息
     *
     * @param mContext
     * @return
     */
    fun getSign(mContext: Context): Int {
        val pkgsign = -1
        val pm = mContext.packageManager
        try {
            val packageInfo = pm.getPackageInfo(mContext.packageName, PackageManager.GET_SIGNATURES)
            return packageInfo.signatures[0].hashCode()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return pkgsign
    }
}