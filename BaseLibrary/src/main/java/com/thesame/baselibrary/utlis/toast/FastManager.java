package com.thesame.baselibrary.utlis.toast;

import android.app.Application;
import android.graphics.Color;
import com.thesame.baselibrary.utlis.GlideManager;


/**
 * @Author: whz on 2019/7/30 13:49
 * Function: 各种UI相关配置属性
 * Description:
 * 1、2018-9-26 16:58:14 新增BasisActivity 子类前台监听按键事件
 */
public class FastManager {

    private static String TAG = "FastManager";
    private static volatile FastManager sInstance;

    private FastManager() {
    }

    public static FastManager getInstance() {
        if (sInstance == null) {
            throw new NullPointerException(FastConstant.EXCEPTION_NOT_INIT_FAST_MANAGER);
        }
        return sInstance;
    }

    private static Application mApplication;


    /**
     * ToastUtil相关配置
     */
    private ToastControl mToastControl;

    public Application getApplication() {
        return mApplication;
    }


    public static FastManager init(Application application) {
        //保证只执行一次初始化属性
        if (mApplication == null && application != null) {
            mApplication = application;
            sInstance = new FastManager();

            //注册activity生命周期
            //初始化Toast工具
            ToastUtil.init(mApplication);
            //初始化Glide
            GlideManager.setPlaceholderColor(Color.parseColor("#CCCCCC"));
            GlideManager.setPlaceholderRoundRadius(6);
        }
        return getInstance();
    }




    public ToastControl getToastControl() {
        return mToastControl;
    }

    /**
     * 配置ToastUtil
     *
     * @param control
     * @return
     */
    FastManager setToastControl(ToastControl control) {
        mToastControl = control;
        return this;
    }
}
