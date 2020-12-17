package com.thesame.whz.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.thesame.baselibrary.common.BaseActivity
import com.thesame.baselibrary.ext.acStart
import com.thesame.whz.MainActivity
import com.thesame.whz.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>() {


    override fun initIntent() {
    }

    override fun initView(savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            delay(2000) //延时两秒跳转界面
            startMain()
        }
    }
    //两秒后跳转首页，由于初始化问题，可能时间会久一点，此时就不延时跳转了，根据手机性能决定什么时候跳转
    private fun startMain() {
        acStart(MainActivity::class.java)
        finish()
    }
}