package com.thesame.baselibrary.common

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.thesame.baselibrary.utlis.helper.LogUtil
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType

/**
 * @author whz
 * @time 2020/4/2
 */
abstract class BaseFragment<SV : ViewBinding>() : Fragment() {
    protected lateinit var mView: SV

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initBindView(inflater, container)
    }

    //初始化ViewBinding
    private fun initBindView(inflater: LayoutInflater, container: ViewGroup?): View {
        val superclass = javaClass.genericSuperclass
        val aClass = (superclass as ParameterizedType).actualTypeArguments[0] as Class<*>
        try {
            val method: Method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
            mView = method.invoke(null, inflater, container, false) as SV
        } catch (e: NoSuchMethodException) {
            LogUtil.e(e)
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            LogUtil.e(e)
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            LogUtil.e(e)
            e.printStackTrace()
        }
        return mView.root
    }

    //获取父Activity
    private fun getParentActivity(): Activity? {
        return activity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView(savedInstanceState)
    }

    abstract fun initView(savedInstanceState: Bundle?)

}