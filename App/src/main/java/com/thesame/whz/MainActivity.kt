package com.thesame.whz

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import com.thesame.baselibrary.utlis.helper.ActivityStackManager
import com.thesame.baselibrary.common.BaseActivity
import com.thesame.whz.databinding.ActivityMainBinding
import com.thesame.whz.ui.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var mExitTime: Long = 0
    override fun initIntent() {
    }

    override fun initView(savedInstanceState: Bundle?) {
        replaceFragment(HomeFragment::class.java.name)

        mView.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbHome -> {
                    replaceFragment(HomeFragment::class.java.name)
                }
                R.id.rbExploration -> {

                }
                R.id.rbPurchasing -> {

                }
                R.id.rbCart -> {

                }
                R.id.rbStandingList -> {

                }
            }
        }
    }


    private fun replaceFragment(tag: String) {
        var tempFragment = supportFragmentManager.findFragmentByTag(tag)
        val transaction = supportFragmentManager.beginTransaction()
        if (tempFragment == null) {
            try {
                tempFragment = Class.forName(tag).newInstance() as Fragment
                transaction.add(R.id.fragment, tempFragment, tag)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        val fragments = supportFragmentManager.fragments
        if (fragments != null) {
            for (i in fragments.indices) {
                val fragment = fragments[i]
                if (fragment.tag == tag) {
                    transaction.show(fragment)
                } else {
                    transaction.hide(fragment)
                }
            }
        }
        transaction.commitAllowingStateLoss()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                toast("再按一次退出app")
                mExitTime = System.currentTimeMillis()
            } else {
                ActivityStackManager.finishAllActivities()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}