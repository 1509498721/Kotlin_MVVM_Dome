package com.thesame.baselibrary.common

import InitActivityManagerTask
import InitAutoTask
import InitFastManageTask
import InitHawkTask
import InitLogTask
import android.app.Application
import android.content.Context
import com.thesame.baselibrary.utlis.launchstarter.TaskDispatcher

/**
 *Created by whz  on 2020-12-11
 */
class BaseApplication : Application() {
    private lateinit var taskDispatcher: TaskDispatcher

    companion object {
        lateinit var context: Context
        lateinit var myApplication: Application
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        myApplication = this
        initTask()

    }

    private fun initTask() {
        TaskDispatcher.init(context)
        taskDispatcher = TaskDispatcher.createInstance()
        taskDispatcher
            .addTask(InitHawkTask())//初始化数据存储
            .addTask(InitFastManageTask())//初始化自定义弹框和图片加载器
            .addTask(InitLogTask())//初始化自定义日志
            .addTask(InitAutoTask())//初始化适配
            .addTask(InitActivityManagerTask())//初始化activity栈
            .start()
        taskDispatcher.await()
    }
}