package com.thesame.baselibrary.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.thesame.baselibrary.action.ToastAction

/**
 * 理论上可以不要这个ViewModel，不过有些ViewModel如果要做统一管理可以使用这个
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application), ToastAction {
    //声明协程的用法,这样可以有效使用协程的功能
    //var job = CoroutineScope(SupervisorJob() + Dispatchers.Main)
}
