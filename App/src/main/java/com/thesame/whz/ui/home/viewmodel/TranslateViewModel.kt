package com.thesame.whz.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.thesame.baselibrary.common.BaseViewModel
import com.thesame.baselibrary.ext.execute
import com.thesame.baselibrary.response.Resource
import com.thesame.whz.bean.TranslateBean
import com.thesame.whz.ui.home.repository.MenuRepository
import kotlinx.coroutines.launch


class TranslateViewModel(application: Application) : BaseViewModel(application) {
    //mData，使用Triple<A,B,C>储存网络请求反应，A为网络请求是否成功，B为成功的消息，C为错误的信息
     val mDataTranslate: MutableLiveData<Triple<Boolean, TranslateBean?, Resource<String>>> by lazy {
        MutableLiveData<Triple<Boolean, TranslateBean?, Resource<String>>>()
    }

    fun translate(text: String) {
        viewModelScope.launch {
            MenuRepository.translate(text).execute(mDataTranslate)
        }
    }
}
